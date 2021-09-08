package br.com.zupacademy.antonio.mercadolivre.util;

public enum StatusTransacao {
    SUCESSO, ERRO;

    public static StatusTransacao criaStatus(String value) {
        switch (value) {
            case "SUCESSO":
            case "1":
                return StatusTransacao.SUCESSO;
            case "ERRO":
            case "0":
                return StatusTransacao.ERRO;
        }
        throw new IllegalArgumentException("Você precisa passar um status válido");
    }
}
