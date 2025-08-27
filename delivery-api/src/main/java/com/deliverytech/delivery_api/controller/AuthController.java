package com.deliverytech.delivery_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;

import com.deliverytech.delivery_api.dto.AuthRequest;
import com.deliverytech.delivery_api.dto.AuthResponse;
import com.deliverytech.delivery_api.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
   
    @Autowired
    private JwtUtil jwtUtil;
   
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
       try {
            // Tenta autenticar com as credenciais fornecidas
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
            );
            
            // Se a autenticação for bem-sucedida, gera um token JWT
            String email = authentication.getName();
            String token = jwtUtil.gerarToken(email);
            
            // Retorna o token na resposta
            return ResponseEntity.ok(new AuthResponse(token));
            } catch (BadCredentialsException e) {
            // Retorna erro 401 se as credenciais forem inválidas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}