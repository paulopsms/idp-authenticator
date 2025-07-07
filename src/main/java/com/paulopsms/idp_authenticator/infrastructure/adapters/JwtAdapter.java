package com.paulopsms.idp_authenticator.infrastructure.adapters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.paulopsms.idp_authenticator.application.gateways.JwtGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class JwtAdapter implements JwtGateway {

    @Override
    public String generateToken(User user) throws BusinessException {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");

            return JWT.create()
                    .withIssuer("FinMan")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.expirationTime(30))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new BusinessException("Error generating JWT token.");
        }
    }

    private Instant expirationTime(Integer minutes) {
        return LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.of("-03:00"));
    }
}
