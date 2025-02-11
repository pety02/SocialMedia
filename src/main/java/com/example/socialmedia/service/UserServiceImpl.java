package com.example.socialmedia.service;

import com.example.socialmedia.dto.LoginUserDTO;
import com.example.socialmedia.dto.RegisterUserDTO;
import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public Optional<UserDTO> getUserByUsername(String username) throws NoSuchElementException {
        return Optional.empty();
    }

    @Override
    public Optional<RegisterUserDTO> register(RegisterUserDTO registerUserDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<LoginUserDTO> login(String username, String password) {
        return Optional.empty();
    }
}
