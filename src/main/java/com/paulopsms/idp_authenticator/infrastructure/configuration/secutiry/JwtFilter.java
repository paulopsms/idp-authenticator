package com.paulopsms.idp_authenticator.infrastructure.configuration.secutiry;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessException;
import com.paulopsms.idp_authenticator.domain.exceptions.BusinessRuntimeException;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserEntity;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserRepository;
import com.paulopsms.idp_authenticator.infrastructure.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    public JwtFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get token from request
        String token = this.getRequestToken(request);

        if (token != null) {
            try {
                String userEmail = this.jwtService.verifyToken(token);

                UserEntity user = this.userRepository.findByEmailIgnoreCaseAndVerifiedTrue(userEmail).orElseThrow();

                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (BusinessException e) {
                throw new BusinessRuntimeException(e);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getRequestToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");

        if (authToken != null) {
            return authToken.replace("Bearer ", "");
        } else return null;
    }

//    private String verifyToken(String token) throws BusinessException {
//        DecodedJWT decodedJWT;
//        try {
//            Algorithm algorithm = Algorithm.HMAC256("secret");
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("FinMan")
//                    .build();
//
//            decodedJWT = verifier.verify(token);
//            return decodedJWT.getSubject();
//        } catch ( JWTVerificationException e){
//            throw new BusinessException("Error verifying JWT token: " + e.getMessage());
//        }
//    }
}
