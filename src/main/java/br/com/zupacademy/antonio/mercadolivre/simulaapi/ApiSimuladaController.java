package br.com.zupacademy.antonio.mercadolivre.simulaapi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-simulada")
public class ApiSimuladaController {

    @PostMapping("/notasfiscais")
    public String notaFiscal(@RequestBody CompraNFForm compraNFForm) {
        System.out.println(compraNFForm.toString());
        return compraNFForm.toString();
    }

    @PostMapping("/rankings")
    public String ranking(@RequestBody CompraRankingForm compraRankingForm){
        System.out.println(compraRankingForm.toString());
        return compraRankingForm.toString();
    }
}
