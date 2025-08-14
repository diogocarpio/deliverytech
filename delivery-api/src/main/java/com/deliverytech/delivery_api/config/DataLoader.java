package com.deliverytech.delivery_api.config;

import com.deliverytech.delivery_api.model.*;
import com.deliverytech.delivery_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== INICIANDO CARGA DE DADOS DE TESTE ===");
    }

    private void inserirClientes(){
        System.out.println("--- Inserindo clientes ---");
        
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João Silva");
        cliente1.setEmail("joao@email.com");
        cliente1.setTelefone("11987654321");
        cliente1.setEndereco("Rua A, 123, São Paulo");
        cliente1.setAtivo(true);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria Santos");
        cliente2.setEmail("maria@email.com");
        cliente2.setTelefone("11998765432");
        cliente2.setEndereco("Avenida B, 456, Rio de Janeiro");
        cliente2.setAtivo(true);

        Cliente cliente3 = new Cliente();
        cliente3.setNome("Pedro Oliveira");
        cliente3.setEmail("pedro@email.com");
        cliente3.setTelefone("11912345678");
        cliente3.setEndereco("Travessa C, 789, Belo Horizonte");
        cliente3.setAtivo(false);

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));
        System.out.println(" 3 clientes inseridos");
    }
    
    private void inserirRestaurantes() {
        System.out.println("--- Inserindo Restaurantes ---");

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Pizza Bella");
        restaurante1.setCategoria("Italiana");
        restaurante1.setEndereco("Av. Principal, 1000");
        restaurante1.setTelefone("1133331111");
        restaurante1.setTaxaEntrega(new BigDecimal("5.00"));
        restaurante1.setAtivo(true);

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Burger House");
        restaurante2.setCategoria("Hamburgueria");
        restaurante2.setEndereco("Rua Augusta, 500");
        restaurante2.setTelefone("1133332222");
        restaurante2.setTaxaEntrega(new BigDecimal("3.50"));
        restaurante2.setAtivo(true);
    
        restauranteRepository.saveAll(Arrays.asList(restaurante1, restaurante2));
        System.out.println(" 2 restaurantes inseridos");
    }

    private void testarConsultas() {
        System.out.println("\n== TESTANDO CONSULTAS DOS REPOSITORIES ==");

        // Teste ClienteRepository
        System.out.println("\nTestes ClienteRepository");
    
        var clientePorEmail = clienteRepository.findByEmail("joao@email.com");
        System.out.println("Cliente por email: " + clientePorEmail.map(Cliente::getNome).orElse("Não encontrado"));
    
        var clientesAtivos = clienteRepository.findByAtivoTrue();
        System.out.println("Clientes ativos: " + clientesAtivos.size());

        var clientesPorNome = clienteRepository.findByNomeContainingIgnoreCase("silva");
        System.out.println("Clientes com 'silva' no nome: " + clientesPorNome.size());

        boolean existeEmail = clienteRepository.existsByEmail("maria@email.com");
        System.out.println("Existe cliente com email: " + existeEmail);
    }

}