package br.com.zupacademy.antonio.mercadolivre.model;

import javax.persistence.*;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Imagem() {
    }

    public Imagem(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public Produto getProduto() {
        return produto;
    }
}
