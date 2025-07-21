package com.paulopsms.idp_authenticator.infrastructure.persistence.role;

import com.paulopsms.idp_authenticator.domain.entities.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRole(UserRole role);
}
