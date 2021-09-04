package br.com.zupacademy.antonio.mercadolivre.controller.dto;

import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class UsuarioDto {

    private Long id;
    private String login;

    @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataRegistro = LocalDateTime.now();

    public UsuarioDto(Long id, String login, LocalDateTime dataRegistro) {
        this.id = id;
        this.login = login;
        this.dataRegistro = dataRegistro;
    }

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.dataRegistro = usuario.getDataRegistro();
    }

    public static UsuarioDto converteParaUsuarioDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(), usuario.getLogin(), usuario.getDataRegistro());
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }
}
