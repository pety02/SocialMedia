package com.example.socialmedia.mappers;

import com.example.socialmedia.dto.RegisterUserDTO;
import com.example.socialmedia.dto.UserDetailsDTO;
import com.example.socialmedia.model.User;
import com.example.socialmedia.model.UserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistrationUserMapper {
    @Mapping(target = "username", source = "user.id.username")
    @Mapping(target = "email", source = "user.id.email")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "name", source = "details.name")
    @Mapping(target = "surname", source = "details.surname")
    @Mapping(target = "dateOfBirth", source = "details.dateOfBirth")
    RegisterUserDTO toDTO(User user, UserDetails details);
    @Mapping(target = "id.username", source = "username")
    @Mapping(target = "id.email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "details.name", source = "name")
    @Mapping(target = "details.surname", source = "surname")
    @Mapping(target = "details.dateOfBirth", source = "dateOfBirth")
    User toEntity(RegisterUserDTO registerUserDTO);
}