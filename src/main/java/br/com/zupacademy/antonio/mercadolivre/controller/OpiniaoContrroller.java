package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.dto.OpiniaoDto;
import br.com.zupacademy.antonio.mercadolivre.controller.form.Opiniaoform;
import br.com.zupacademy.antonio.mercadolivre.model.Opiniao;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import br.com.zupacademy.antonio.mercadolivre.repository.OpiniaoRepository;
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
@RequestMapping("/opinioes/produtos/{id}")
public class OpiniaoContrroller {

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<OpiniaoDto> salvar(@Valid @RequestBody Opiniaoform opiniaoform, @PathVariable Long id,
                                             @AuthenticationPrincipal Usuario usuario) {

        Optional<Produto> produtoId = produtoRepository.findById(id);
        if (produtoId.isPresent()) {
            Produto produto = produtoId.get();
                Opiniao opiniaoSalvo = opiniaoRepository.save(opiniaoform.converteParaModelOpiniao(usuario, produto));
                return ResponseEntity.ok().body(new OpiniaoDto(opiniaoSalvo));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registro do produto não encontrado");
    }
}
