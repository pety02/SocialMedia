package com.example.socialmedia.mappers;

import com.example.socialmedia.dto.RegisterUserDTO;
import com.example.socialmedia.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistrationUserMapper {
    @Mapping(target = "username", source = "id.username")
    @Mapping(target = "email", source = "id.email")
    @Mapping(target = "password", source = "password")
    RegisterUserDTO toDTO(User user);
    @Mapping(target = "id.username", source = "username")
    @Mapping(target = "id.email", source = "email")
    @Mapping(target = "password", source = "password")
    User toEntity(RegisterUserDTO userDTO);
}