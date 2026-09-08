package com.nikaru.fixit.service;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service 
public class TokenService {

    private final Long expiration = 900000L;
    private final SecretKey secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode("utXbPF1Zr9Qtdv6jtzrA8xX9io26syDDbIe21BvcVvA="));
    

    public String generateToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                    .subject(username)
                    .issuedAt(Date.from(now))
                    .expiration(Date.from(now.plusMillis(expiration)))
                    .signWith(secret)
                    .compact();

    }


    public String extractUsername(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extractUsername'");
    }


    public boolean validate(String token, UserDetails userDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }
}
