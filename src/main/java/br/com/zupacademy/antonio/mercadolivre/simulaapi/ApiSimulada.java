package br.com.zupacademy.antonio.mercadolivre.simulaapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "simula-api", url = "${feign.client.api-simulada}")
public interface ApiSimulada {

    @PostMapping(value="/notasfiscais")
    String notaFiscal(@RequestBody @Valid CompraNFForm form);

    @PostMapping(value="/rankings")
    String ranking(@RequestBody @Valid CompraRankingForm form);
}
