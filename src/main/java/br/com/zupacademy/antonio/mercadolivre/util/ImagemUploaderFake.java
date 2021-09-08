package br.com.zupacademy.antonio.mercadolivre.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ImagemUploaderFake {

    /**
     *
     * @param imagens
     * @return links gerados das imagens
     */

    public Set<String> enviando(List<MultipartFile> imagens) {
        return imagens.stream()
                .map(imagem -> "http://bucket.io/"
                +imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
