package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.dto.CategoriaDto;
import br.com.zupacademy.antonio.mercadolivre.controller.form.CategoriaForm;
import br.com.zupacademy.antonio.mercadolivre.model.Categoria;
import br.com.zupacademy.antonio.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> salvar(@RequestBody @Valid CategoriaForm categoriaForm) {
        Categoria categoriaSalvo = categoriaRepository.save(categoriaForm.converteParaModelCategoria(categoriaRepository));
        return ResponseEntity.ok().body(new CategoriaDto(categoriaSalvo));
    }
}
