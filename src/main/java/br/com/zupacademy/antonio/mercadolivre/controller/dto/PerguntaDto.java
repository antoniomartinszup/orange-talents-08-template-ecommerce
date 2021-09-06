package br.com.zupacademy.antonio.mercadolivre.controller.dto;

import br.com.zupacademy.antonio.mercadolivre.model.Pergunta;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PerguntaDto {

    private Long id;
    private String titulo;
    private String assunto;

    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime momentoRegistro = LocalDateTime.now();
    private UsuarioDto usuario;
    private ProdutoDto produto;

    public PerguntaDto(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.titulo = pergunta.getTitulo();
        this.assunto = pergunta.getAssunto();
        this.momentoRegistro = pergunta.getMomentoRegistro();
        this.usuario = new UsuarioDto(pergunta.getUsuario());
        this.produto = new ProdutoDto(pergunta.getProduto());
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

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public ProdutoDto getProduto() {
        return produto;
    }
}
