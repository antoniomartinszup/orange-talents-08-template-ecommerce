package br.com.zupacademy.antonio.mercadolivre.simulaapi;

import javax.validation.constraints.NotNull;

public class CompraNFForm {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idComprador;

    public CompraNFForm(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }

    @Override
    public String toString() {
        return "NovaCompraNFRequest [idCompra=" + idCompra + ", idComprador="
                + idComprador + "]";
    }
}
