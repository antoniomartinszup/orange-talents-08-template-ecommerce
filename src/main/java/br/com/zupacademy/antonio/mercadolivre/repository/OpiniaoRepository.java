package br.com.zupacademy.antonio.mercadolivre.repository;

import br.com.zupacademy.antonio.mercadolivre.model.Opiniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao, Long> {
    List<Opiniao> findByProdutoId(Long id);
}
