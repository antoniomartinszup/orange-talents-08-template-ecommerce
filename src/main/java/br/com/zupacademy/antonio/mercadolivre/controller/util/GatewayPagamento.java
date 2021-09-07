package br.com.zupacademy.antonio.mercadolivre.controller.util;

import br.com.zupacademy.antonio.mercadolivre.model.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    pagseguro {
        @Override
        public String direcionandoGateway(UriComponentsBuilder uriComponentsBuilder, Compra compra) {
            String retornoUri = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toUriString();
            return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + retornoUri;
        }
    },
    paypal {
        @Override
        public String direcionandoGateway(UriComponentsBuilder uriComponentsBuilder, Compra compra) {
            String retornoUri = uriComponentsBuilder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toUriString();
            return "paypal.com/" + compra.getId() + "?redirectUrl=" + retornoUri;
        }
    };
    public abstract String direcionandoGateway(UriComponentsBuilder uriComponentsBuilder, Compra compra);

}
