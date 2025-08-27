package com.deliverytech.delivery_api.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // 🔑 Gera a chave a partir do secret
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Gera um token JWT para o usuário
    public String gerarToken(String email) {
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + expiration);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(agora)
                .setExpiration(dataExpiracao)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // ✅ novo formato
                .compact();
    }

    // Valida um token JWT e retorna as claims
    public Claims validarToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // ✅ parserBuilder no lugar de parser
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null; // Token inválido
        }
    }

    // Extrai o email (subject) do token
    public String getEmailFromToken(String token) {
        Claims claims = validarToken(token);
        return claims != null ? claims.getSubject() : null;
    }
}