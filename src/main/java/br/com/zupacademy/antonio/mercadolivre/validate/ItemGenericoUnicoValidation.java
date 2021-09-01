package br.com.zupacademy.antonio.mercadolivre.validate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ItemGenericoUnicoValidation implements ConstraintValidator<ItemGenericoUnico, Object> {

    private String domainAttibute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(ItemGenericoUnico params) {
        klass = params.domainClass();
        domainAttibute = params.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = em.createQuery("select 1 from " +klass.getName()+" where " +domainAttibute+ "=:value");
        query.setParameter("value", value.toString());
        List<?> list = query.getResultList();
        return list.isEmpty();
    }
}
