package br.com.zupacademy.antonio.mercadolivre.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ItemGenericoUnicoValidation.class)
@Target( { FIELD })
@Retention(RUNTIME)
public @interface ItemGenericoUnico {

    Class<?> domainClass();
    String fieldName();

    String message() default "Item já cadastrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
