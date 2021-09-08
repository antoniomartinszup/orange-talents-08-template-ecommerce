package br.com.zupacademy.antonio.mercadolivre.controller;

import br.com.zupacademy.antonio.mercadolivre.controller.form.TransacaoForm;
import br.com.zupacademy.antonio.mercadolivre.model.Compra;
import br.com.zupacademy.antonio.mercadolivre.model.Transacao;
import br.com.zupacademy.antonio.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.antonio.mercadolivre.repository.TransacaoRepository;
import br.com.zupacademy.antonio.mercadolivre.simulaapi.ApiSimulada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ApiSimulada apiSimulada;

    @PostMapping
    @RequestMapping("/retorno-pagseguro/{id}")
    public String TransacaoPagSeguro(@PathVariable("id") Long id, @RequestBody @Valid TransacaoForm transacaoForm) {
        return tipoPagamento(id, transacaoForm);
    }

    @PostMapping
    @RequestMapping("/retorno-paypal/{id}")
    public String TransacaoPayPal(@PathVariable("id") Long id,  @RequestBody @Valid TransacaoForm transacaoForm) {
        return tipoPagamento(id, transacaoForm);
    }

    private String tipoPagamento(Long id, TransacaoForm form) {
        Transacao transacao = form.converteParaModelTransacao(this.compraRepository, id);
        Compra compra = compraRepository.findById(id).get();
        compra.anexaTransacao(transacao, apiSimulada);
        compra = compraRepository.save(compra);
        return compra.toString();
    }
}
