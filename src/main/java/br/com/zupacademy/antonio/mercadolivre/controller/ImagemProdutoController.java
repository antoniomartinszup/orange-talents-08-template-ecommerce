package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.uploader.ImagemUploaderFake;
import br.com.zupacademy.antonio.mercadolivre.controller.dto.ProdutoDto;
import br.com.zupacademy.antonio.mercadolivre.controller.form.ImagemForm;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
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
import java.util.Set;

@RestController
@RequestMapping("/produtos/{id}/imagens")
public class ImagemProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImagemUploaderFake imagemUploaderFake;

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> anexaImagem(@Valid ImagemForm imagemForm, @PathVariable Long id,
                                                  @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> produtoId = produtoRepository.findById(id);
        if (produtoId.isPresent()) {
            Produto produto = produtoId.get();
            if (produto.pertenceAoUsuario(usuario.getId())) {
                Set<String> links = imagemUploaderFake.enviando(imagemForm.getImagens());
                produto.anexaImagens(links);
                return ResponseEntity.ok(new ProdutoDto(produto));
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Produto não é do usuario logado");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registro do produto não encontrado");
    }
}
