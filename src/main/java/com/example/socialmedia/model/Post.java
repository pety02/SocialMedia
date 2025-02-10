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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column
    private LocalDateTime postDateTime;
    @Column
    private LocalDateTime updateDateTime;
    @Column
    private LocalDateTime deleteDateTime;
    @OneToMany
    private List<User> taggedUsers;
    @OneToMany
    private List<MediaFile> files;
    @OneToOne
    private User publishedBy;
    @OneToMany
    private List<User> likedBy;
    @OneToMany
    private List<Comment> comments;
}