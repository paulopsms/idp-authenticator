package com.paulopsms.idp_authenticator.infrastructure.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String verifyToken(String token) throws BusinessException {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("FinMan")
                    .build();

            decodedJWT = verifier.verify(token);

            return decodedJWT.getSubject();
        } catch ( JWTVerificationException e){
            throw new BusinessException("Error verifying JWT token: " + e.getMessage());
        }
    }
}
