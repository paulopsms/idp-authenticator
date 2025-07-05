package com.paulopsms.idp_authenticator.infrastructure.mappers;

import com.paulopsms.idp_authenticator.domain.entities.user.User;
import com.paulopsms.idp_authenticator.infrastructure.persistence.usuario.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface UserMapper {

    User toModel(UserEntity entity);

    UserEntity toEntity(User dto);
}
