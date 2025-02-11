package com.example.socialmedia.service.interfaces;

import com.example.socialmedia.dto.LoginUserDTO;
import com.example.socialmedia.dto.RegisterUserDTO;
import com.example.socialmedia.dto.UserDTO;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface UserService {
    Optional<UserDTO> getUserByUsername(String username)
            throws NoSuchElementException;
    Optional<RegisterUserDTO> register(RegisterUserDTO registerUserDTO);

    Optional<LoginUserDTO> login(String username, String password);
}