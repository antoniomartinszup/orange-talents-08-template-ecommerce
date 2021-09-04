package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.dto.ProdutoDto;
import br.com.zupacademy.antonio.mercadolivre.controller.form.ProdutoForm;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import br.com.zupacademy.antonio.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.antonio.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.antonio.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> salvar(@RequestBody @Valid ProdutoForm produtoForm,
                                             @AuthenticationPrincipal Usuario usuario) {
        Produto produtoSalvo = produtoForm.converteParaModelProduto(categoriaRepository, usuarioRepository, usuario);
        produtoRepository.save(produtoSalvo);
        return ResponseEntity.ok().body(new ProdutoDto(produtoSalvo));
    }
}
