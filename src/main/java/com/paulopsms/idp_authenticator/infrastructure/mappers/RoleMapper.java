package com.paulopsms.idp_authenticator.infrastructure.mappers;

import com.paulopsms.idp_authenticator.domain.entities.user.Role;
import com.paulopsms.idp_authenticator.infrastructure.persistence.role.RoleEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface RoleMapper {

    Role toModel(RoleEntity entity);

    RoleEntity toEntity(Role dto);
}
