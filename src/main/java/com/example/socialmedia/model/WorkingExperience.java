package com.example.socialmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class WorkingExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User user;
    @OneToOne
    private Business business;
    @Column
    private LocalDate fromDate;
    @Column
    private LocalDate toDate;
    @Column(nullable = false, length = 100)
    private String jobTitle;
    @Column(length = 500)
    private String description;
}