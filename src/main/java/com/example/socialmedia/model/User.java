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
@IdClass(UserId.class)
public class User {
    @Id
    @Column(nullable = false, unique = true, length = 360)
    private String email;
    @Id
    @Column(nullable = false, unique = true, length = 100)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
    @OneToOne
    private UserDetails details;
    @OneToMany
    private List<User> friends;
    @OneToMany
    private List<Post> createdPosts;
    @OneToMany
    private List<Post> likedPosts;
    @OneToMany
    private List<Event> organisedEvents;
    @OneToMany
    private List<Event> interestsEvents;
    @OneToMany
    private List<Message> writtenMessages;
    @OneToMany
    private List<Message> receivedMessages;
}