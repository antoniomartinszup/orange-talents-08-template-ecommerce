package br.com.zupacademy.antonio.mercadolivre.repository;

import br.com.zupacademy.antonio.mercadolivre.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
