package com.zup.proposta.validator;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private String attribute;
    private Class<?> clazz;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue params) {
        this.attribute = params.attribute();
        this.clazz = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Query query = entityManager.createQuery("SELECT 1 FROM " + clazz.getName() + " WHERE " + attribute + "=:value");
        query.setParameter("value", value);

        List<?> list = query.getResultList();

        if (!list.isEmpty()) {
            context.disableDefaultConstraintViolation();
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "An " + clazz.getSimpleName().toLowerCase() + " was " +
                    "found with the " + attribute + " attribute " + value.toString() + " already registered.");
        }

        return true;
    }
}
