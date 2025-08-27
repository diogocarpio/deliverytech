package com.deliverytech.delivery_api.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery_api.model.User;
import com.deliverytech.delivery_api.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
   
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
      
       User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(
                "Usuário não encontrado com email: " + email));
      
       return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getSenha())
            .authorities("USER") // Ou use as roles do usuário
            .build();
    }
}
