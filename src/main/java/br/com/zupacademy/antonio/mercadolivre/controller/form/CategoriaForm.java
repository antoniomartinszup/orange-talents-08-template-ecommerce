package br.com.zupacademy.antonio.mercadolivre.controller.form;

import br.com.zupacademy.antonio.mercadolivre.model.Categoria;
import br.com.zupacademy.antonio.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.antonio.mercadolivre.validate.ItemGenericoUnico;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;


public class CategoriaForm {

    @NotEmpty
    @ItemGenericoUnico(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    private Long idCategoriaMae;

    public CategoriaForm(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria converteParaModelCategoria(CategoriaRepository cRepo) {
        Categoria categoria = new Categoria(this.nome);

        if (this.idCategoriaMae != null) {
            Optional<Categoria> categoriaMae = cRepo.findById(this.idCategoriaMae);
            if (categoriaMae.isEmpty()) {
                throw new IllegalArgumentException();
            }
            categoria.setCategoriaMae(categoriaMae.get());
        }
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }
}
