package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.form.CompraForm;
import br.com.zupacademy.antonio.mercadolivre.model.Compra;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import br.com.zupacademy.antonio.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.antonio.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> salva(@Valid @RequestBody CompraForm compraForm,
                                           @AuthenticationPrincipal Usuario usuario,
                                           UriComponentsBuilder uriComponentsBuilder) {

        Optional<Produto> produtoId = produtoRepository.findById(compraForm.getIdProduto());
        if (produtoId.isPresent()) {
            Produto produto = produtoId.get();
            Boolean diminuiEstoque = produto.reduzEstoque(compraForm.getQuantidade());
            if (diminuiEstoque) {
                Compra compra = compraForm.converteParaModelCompra(produto, usuario);
                compraRepository.save(compra);
                System.out.println("Enviar email ao vendedor: " + compra.getUsuario().getUsername());

                String url = compra.getGateway().direcionandoGateway(uriComponentsBuilder, compra);
                return ResponseEntity.status(HttpStatus.MOVED_TEMPORARILY).body(url);
            }
            return ResponseEntity.badRequest().body("Não tem estoque para o produto escolhido");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registro do produto não encontrado");
    }
}
