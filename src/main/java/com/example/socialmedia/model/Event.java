package com.example.socialmedia.model;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
@IdClass(EventId.class)
public class Event {
    @Id
    @Column(nullable = false, length = 200)
    private String title;
    @Id
    @Column(nullable = false)
    private LocalDateTime startDateTime;
    @Id
    @Column(nullable = false)
    private LocalDateTime endDateTime;
    @Column(length = 500)
    private String description;
    @ManyToOne
    private Location location;
    @ManyToOne
    private UserDetails organisedBy;
    @OneToMany
    private List<MediaFile> files;
    @OneToMany
    private List<User> interestedIn;
}