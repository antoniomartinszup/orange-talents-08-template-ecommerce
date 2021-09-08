package br.com.zupacademy.antonio.mercadolivre.model;

import br.com.zupacademy.antonio.mercadolivre.util.AndamentoCompra;
import br.com.zupacademy.antonio.mercadolivre.util.GatewayPagamento;
import br.com.zupacademy.antonio.mercadolivre.util.StatusTransacao;
import br.com.zupacademy.antonio.mercadolivre.simulaapi.ApiSimulada;
import br.com.zupacademy.antonio.mercadolivre.simulaapi.CompraNFForm;
import br.com.zupacademy.antonio.mercadolivre.simulaapi.CompraRankingForm;
import br.com.zupacademy.antonio.mercadolivre.util.EncaminhaEmails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private List<Transacao> transacoes = new ArrayList<>();

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

    public void anexaTransacao(Transacao transacao, ApiSimulada apiSimulada) {
        if (this.transacoes.contains(transacao)) {
            throw new IllegalArgumentException("Transação já feita.");
        }

        List<Transacao> transacaoList = this.transacoes.stream().
                filter(transacao::transacaoSucesso).collect(Collectors.toList());

        if (!(transacaoList.size() < 2)) throw new IllegalArgumentException(
                "Número de transações máximas para esta compra");

        conectaApiSimulada(transacao, apiSimulada);
        this.transacoes.add(transacao);
    }

    private void conectaApiSimulada(Transacao transacao, ApiSimulada apiSimulada) {
        if (transacao.getStatus().equals(StatusTransacao.SUCESSO)) {
            apiSimulada.notaFiscal(new CompraNFForm(this.id, this.usuario.getId()));
            apiSimulada.ranking(new CompraRankingForm(this.id, this.produto.getUsuario().getId()));
            EncaminhaEmails.compraRealizada(this);
        }
        else {
            EncaminhaEmails.pagamentoRecusado(this);
        }
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

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", usuario=" + usuario +
                ", valor=" + valor +
                ", gateway=" + gateway +
                ", transacoes=" + transacoes +
                '}';
    }
}
