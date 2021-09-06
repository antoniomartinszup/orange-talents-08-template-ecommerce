package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.dto.ProdutoDetalheDto;
import br.com.zupacademy.antonio.mercadolivre.model.Opiniao;
import br.com.zupacademy.antonio.mercadolivre.model.Pergunta;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import br.com.zupacademy.antonio.mercadolivre.repository.OpiniaoRepository;
import br.com.zupacademy.antonio.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.antonio.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}")
public class ProdutoDetalheController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @GetMapping
    public ResponseEntity<ProdutoDetalheDto> detalhe(@PathVariable Long id) {
        Optional<Produto> produtoId = produtoRepository.findById(id);
        if (produtoId.isPresent()) {
            Produto produto = produtoId.get();

            List<Opiniao> opiniaos = opiniaoRepository.findByProdutoId(id);
            List<Pergunta> perguntas = perguntaRepository.findByProdutoId(id);
            ProdutoDetalheDto produtoDetalheDto = new ProdutoDetalheDto(produto, opiniaos, perguntas);
            return ResponseEntity.ok(produtoDetalheDto);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registro do produto não encontrado");
    }
}
