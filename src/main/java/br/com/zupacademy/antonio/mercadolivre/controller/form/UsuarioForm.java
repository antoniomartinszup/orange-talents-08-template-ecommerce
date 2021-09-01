package br.com.zupacademy.antonio.mercadolivre.controller.form;

import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import br.com.zupacademy.antonio.mercadolivre.security.SenhaLimpa;
import br.com.zupacademy.antonio.mercadolivre.validate.ItemGenericoUnico;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UsuarioForm {

    @ItemGenericoUnico(domainClass = Usuario.class, fieldName = "login")
    @NotEmpty
    @Email
    private String login;

    @NotEmpty
    @Size(min = 6)
    private String senha;

    public UsuarioForm(String login, @NotEmpty @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converteParaModelUsuario() {
        return new Usuario(this.login, new SenhaLimpa(senha));
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
