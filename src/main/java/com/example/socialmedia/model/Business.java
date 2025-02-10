package com.example.socialmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Business {
    @Id
    @Column(nullable = false, unique = true)
    private String title;
    @OneToOne
    private Location location;
    @Column(length = 500)
    private String description;
    @OneToMany
    private List<MediaFile> files;
    @OneToMany
    private List<WorkingExperience> workingExperiences;
}