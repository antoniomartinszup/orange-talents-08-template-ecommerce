package br.com.zupacademy.antonio.mercadolivre.controller.dto;

import br.com.zupacademy.antonio.mercadolivre.model.Caracteristica;

import java.util.ArrayList;
import java.util.List;

public class CaracteristicaDto {

    private Long id;
    private String nome;
    private String descricao;

    public CaracteristicaDto(Caracteristica caracteristica) {
        this.id = caracteristica.getId();
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public static List<CaracteristicaDto> converteParaCaracteristicaDto(List<Caracteristica> caracteristicas) {

        List<CaracteristicaDto> caracteristicasDto = new ArrayList<>();
        for (Caracteristica cDto : caracteristicas) {
            CaracteristicaDto caracteristicaDto = new CaracteristicaDto(cDto);
            caracteristicasDto.add(caracteristicaDto);
        }
        return caracteristicasDto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
