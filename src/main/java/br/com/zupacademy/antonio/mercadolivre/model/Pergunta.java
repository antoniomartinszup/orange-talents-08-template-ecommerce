package br.com.zupacademy.antonio.mercadolivre.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String assunto;
    private LocalDateTime momentoRegistro = LocalDateTime.now();

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta() {
    }

    public Pergunta(String titulo, String assunto, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.assunto = assunto;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAssunto() {
        return assunto;
    }

    public LocalDateTime getMomentoRegistro() {
        return momentoRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }
}
