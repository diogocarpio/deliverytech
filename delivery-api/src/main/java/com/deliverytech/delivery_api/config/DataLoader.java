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

    private void inserirClientes() {
        System.out.println("--- Inserindo clientes ---");

        Cliente cliente1 = new Cliente();
        cliente1.setNome("João Silva");
        cliente1.setEmail("joao@email.com");
        cliente1.setTelefone("(11) 99999-1111");
        cliente1.setEndereco("Rua A, 123 - São Paulo/SP");
        cliente1.setAtivo(true);
        cliente1.setDataCadastro(LocalDateTime.now());

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria Santos");
        cliente2.setEmail("maria@email.com");
        cliente2.setTelefone("(11) 99999-2222");
        cliente2.setEndereco("Rua B, 456 - São Paulo/SP");
        cliente2.setAtivo(true);
        cliente2.setDataCadastro(LocalDateTime.now());

        Cliente cliente3 = new Cliente();
        cliente3.setNome("Pedro Oliveira");
        cliente3.setEmail("pedro@email.com");
        cliente3.setTelefone("(11) 99999-3333");
        cliente3.setEndereco("Rua C, 789 - São Paulo/SP");
        cliente3.setAtivo(true);
        cliente3.setDataCadastro(LocalDateTime.now());

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));
        System.out.println(" 3 clientes inseridos");
    }
    
    private void inserirRestaurantes() {
        System.out.println("--- Inserindo Restaurantes ---");

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Pizzaria Bella");
        restaurante1.setCategoria("Italiana");
        restaurante1.setEndereco("Av. Paulista, 1000 - São Paulo/SP");
        restaurante1.setTelefone("(11) 3333-1111");
        restaurante1.setTaxaEntrega(new BigDecimal("5.00"));
        restaurante1.setAtivo(true);
        restaurante1.setAvaliacao(new BigDecimal("4.5"));

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Burger House");
        restaurante2.setCategoria("Hamburgueria");
        restaurante2.setEndereco("Rua Augusta, 500 - São Paulo/SP");
        restaurante2.setTelefone("(11) 3333-2222");
        restaurante2.setTaxaEntrega(new BigDecimal("3.50"));
        restaurante2.setAtivo(true);
        restaurante2.setAvaliacao(new BigDecimal("4.2"));

        Restaurante restaurante3 = new Restaurante();
        restaurante3.setNome("Sushi Master");
        restaurante3.setCategoria("Japonesa");
        restaurante3.setEndereco("Rua Liberdade, 200 - São Paulo/SP");
        restaurante3.setTelefone("(11) 3333-3333");
        restaurante3.setTaxaEntrega(new BigDecimal("8.00"));
        restaurante3.setAtivo(true);
        restaurante3.setAvaliacao(new BigDecimal("4.8"));

        restauranteRepository.saveAll(Arrays.asList(restaurante1, restaurante2, restaurante3));
        System.out.println(" 3 restaurantes inseridos");
    }

    private void inserirProdutos() {
        System.out.println("--- Inserindo produtos ---");

        // Pizzaria Bella
        Produto produto1 = new Produto();
        produto1.setNome("Pizza Margherita");
        produto1.setDescricao("Molho de tomate, mussarela e manjericão");
        produto1.setPreco(new BigDecimal("35.90"));
        produto1.setCategoria("Pizza");
        produto1.setDisponivel(true);
        produto1.setRestaurante(restauranteRepository.findById(1L).orElse(null));

        Produto produto2 = new Produto();
        produto2.setNome("Pizza Calabresa");
        produto2.setDescricao("Molho de tomate, mussarela e calabresa");
        produto2.setPreco(new BigDecimal("38.90"));
        produto2.setCategoria("Pizza");
        produto2.setDisponivel(true);
        produto2.setRestaurante(restauranteRepository.findById(1L).orElse(null));

        Produto produto3 = new Produto();
        produto3.setNome("Lasanha Bolonhesa");
        produto3.setDescricao("Lasanha tradicional com molho bolonhesa");
        produto3.setPreco(new BigDecimal("28.90"));
        produto3.setCategoria("Massa");
        produto3.setDisponivel(true);
        produto3.setRestaurante(restauranteRepository.findById(1L).orElse(null));

        // Burger House
        Produto produto4 = new Produto();
        produto4.setNome("X-Burger");
        produto4.setDescricao("Hambúrguer, queijo, alface e tomate");
        produto4.setPreco(new BigDecimal("18.90"));
        produto4.setCategoria("Hambúrguer");
        produto4.setDisponivel(true);
        produto4.setRestaurante(restauranteRepository.findById(2L).orElse(null));

        Produto produto5 = new Produto();
        produto5.setNome("X-Bacon");
        produto5.setDescricao("Hambúrguer, queijo, bacon, alface e tomate");
        produto5.setPreco(new BigDecimal("22.90"));
        produto5.setCategoria("Hambúrguer");
        produto5.setDisponivel(true);
        produto5.setRestaurante(restauranteRepository.findById(2L).orElse(null));

        Produto produto6 = new Produto();
        produto6.setNome("Batata Frita");
        produto6.setDescricao("Porção de batata frita crocante");
        produto6.setPreco(new BigDecimal("12.90"));
        produto6.setCategoria("Acompanhamento");
        produto6.setDisponivel(true);
        produto6.setRestaurante(restauranteRepository.findById(2L).orElse(null));

        // Sushi Master
        Produto produto7 = new Produto();
        produto7.setNome("Combo Sashimi");
        produto7.setDescricao("15 peças de sashimi variado");
        produto7.setPreco(new BigDecimal("45.90"));
        produto7.setCategoria("Sashimi");
        produto7.setDisponivel(true);
        produto7.setRestaurante(restauranteRepository.findById(3L).orElse(null));

        Produto produto8 = new Produto();
        produto8.setNome("Hot Roll Salmão");
        produto8.setDescricao("8 peças de hot roll de salmão");
        produto8.setPreco(new BigDecimal("32.90"));
        produto8.setCategoria("Hot Roll");
        produto8.setDisponivel(true);
        produto8.setRestaurante(restauranteRepository.findById(3L).orElse(null));

        Produto produto9 = new Produto();
        produto9.setNome("Temaki Atum");
        produto9.setDescricao("Temaki de atum com cream cheese");
        produto9.setPreco(new BigDecimal("15.90"));
        produto9.setCategoria("Temaki");
        produto9.setDisponivel(true);
        produto9.setRestaurante(restauranteRepository.findById(3L).orElse(null));

        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8, produto9));
        System.out.println(" 9 produtos inseridos");
    }

    private void inserirPedidos() {
        System.out.println("--- Inserindo pedidos ---");

        Pedido ped1 = new Pedido();
        ped1.setNumeroPedido("PED1234567890");
        ped1.setDataPedido(LocalDateTime.now());
        ped1.setStatus(StatusPedido.PENDENTE);
        ped1.setValorTotal(new BigDecimal("54.80"));
        ped1.setObservacoes("Sem cebola na pizza");
        ped1.setCliente(clienteRepository.findById(1L).orElse(null));
        ped1.setRestaurante(restauranteRepository.findById(1L).orElse(null));

        Pedido ped2 = new Pedido();
        ped2.setNumeroPedido("PED1234567891");
        ped2.setDataPedido(LocalDateTime.now());
        ped2.setStatus(StatusPedido.ENTREGUE);
        ped2.setValorTotal(new BigDecimal("45.90"));
        ped2.setObservacoes(null);
        ped2.setCliente(clienteRepository.findById(2L).orElse(null));
        ped2.setRestaurante(restauranteRepository.findById(2L).orElse(null));

        Pedido ped3 = new Pedido();
        ped3.setNumeroPedido("PED1234567892");
        ped3.setDataPedido(LocalDateTime.now());
        ped3.setStatus(StatusPedido.ENTREGUE);
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