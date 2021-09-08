package br.com.zupacademy.antonio.mercadolivre.util;

import br.com.zupacademy.antonio.mercadolivre.model.Compra;
import br.com.zupacademy.antonio.mercadolivre.model.Usuario;

public class EncaminhaEmails {

    public static void perguntaNova(Usuario usuarioDono, Usuario usuarioPergunta) {
        System.out.println("Oi, " + usuarioDono.getLogin() + ". O usuário " + usuarioPergunta.getLogin() + " mandou uma pergunta no produto anúncio");
    }

    public static void compraNova(Usuario usuarioDono, Usuario usuarioPergunta, Compra compra) {
        System.out.println("Oi, " + usuarioDono.getLogin() + ". O usuário " + usuarioPergunta.getLogin() + " gostaria de adiquirir " +
                compra.getQuantidade() + " peças do produto " + compra.getProduto().getNome());
    }

    public static void compraRealizada(Compra compra) {
        System.out.println("Oi, " + compra.getUsuario().getLogin() + ". Compra realizada com sucesso com " +
                compra.getQuantidade() + " do produto comprado " + compra.getProduto().getNome());
    }

    public static void pagamentoRecusado(Compra compra) {
        String path = compra.getGateway().equals(GatewayEnum.PAYPAL) ? "retono-paypal" : "retorno-pagseguro";

        System.out.println("Oi, " + compra.getUsuario().getLogin() + ". Compra não realizada com " +
                compra.getQuantidade() + " do produto selecionado " + compra.getProduto().getNome() + " não efetuado. " +
                "Tentar pagar novamente no seguinte link: http://localhost:8080/" + path +"/" + compra.getId());
    }
}
