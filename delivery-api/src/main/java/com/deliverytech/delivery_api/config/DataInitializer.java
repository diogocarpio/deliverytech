package com.deliverytech.delivery_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.deliverytech.delivery_api.model.User;
import com.deliverytech.delivery_api.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
   
    @Override
    public void run(String... args) throws Exception {
       // Verifica se já existe um usuário admin
       if (!userRepository.existsByEmail("admin@delivery.com")) {
            // Cria um usuário admin com senha codificada
            User user = User.builder()
                .email("admin@delivery.com")
                .senha(passwordEncoder.encode("123"))
                .nome("Administrador")
                .build();
               
            userRepository.save(user);
            System.out.println("Usuário admin criado com sucesso!");

            // --- Início da autenticação do admin ---
            // 1. Crie o token de autenticação
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken("admin@delivery.com", "123");

            // 2. Autentique o token
            Authentication authenticatedUser = authenticationManager.authenticate(authenticationToken);

            // 3. Defina o contexto de segurança (opcional, mas útil para testes)
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

            System.out.println("Admin autenticado e contexto de segurança definido.");
       }
    }
}
