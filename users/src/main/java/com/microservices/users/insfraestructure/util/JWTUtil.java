package com.microservices.users.insfraestructure.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {

    private final JWTConfig jwtConfig;

    public JWTUtil(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }


    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();
    }

    public String getEmailFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado");
        } catch (MalformedJwtException e) {
            System.out.println("Token mal formado");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token no soportado");
        } catch (IllegalArgumentException e) {
            System.out.println("Token vacío o nulo");
        }
        return false;
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
