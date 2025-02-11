package com.example.socialmedia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserId implements Serializable {
    @Column(name = "user_email", nullable = false, unique = true, length = 360)
    private String email;
    @Column(name = "user_username", nullable = false, unique = true, length = 100)
    private String username;
}