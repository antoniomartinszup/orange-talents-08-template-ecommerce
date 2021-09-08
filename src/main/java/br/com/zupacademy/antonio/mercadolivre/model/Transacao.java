package br.com.zupacademy.antonio.mercadolivre.model;

import br.com.zupacademy.antonio.mercadolivre.util.StatusTransacao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private StatusTransacao status;

    private @NotBlank String idTransacaoGateway;

    @NotNull
    private LocalDateTime instante;

    @ManyToOne
    private @NotNull @Valid Compra compra;

    @Deprecated
    public Transacao() {
    }

    public Transacao(StatusTransacao status, String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    public boolean transacaoSucesso(Transacao transacao) {
        return this.status.equals(StatusTransacao.SUCESSO);
    }

    public Long getId() {
        return id;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public String getIdTransacaoGateway() {
        return idTransacaoGateway;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Compra getCompra() {
        return compra;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacaoGateway='" + idTransacaoGateway + '\'' +
                ", instante=" + instante +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return Objects.equals(idTransacaoGateway, transacao.idTransacaoGateway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacaoGateway);
    }
}
