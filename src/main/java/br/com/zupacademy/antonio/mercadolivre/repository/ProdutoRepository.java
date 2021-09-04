package br.com.zupacademy.antonio.mercadolivre.repository;

import br.com.zupacademy.antonio.mercadolivre.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
