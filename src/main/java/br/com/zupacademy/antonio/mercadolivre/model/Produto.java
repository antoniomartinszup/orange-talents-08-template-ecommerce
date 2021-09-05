package br.com.zupacademy.antonio.mercadolivre.model;

import br.com.zupacademy.antonio.mercadolivre.controller.form.CaracteristicaForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer quantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private List<Caracteristica> caracteristicas = new ArrayList<>();

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Categoria categoria;
    private LocalDateTime dataRegistro = LocalDateTime.now();

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Imagem> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade,
                   List<CaracteristicaForm> caracteristicas, String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = CaracteristicaForm.converteParaModelCaracteristicaList(caracteristicas, this);
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public void anexaImagens(Set<String> links) {
        Set<Imagem> imagens = links.stream()
                .map(link -> new Imagem(link,this))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Long id) {
        return this.id.equals(id);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Set<Imagem> getImagens() {
        return imagens;
    }
}
