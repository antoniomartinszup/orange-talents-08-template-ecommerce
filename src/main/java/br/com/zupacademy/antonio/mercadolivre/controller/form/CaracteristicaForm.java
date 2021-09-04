package br.com.zupacademy.antonio.mercadolivre.controller.form;

import br.com.zupacademy.antonio.mercadolivre.model.Caracteristica;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class CaracteristicaForm {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String descricao;

    public CaracteristicaForm(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica converteParaModelCaracteristica(Produto produto) {
        return new Caracteristica(this.nome, this.descricao, produto);
    }

    public static List<Caracteristica> converteParaModelCaracteristicaList(
            List<CaracteristicaForm> caracteristicaForm, Produto produto) {

        List<Caracteristica> caracteristicas = new ArrayList<>();
        for (CaracteristicaForm cForm : caracteristicaForm) {
            Caracteristica caracteristica = cForm.converteParaModelCaracteristica(produto);
            caracteristicas.add(caracteristica);
        }
        return caracteristicas;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
