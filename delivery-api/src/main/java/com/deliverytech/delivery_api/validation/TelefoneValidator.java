package com.deliverytech.delivery_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Classe que contém a lógica para validar a anotação @ValidTelefone
public class TelefoneValidator implements ConstraintValidator<ValidTelefone, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Permitir valor nulo ou vazio -> esses campos com @NotBlank devem validar obrigatoriamente
        if (value == null || value.isEmpty()) {
            return true;
        }

        // Aceita os formatos:
        // (99) 99999-9999 ou 11999999999
        return value.matches("\\(\\d{2}\\)\\s?9\\d{4}-\\d{4}") || value.matches("\\d{11}");
    }
}

