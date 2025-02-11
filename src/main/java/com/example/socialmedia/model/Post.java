package com.example.socialmedia.model;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MediaFile> files;
    @OneToOne
    private User publishedBy;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "user_email", referencedColumnName = "user_email"),
                    @JoinColumn(name = "user_username", referencedColumnName = "user_username")
            }
    )
    private Set<User> likedBy;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;
}