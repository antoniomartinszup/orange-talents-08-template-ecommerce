package br.com.zupacademy.antonio.mercadolivre.controller.dto;

import br.com.zupacademy.antonio.mercadolivre.model.Imagem;
import br.com.zupacademy.antonio.mercadolivre.model.Opiniao;
import br.com.zupacademy.antonio.mercadolivre.model.Pergunta;
import br.com.zupacademy.antonio.mercadolivre.model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDetalheDto {

    private List<String> linksImagens = new ArrayList<>();
    private String produto;
    private BigDecimal valor;
    private List<CaracteristicaDto> caracteristicas;
    private String descricao;
    private Double mediaNotas;
    private Integer notaTotal;
    private List<OpiniaoDto> opiniaoProduto = new ArrayList<>();
    private List<PerguntaDto> perguntaDto = new ArrayList<>();

    public ProdutoDetalheDto(Produto produto, List<Opiniao> opinioes, List<Pergunta> perguntas) {

        this.produto = produto.getNome();
        this.valor = produto.getValor();
        this.caracteristicas = CaracteristicaDto.converteParaCaracteristicaDto(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        this.mediaNotas = calculaMedia(opinioes);
        this.notaTotal = notaTotal(opinioes);
        for (Opiniao opiniao : opinioes) {
            this.opiniaoProduto.add(new OpiniaoDto(opiniao));
        }
        for (Pergunta pergunta : perguntas) {
            this.perguntaDto.add(new PerguntaDto(pergunta));
        }
        for (Imagem imagem : produto.getImagens()) {
            this.linksImagens.add(imagem.getLink());
        }
    }

    private Integer notaTotal(List<Opiniao> opiniaos) {
        Integer notaTotal = 0;
        for (Opiniao opiniao : opiniaos) {
            notaTotal += opiniao.getNota();
        }
        return notaTotal;
    }

    public Double calculaMedia(List<Opiniao> opiniaos) {
        Double media = 0.0;
        for (Opiniao opiniao : opiniaos) {
            media+= opiniao.getNota();
        }
        return media /= opiniaos.size();
    }

    public List<String> getLinksImagens() {
        return linksImagens;
    }

    public String getProduto() {
        return produto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public List<CaracteristicaDto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getNotaTotal() {
        return notaTotal;
    }

    public List<OpiniaoDto> getOpiniaoProduto() {
        return opiniaoProduto;
    }

    public List<PerguntaDto> getPerguntaDto() {
        return perguntaDto;
    }
}
