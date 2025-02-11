package com.example.socialmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

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
    @OneToOne(fetch = FetchType.EAGER)
    private Location location;
    @OneToOne(fetch = FetchType.EAGER)
    private User organisedBy;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_files",
            joinColumns = {
                    @JoinColumn(name = "event_title", referencedColumnName = "title"),
                    @JoinColumn(name = "event_start_time", referencedColumnName = "startDateTime"),
                    @JoinColumn(name = "event_end_time", referencedColumnName = "endDateTime")
            },
            inverseJoinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id")
    )
    private Set<MediaFile> files;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_interests",
            joinColumns = {
                    @JoinColumn(name = "event_title", referencedColumnName = "title"),
                    @JoinColumn(name = "event_start_time", referencedColumnName = "startDateTime"),
                    @JoinColumn(name = "event_end_time", referencedColumnName = "endDateTime")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_email", referencedColumnName = "user_email"),
                    @JoinColumn(name = "user_username", referencedColumnName = "user_username")
            }
    )
    private Set<User> interestedIn;
}