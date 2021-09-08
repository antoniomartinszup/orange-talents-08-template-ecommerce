package br.com.zupacademy.antonio.mercadolivre.controller.form;

import br.com.zupacademy.antonio.mercadolivre.util.StatusTransacao;
import br.com.zupacademy.antonio.mercadolivre.model.Compra;
import br.com.zupacademy.antonio.mercadolivre.model.Transacao;
import br.com.zupacademy.antonio.mercadolivre.repository.CompraRepository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class TransacaoForm {

    @NotBlank
    private String codigoGateway;

    @NotBlank
    private String statusTransacao;

    public Transacao converteParaModelTransacao(CompraRepository cRepo, Long id) {
        Optional<Compra> compra = cRepo.findById(id);
        if (compra.isEmpty()) throw new IllegalArgumentException("Não existe uma compra com esse id.");
        return new Transacao(StatusTransacao.criaStatus(this.statusTransacao.toUpperCase()), this.codigoGateway, compra.get());
    }

    public String getCodigoGateway() {
        return codigoGateway;
    }

    public String getStatusTransacao() {
        return statusTransacao;
    }
}
