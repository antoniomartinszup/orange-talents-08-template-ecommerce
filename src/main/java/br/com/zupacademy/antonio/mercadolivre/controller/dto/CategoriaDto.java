package br.com.zupacademy.antonio.mercadolivre.controller.dto;

import br.com.zupacademy.antonio.mercadolivre.model.Categoria;

public class CategoriaDto {

    private Long id;
    private String nome;
    private CategoriaDto categoriaMae;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.categoriaMae = null;
        if (categoria.getCategoriaMae() != null) {
            this.categoriaMae = new CategoriaDto(categoria.getCategoriaMae());
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaDto getCategoriaMae() {
        return categoriaMae;
    }
}
