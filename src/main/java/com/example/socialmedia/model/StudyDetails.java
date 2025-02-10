package com.example.socialmedia.model;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class StudyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User student;
    @ManyToOne
    private EducationCenter educationCenter;
    @Column
    private LocalDate fromDate;
    @Column
    private LocalDate toDate;
    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;
}