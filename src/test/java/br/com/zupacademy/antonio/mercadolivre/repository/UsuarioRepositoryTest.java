package br.com.zupacademy.antonio.mercadolivre.repository;

import br.com.zupacademy.antonio.mercadolivre.controller.form.UsuarioForm;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;
import br.com.zupacademy.antonio.mercadolivre.security.SenhaLimpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("Teste Usuario Repository")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Salva novo Usuario com sucesso")
    void salva() {
        Usuario usuario = criaUsuario();
        Usuario usuarioSalvo = this.usuarioRepository.save(usuario);

        Assertions.assertEquals(usuario, usuarioSalvo);
        assertThat(usuarioSalvo).isEqualTo(usuario);

        Assertions.assertNotNull(usuarioSalvo);
        assertThat(usuarioSalvo).isNotNull();

        Usuario usurarioFake = criaUsuarioFake();
        Usuario usuarioSalvoFake = this.usuarioRepository.save(usurarioFake);

        Assertions.assertNotEquals(usuario, usuarioSalvoFake);
        assertThat(usuario).isNotEqualTo(usuarioSalvoFake);
    }

    @Test
    @DisplayName("Busca Email com sucesso")
    void porEmail() {

        Usuario usuario = criaUsuario();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(usuario.getLogin());

        assertThat(usuarioOptional).isNotNull();
        assertThat(usuario).isNotEqualTo(usuarioOptional);
    }

    @Test
    @DisplayName("Exceção ConstraintViolationException não pode ser vazio")
    void naoPodeSerVazio() {

        Usuario usuario = new Usuario();

        assertThatThrownBy(() -> this.usuarioRepository.save(usuario))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Busca Email com falha")
    void porEmailFalha() {

        Usuario usuario = criaUsuarioSemEmail();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(usuario.getLogin());

        assertThat(usuarioOptional).isEmpty();
        assertThat(usuario).isNotEqualTo(usuarioOptional);
    }

    private Usuario criaUsuario() {
        SenhaLimpa senhaLimpa = new SenhaLimpa("123456");
        return new Usuario("antonio@gmail.com", senhaLimpa);
    }

    private Usuario criaUsuarioFake() {
        SenhaLimpa senhaLimpa = new SenhaLimpa("123456");
        return new Usuario("zepa@gmail.com", senhaLimpa);
    }

    private Usuario criaUsuarioSemEmail() {
        SenhaLimpa senhaLimpa = new SenhaLimpa("123456");
        return new Usuario(null, senhaLimpa);
    }
}