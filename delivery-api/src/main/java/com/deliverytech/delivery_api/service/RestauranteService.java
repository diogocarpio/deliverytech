package com.deliverytech.delivery_api.service;

import com.deliverytech.delivery_api.dto.RestauranteDTO;
import java.util.Optional;

public interface RestauranteService {

    // Busca restaurante por ID, retornando Optional
    Optional<RestauranteDTO> buscarPorId(Long id);

    // Salva um novo restaurante
    RestauranteDTO salvar(RestauranteDTO dto);

    // Verifica se já existe restaurante com o mesmo nome
    boolean existePorNome(String nome);
}