package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.app.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidatorService implements com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.app.ValidatorService {

    private final Validator validator;

    @Override
    public void validator(Object request) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

}
