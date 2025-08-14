package com.deliverytech.delivery_api.config;

import com.deliverytech.delivery_api.model.*;
import com.deliverytech.delivery_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

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

    private void inserirProdutos() {
        System.out.println("--- Inserindo produtos ---");

        // Pizzaria Bella
        Produto p1 = new Produto();
        p1.setNome("Pizza Margherita");
        p1.setDescricao("Molho de tomate, mussarela e manjericão");
        p1.setPreco(new BigDecimal("35.90"));
        p1.setCategoria("Pizza");
        p1.setDisponivel(true);
        p1.setRestaurante(restauranteRepository.findById(1L).orElse(null));

        Produto p2 = new Produto();
        p2.setNome("Pizza Calabresa");
        p2.setDescricao("Molho de tomate, mussarela e calabresa");
        p2.setPreco(new BigDecimal("38.90"));
        p2.setCategoria("Pizza");
        p2.setDisponivel(true);
        p2.setRestaurante(restauranteRepository.findById(1L).orElse(null));

        Produto p3 = new Produto();
        p3.setNome("Lasanha Bolonhesa");
        p3.setDescricao("Lasanha tradicional com molho bolonhesa");
        p3.setPreco(new BigDecimal("28.90"));
        p3.setCategoria("Massa");
        p3.setDisponivel(true);
        p3.setRestaurante(restauranteRepository.findById(1L).orElse(null));

        // Burger House
        Produto p4 = new Produto();
        p4.setNome("X-Burger");
        p4.setDescricao("Hambúrguer, queijo, alface e tomate");
        p4.setPreco(new BigDecimal("18.90"));
        p4.setCategoria("Hambúrguer");
        p4.setDisponivel(true);
        p4.setRestaurante(restauranteRepository.findById(2L).orElse(null));

        Produto p5 = new Produto();
        p5.setNome("X-Bacon");
        p5.setDescricao("Hambúrguer, queijo, bacon, alface e tomate");
        p5.setPreco(new BigDecimal("22.90"));
        p5.setCategoria("Hambúrguer");
        p5.setDisponivel(true);
        p5.setRestaurante(restauranteRepository.findById(2L).orElse(null));

        Produto p6 = new Produto();
        p6.setNome("Batata Frita");
        p6.setDescricao("Porção de batata frita crocante");
        p6.setPreco(new BigDecimal("12.90"));
        p6.setCategoria("Acompanhamento");
        p6.setDisponivel(true);
        p6.setRestaurante(restauranteRepository.findById(2L).orElse(null));

        // Sushi Master
        Produto p7 = new Produto();
        p7.setNome("Combo Sashimi");
        p7.setDescricao("15 peças de sashimi variado");
        p7.setPreco(new BigDecimal("45.90"));
        p7.setCategoria("Sashimi");
        p7.setDisponivel(true);
        p7.setRestaurante(restauranteRepository.findById(3L).orElse(null));

        Produto p8 = new Produto();
        p8.setNome("Hot Roll Salmão");
        p8.setDescricao("8 peças de hot roll de salmão");
        p8.setPreco(new BigDecimal("32.90"));
        p8.setCategoria("Hot Roll");
        p8.setDisponivel(true);
        p8.setRestaurante(restauranteRepository.findById(3L).orElse(null));

        Produto p9 = new Produto();
        p9.setNome("Temaki Atum");
        p9.setDescricao("Temaki de atum com cream cheese");
        p9.setPreco(new BigDecimal("15.90"));
        p9.setCategoria("Temaki");
        p9.setDisponivel(true);
        p9.setRestaurante(restauranteRepository.findById(3L).orElse(null));

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));
        System.out.println(" 9 produtos inseridos");
    }

    private void inserirPedidos() {
        System.out.println("--- Inserindo pedidos ---");

        Pedido ped1 = new Pedido();
        ped1.setNumeroPedido("PED1234567890");
        ped1.setDataPedido(LocalDateTime.now());
        ped1.setStatus("PENDENTE");
        ped1.setValorTotal(new BigDecimal("54.80"));
        ped1.setObservacoes("Sem cebola na pizza");
        ped1.setCliente(clienteRepository.findById(1L).orElse(null));
        ped1.setRestaurante(restauranteRepository.findById(1L).orElse(null));

        Pedido ped2 = new Pedido();
        ped2.setNumeroPedido("PED1234567891");
        ped2.setDataPedido(LocalDateTime.now());
        ped2.setStatus("ENTREGUE");
        ped2.setValorTotal(new BigDecimal("45.90"));
        ped2.setObservacoes(null);
        ped2.setCliente(clienteRepository.findById(2L).orElse(null));
        ped2.setRestaurante(restauranteRepository.findById(2L).orElse(null));

        Pedido ped3 = new Pedido();
        ped3.setNumeroPedido("PED1234567892");
        ped3.setDataPedido(LocalDateTime.now());
        ped3.setStatus("ENTREGUE");
        ped3.setValorTotal(new BigDecimal("78.80"));
        ped3.setObservacoes("wasabi à parte");
        ped3.setCliente(clienteRepository.findById(3L).orElse(null));
        ped3.setRestaurante(restauranteRepository.findById(3L).orElse(null));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3));
        System.out.println(" 3 pedidos inseridos");
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