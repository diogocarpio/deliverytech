package com.deliverytech.delivery_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// Torna a anotação visível na documentação
@Documented

// Define a classe validadora associada
@Constraint(validatedBy = TelefoneValidator.class)

// Permite o uso da anotação apenas em campos
@Target({FIELD})

// Define que a anotação estará disponível em tempo de execução
@Retention(RUNTIME)
public @interface ValidTelefone {

    // Mensagem padrão em caso de erro
    String message() default "Telefone inválido";

    // Grupos de validação (recurso avançado, geralmente não utilizado)
    Class<?>[] groups() default {};

    // Payload adicional (também pouco usado)
    Class<? extends Payload>[] payload() default {};
}
