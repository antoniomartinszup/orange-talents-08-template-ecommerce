package br.com.zupacademy.antonio.mercadolivre.controller.dto;

import br.com.zupacademy.antonio.mercadolivre.model.Opiniao;


public class OpiniaoDto {

    private Long id;
    private Integer nota;
    private String titulo;
    private String descricao;
    private UsuarioDto usuario;
    private ProdutoDto produto;

    public OpiniaoDto(Opiniao opiniao) {
        this.id = opiniao.getId();
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.usuario = new UsuarioDto(opiniao.getUsuario());
        this.produto = new ProdutoDto(opiniao.getProduto());
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public ProdutoDto getProduto() {
        return produto;
    }
}
