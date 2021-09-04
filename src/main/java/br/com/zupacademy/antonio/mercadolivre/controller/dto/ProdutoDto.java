package br.com.zupacademy.antonio.mercadolivre.controller.dto;

import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoDto {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private List<CaracteristicaDto> caracteristicas;
    private String descricao;
    private CategoriaDto categoria;

    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataRegistro;
    private UsuarioDto usuario;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.caracteristicas = CaracteristicaDto.converteParaCaracteristicaDto(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        this.categoria = new CategoriaDto(produto.getCategoria());
        this.dataRegistro = produto.getDataRegistro();
        this.usuario = new UsuarioDto(produto.getUsuario());
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

    public List<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }
}
