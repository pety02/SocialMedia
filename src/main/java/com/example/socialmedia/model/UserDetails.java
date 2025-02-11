package com.example.socialmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 150)
    private String name;
    @Column(length = 150)
    private String surname;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    @Column(length = 500)
    private String resume;
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;
    @OneToOne
    private User credentials;
    @OneToOne
    private MediaFile profilePicture;
}