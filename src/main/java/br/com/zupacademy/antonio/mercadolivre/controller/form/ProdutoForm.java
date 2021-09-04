package br.com.zupacademy.antonio.mercadolivre.controller.form;

import br.com.zupacademy.antonio.mercadolivre.model.Categoria;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import br.com.zupacademy.antonio.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.antonio.mercadolivre.repository.UsuarioRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProdutoForm {

    @NotEmpty
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @Positive
    private Integer quantidade;

    @Valid
    @Size(min = 3)
    private List<CaracteristicaForm> caracteristicas;

    @NotEmpty
    @Size(max = 1000)
    private String descricao;

    @NotNull
    private Long idCategoria;

    public ProdutoForm(String nome, BigDecimal valor, Integer quantidade,
                       List<CaracteristicaForm> caracteristicas, String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public Produto converteParaModelProduto(CategoriaRepository cRepo, UsuarioRepository uRepo, Usuario usuario) {
        Optional<Categoria> categoria = cRepo.findById(this.idCategoria);
        if (categoria.isEmpty()) throw new IllegalArgumentException("Categoria não existe");
        return new Produto(this.nome, this.valor, this.quantidade, this.caracteristicas, this.descricao, categoria.get(), usuario);
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

    public List<CaracteristicaForm> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }
}
