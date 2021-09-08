package br.com.zupacademy.antonio.mercadolivre.simulaapi;

import javax.validation.constraints.NotNull;

public class CompraRankingForm {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idDonoProduto;

    public CompraRankingForm(Long idCompra, Long idDonoProduto) {
        this.idCompra = idCompra;
        this.idDonoProduto = idDonoProduto;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdDonoProduto() {
        return idDonoProduto;
    }

    @Override
    public String toString() {
        return "NovaCompraNFRequest [idCompra=" + idCompra + ", idComprador="
                + idDonoProduto + "]";
    }
}
