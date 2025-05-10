package com.Restfulapi.infra;
import com.Restfulapi.domain.model.Funcionario.Funcionario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {
    //por em uma variavel de ambiente
    private String secret = "test";
    public String generateToken(Funcionario funcionario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            List<String> roles = funcionario.getAuthorities()
                    .stream()
                    .map(granted -> granted.getAuthority())
                    .collect(Collectors.toList());
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(funcionario.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .withClaim("roles", roles)
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("erro na geracao do token", e);

        }
    }
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
    public List<String> extractRoles(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        var decoded = JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token);
        return decoded.getClaim("roles").asList(String.class);
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}

