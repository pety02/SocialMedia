package com.example.socialmedia.service;

import com.example.socialmedia.dto.LoginUserDTO;
import com.example.socialmedia.dto.RegisterUserDTO;
import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.mappers.RegistrationUserMapper;
import com.example.socialmedia.mappers.UserMapper;
import com.example.socialmedia.model.ProfileType;
import com.example.socialmedia.model.Role;
import com.example.socialmedia.model.User;
import com.example.socialmedia.model.UserDetails;
import com.example.socialmedia.repository.UserDetailsRepository;
import com.example.socialmedia.repository.UserRepository;
import com.example.socialmedia.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final RegistrationUserMapper registrationUserMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RegistrationUserMapper registrationUserMapper, UserMapper loginUserMapper, UserRepository userRepository, UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder) {
        this.registrationUserMapper = registrationUserMapper;
        this.userMapper = loginUserMapper;
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticateUser(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<UserDTO> getUserByUsername(String username) throws NoSuchElementException {
        return Optional.empty();
    }

    @Override
    public Optional<RegisterUserDTO> register(RegisterUserDTO registerUserDTO) {
        User registerUser = registrationUserMapper.toEntity(registerUserDTO);
        UserDetails registerUserDetails = new UserDetails();
        registerUserDetails.setName(registerUserDTO.getName());
        registerUserDetails.setSurname(registerUserDTO.getSurname());
        registerUserDetails.setDateOfBirth(registerUserDTO.getDateOfBirth());
        registerUserDetails.setProfileType(ProfileType.BASIC);
        registerUserDetails.setRoles((List<Role>) Collections.singleton(Role.USER));
        registerUserDetails.setCredentials(registerUser);
        String rawPassword = registerUser.getPassword();
        registerUser.setPassword(passwordEncoder.encode(rawPassword));
        User u = userRepository.save(registerUser);
        UserDetails ud = userDetailsRepository.save(registerUserDetails);
        return Optional.of(registrationUserMapper.toDTO(u, ud));
    }

    @Override
    public Optional<UserDTO> login(LoginUserDTO loginUserDTO) {
        return Optional.of(userMapper.toDTO(userRepository.getByUsernameAndPassword(loginUserDTO.getUsername(), loginUserDTO.getPassword())));
    }
}
