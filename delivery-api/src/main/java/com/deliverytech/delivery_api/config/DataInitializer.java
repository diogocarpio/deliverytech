package com.deliverytech.delivery_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
       }
    }
}
