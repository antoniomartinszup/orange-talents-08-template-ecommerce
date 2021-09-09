package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.form.UsuarioForm;
import br.com.zupacademy.antonio.mercadolivre.repository.UsuarioRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
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
    public void cadastrarUsuarioComDadosSucesso() throws Exception {
        UsuarioForm usuarioForm = new UsuarioForm("zepa@email.com", "123456");
        mockMvc.perform(post("/usuarios")
                        .content(gson.toJson(usuarioForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void cadastrarUsuarioComFalhaFormatoEmail() throws Exception {
        UsuarioForm usuarioForm = new UsuarioForm("zepa", "123456");
        mockMvc.perform(post("/usuarios")
                        .content(gson.toJson(usuarioForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void cadastrarUsuarioComFalhaFormatoSenha() throws Exception {
        UsuarioForm usuarioForm = new UsuarioForm("zepa@email.com", "123");
        mockMvc.perform(post("/usuarios")
                        .content(gson.toJson(usuarioForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}
