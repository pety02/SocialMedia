package com.example.socialmedia.dto;

import com.example.socialmedia.model.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
}