package br.com.zupacademy.antonio.mercadolivre.controller.form;

import br.com.zupacademy.antonio.mercadolivre.model.Pergunta;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class PerguntaForm {

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 300)
    private String assunto;

    public PerguntaForm(String titulo, String assunto) {
        this.titulo = titulo;
        this.assunto = assunto;
    }

    public Pergunta converteParaModelPergunta(Usuario usuario, Produto produto) {
        return new Pergunta(this.titulo, this.assunto, usuario, produto);
    }
}
