package com.example.socialmedia.dto;

import com.example.socialmedia.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String password;
    //private Set<Role> roles;
    // TODO: to add all other fields as DTO objects
}