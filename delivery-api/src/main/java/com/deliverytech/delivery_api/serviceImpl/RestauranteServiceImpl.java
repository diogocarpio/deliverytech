package com.deliverytech.delivery_api.serviceImpl;

import com.deliverytech.delivery_api.dto.RestauranteDTO;
import com.deliverytech.delivery_api.model.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;
import com.deliverytech.delivery_api.service.RestauranteService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public RestauranteServiceImpl(RestauranteRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<RestauranteDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(restaurante -> modelMapper.map(restaurante, RestauranteDTO.class));
    }

    @Override
    public RestauranteDTO salvar(RestauranteDTO dto) {
        Restaurante restaurante = modelMapper.map(dto, Restaurante.class);
        Restaurante salvo = repository.save(restaurante);
        return modelMapper.map(salvo, RestauranteDTO.class);
    }

    @Override
    public boolean existePorNome(String nome) {
        return repository.existsByNome(nome);
    }
}
