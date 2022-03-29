package com.ogawalucas.springbootapirest.config.security;

import com.ogawalucas.springbootapirest.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expirationTime;
    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        var user = (Usuario) authentication.getPrincipal();

        return Jwts.builder()
            .setIssuer("Spring Boot Api REST")
            .setSubject(user.getId().toString())
            .setExpiration(new Date(new Date().getTime() + Long.parseLong(expirationTime)))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }
}
