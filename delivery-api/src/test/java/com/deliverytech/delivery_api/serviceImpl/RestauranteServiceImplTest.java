package com.deliverytech.delivery_api.serviceImpl;

import com.deliverytech.delivery_api.dto.RestauranteDTO;
import com.deliverytech.delivery_api.model.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteServiceImplTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RestauranteServiceImpl service;

    private Restaurante restaurante;
    private RestauranteDTO restauranteDTO;

    @BeforeEach
    void setup() {
        restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Restaurante Teste");

        restauranteDTO = new RestauranteDTO();
        restauranteDTO.setId(1L);
        restauranteDTO.setNome("Restaurante Teste");
    }

    @Test
    void deveBuscarRestaurantePorIdComSucesso() {
        when(repository.findById(1L)).thenReturn(Optional.of(restaurante));
        when(modelMapper.map(restaurante, RestauranteDTO.class)).thenReturn(restauranteDTO);

        Optional<RestauranteDTO> result = service.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(restauranteDTO.getId(), result.get().getId());
    }

    @Test
    void deveRetornarVazioQuandoRestauranteNaoEncontrado() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Optional<RestauranteDTO> result = service.buscarPorId(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void deveSalvarRestauranteComSucesso() {
        when(modelMapper.map(restauranteDTO, Restaurante.class)).thenReturn(restaurante);
        when(repository.save(restaurante)).thenReturn(restaurante);
        when(modelMapper.map(restaurante, RestauranteDTO.class)).thenReturn(restauranteDTO);

        RestauranteDTO result = service.salvar(restauranteDTO);

        assertNotNull(result);
        assertEquals(restauranteDTO.getNome(), result.getNome());
        verify(repository).save(restaurante);
    }

    @Test
    void deveVerificarSeRestauranteExistePorNome() {
        when(repository.existsByNome("Restaurante Teste")).thenReturn(true);

        boolean exists = service.existePorNome("Restaurante Teste");

        assertTrue(exists);
        verify(repository).existsByNome("Restaurante Teste");
    }

    @Test
    void deveRetornarFalseQuandoRestauranteNaoExistePorNome() {
        when(repository.existsByNome("Outro Restaurante")).thenReturn(false);

        boolean exists = service.existePorNome("Outro Restaurante");

        assertFalse(exists);
    }
}
