package com.deliverytech.delivery_api.exception;

// Exceção personalizada para indicar conflito de dados, como duplicidade
// Estende BusinessException para reutilizar mensagem e código padrão
public class ConflictException extends BusinessException {

    // Construtor que recebe o nome da entidade e o campo que gerou o conflito
    public ConflictException(String entity, String field) {
        // Chama o construtor da superclasse com mensagem formatada e código fixo
        super(
            String.format("%s com %s já existe", entity, field), // Mensagem informando o conflito
            "entity.conflict" // Código para identificar esse tipo de erro
        );
    }
}
