package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.form.CategoriaForm;
import br.com.zupacademy.antonio.mercadolivre.model.Categoria;
import br.com.zupacademy.antonio.mercadolivre.repository.CategoriaRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    Gson gson = new Gson();

    @BeforeEach
    void setup() {
        categoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("Tentativa de Cadastrar Categoria sem Permissao")
    public void tentativaDeCadastrarCategoriaSemTerPermissao() throws Exception {
        CategoriaForm categoriaForm = new CategoriaForm("Tecnologia", null);
        mockMvc.perform(post("/categorias")
                        .content(gson.toJson(categoriaForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));
    }

    @Test
    @WithMockUser
    @DisplayName("Cadastra Categoria com sucesso")
    public void cadastraSucesso() throws Exception {
        CategoriaForm categoriaForm = new CategoriaForm("Tecnologia", null);
        mockMvc.perform(post("/categorias")
                        .content(gson.toJson(categoriaForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    @WithMockUser
//    @DisplayName("Cadastra Categoria e Categoria Mae com sucesso")
//    public void cadastraCategoriaMaeSucesso() throws Exception {
//        Categoria categoriaMae = new Categoria("Tecnologia");
//        categoriaRepository.save(categoriaMae);
//        CategoriaForm categoriaForm = new CategoriaForm("Celular", 1L);
//        mockMvc.perform(post("/categorias")
//                        .content(gson.toJson(categoriaForm))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser
    @DisplayName("Falha no cadastro de Categoria atributo nome duplicado")
    public void falhaNoCadastroCategoriaNomeDuplicado() throws Exception {
        Categoria categoriaMae = new Categoria("Tecnologia");
        categoriaRepository.save(categoriaMae);
        CategoriaForm categoriaForm = new CategoriaForm("Tecnologia", null);
        mockMvc.perform(post("/categorias")
                        .content(gson.toJson(categoriaForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    @DisplayName("Falha no cadastro de Categoria atributo nome")
    public void falhaNoCadastroCategoriaNome() throws Exception {
        CategoriaForm categoriaForm = new CategoriaForm("", null);
        mockMvc.perform(post("/categorias")
                        .content(gson.toJson(categoriaForm))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
