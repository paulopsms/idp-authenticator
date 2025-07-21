package com.paulopsms.idp_authenticator.infrastructure.adapters.role;

import com.paulopsms.idp_authenticator.application.gateways.RoleRepositoryGateway;
import com.paulopsms.idp_authenticator.domain.entities.user.Role;
import com.paulopsms.idp_authenticator.domain.entities.user.UserRole;
import com.paulopsms.idp_authenticator.infrastructure.mappers.RoleMapper;
import com.paulopsms.idp_authenticator.infrastructure.persistence.role.RoleEntity;
import com.paulopsms.idp_authenticator.infrastructure.persistence.role.RoleRepository;

import java.util.Optional;

public class RoleRepositoryAdapter implements RoleRepositoryGateway {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleRepositoryAdapter(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Optional<Role> findByRole(UserRole role) {
        Optional<RoleEntity> foundROle = this.roleRepository.findByRole(role);

        return foundROle.map(this.roleMapper::toModel);
    }
}
