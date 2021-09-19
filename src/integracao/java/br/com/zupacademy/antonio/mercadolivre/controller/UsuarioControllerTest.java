package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.form.UsuarioForm;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import br.com.zupacademy.antonio.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.antonio.mercadolivre.security.SenhaLimpa;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    Gson gson = new Gson();

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    @DisplayName("Cadastra Usuario com sucesso")
    public void cadastrarUsuarioComDadosSucesso() throws Exception {
        UsuarioForm usuarioForm = new UsuarioForm("zepa@email.com", "123456");
        mockMvc.perform(post("/usuarios")
                        .content(gson.toJson(usuarioForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Falha no cadastro do Usuario atributo email formato invalido")
    public void falhaNoCadastroUsuarioFormatoEmail() throws Exception {
        UsuarioForm usuarioForm = new UsuarioForm("zepa", "123456");
        mockMvc.perform(post("/usuarios")
                        .locale(new Locale("pt", "BR"))
                        .content(gson.toJson(usuarioForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].campo").value("login"))
                .andExpect(jsonPath("$[0].erro").value("deve ser um endereço de e-mail bem formado"))
                .andReturn().getResponse();
    }

    @Test
    @DisplayName("Falha no cadastro do Usuario atributo senha formato invalido")
    public void falhaNoCadastroUsuarioFormatoSenha() throws Exception {
        UsuarioForm usuarioForm = new UsuarioForm("zepa@email.com", "123");
        mockMvc.perform(post("/usuarios")
                        .locale(new Locale("pt", "BR"))
                        .content(gson.toJson(usuarioForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$[0].campo").value("senha"))
                .andExpect(jsonPath("$[0].erro").value("tamanho deve ser entre 6 e 2147483647"))
                .andReturn().getResponse();
    }

    @Test
    @DisplayName("Falha no cadastro do Usuario atributo email duplicado")
    public void falhaNoCadastroUsuarioEmailDuplicado() throws Exception {

        Usuario usuarioDuplicado = new Usuario("zepa@email.com", new SenhaLimpa("123456"));
        usuarioRepository.save(usuarioDuplicado);
        UsuarioForm usuarioForm = new UsuarioForm("zepa@email.com", "123456");

        mockMvc.perform(post("/usuarios")
                        .locale(new Locale("pt", "BR"))
                        .content(gson.toJson(usuarioForm))
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].campo").value("login"))
                .andExpect(jsonPath("$[0].erro").value("Item já cadastrado"))
                .andReturn().getResponse();
    }
}
