package com.paulopsms.idp_authenticator.infrastructure.persistence.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmailIgnoreCaseAndVerifiedTrue(String email);

    Optional<UserEntity> findByToken(String token);
}
