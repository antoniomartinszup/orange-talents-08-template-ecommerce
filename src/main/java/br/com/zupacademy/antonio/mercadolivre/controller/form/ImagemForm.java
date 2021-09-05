package br.com.zupacademy.antonio.mercadolivre.controller.form;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemForm {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens = new ArrayList<>();
    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }
    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
