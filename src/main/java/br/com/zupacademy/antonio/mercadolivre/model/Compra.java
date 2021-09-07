package br.com.zupacademy.antonio.mercadolivre.model;

import br.com.zupacademy.antonio.mercadolivre.controller.util.AndamentoCompra;
import br.com.zupacademy.antonio.mercadolivre.controller.util.GatewayPagamento;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;
    private Integer quantidade;

    @ManyToOne
    private Usuario usuario;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private AndamentoCompra andamentoCompra = AndamentoCompra.INICIADA;

    @Enumerated(EnumType.STRING)
    private GatewayPagamento gateway;

    @Deprecated
    public Compra() {
    }

    public Compra(Produto produto, Integer quantidade, Usuario usuario, GatewayPagamento gateway) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuario = usuario;
        this.gateway = gateway;
        this.valor = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public AndamentoCompra getAndamentoCompra() {
        return andamentoCompra;
    }
}
