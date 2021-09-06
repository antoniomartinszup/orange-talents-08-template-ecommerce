package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.dto.PerguntaDto;
import br.com.zupacademy.antonio.mercadolivre.controller.form.PerguntaForm;
import br.com.zupacademy.antonio.mercadolivre.model.Pergunta;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import br.com.zupacademy.antonio.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.antonio.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/perguntas/produtos/{id}")
public class PerguntaController {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PerguntaDto> salvar(@Valid @RequestBody PerguntaForm perguntaForm, @PathVariable Long id,
                                              @AuthenticationPrincipal Usuario usuario) {

        Optional<Produto> produtoId = produtoRepository.findById(id);
        if (produtoId.isPresent()) {
            Produto produto = produtoId.get();
            Pergunta perguntaSalvo = perguntaRepository.save(perguntaForm.converteParaModelPergunta(usuario, produto));
            System.out.println("Enviar email para: " + perguntaSalvo.getProduto().getUsuario().getLogin());
            return ResponseEntity.ok().body(new PerguntaDto(perguntaSalvo));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registro do produto não encontrado");
    }
}
