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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String country;
    @Column(nullable = false, length = 50)
    private String livingArea;
    @Column(nullable = false, length = 100)
    private String addressDetails;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;
}