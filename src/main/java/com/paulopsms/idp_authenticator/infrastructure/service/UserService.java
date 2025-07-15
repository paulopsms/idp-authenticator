package com.paulopsms.idp_authenticator.infrastructure.service;

import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmailIgnoreCaseAndVerifiedTrue(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
