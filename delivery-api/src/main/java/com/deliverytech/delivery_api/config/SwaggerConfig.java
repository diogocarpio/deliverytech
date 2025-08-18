package com.deliverytech.delivery_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .info(new Info()
            .title("Delivery API")  // Título da documentação
            .version("1.0.0") // Versão da API
            .description("Documentação da API Delivery")  // Descrição visível na UI
            .contact(new Contact()
                .name("Equipe Delivery Tech") // Nome do responsável ou equipe
                .email("dev@deliverytech.com") // E-mail de contato
                .url("http://deliverytech.com"))
            .license(new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT")))
        .servers(List.of(
            new Server().url("http://localhost:8080").description("Servidor Local"),
            new Server().url("http://api.deliverytech.com").description("Servidor Produção")
        ));
    }
}
