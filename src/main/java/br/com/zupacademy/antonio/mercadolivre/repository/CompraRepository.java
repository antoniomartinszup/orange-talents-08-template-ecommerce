package br.com.zupacademy.antonio.mercadolivre.repository;

import br.com.zupacademy.antonio.mercadolivre.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
