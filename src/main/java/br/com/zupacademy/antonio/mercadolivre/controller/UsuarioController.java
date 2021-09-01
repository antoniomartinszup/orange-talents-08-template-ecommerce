package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.dto.UsuarioDto;
import br.com.zupacademy.antonio.mercadolivre.controller.form.UsuarioForm;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import br.com.zupacademy.antonio.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> salvar(@RequestBody @Valid UsuarioForm usuarioForm) {
        Usuario usuarioSalvo = usuarioRepository.save(usuarioForm.converteParaModelUsuario());
        return ResponseEntity.ok().body(UsuarioDto.converteParaUsuarioDto(usuarioSalvo));
    }
}
