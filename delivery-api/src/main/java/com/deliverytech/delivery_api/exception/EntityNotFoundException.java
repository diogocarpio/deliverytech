package com.deliverytech.delivery_api.exception;

// Exceção personalizada para indicar que uma entidade não foi encontrada no banco de dados
// Estende BusinessException para aproveitar a estrutura de mensagens e códigos
public class EntityNotFoundException extends BusinessException {

    // Construtor que recebe o nome da entidade e o identificador pesquisado
    public EntityNotFoundException(String entity, Object id) {
        // Chama o construtor da superclasse com mensagem formatada e código fixo
        super(
            String.format("%s com ID %s não encontrado", entity, id), // Mensagem descritiva
            "entity.not.found" // Código do erro para identificar a situação
        );
    }
}

