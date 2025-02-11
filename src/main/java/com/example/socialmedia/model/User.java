package com.example.socialmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class User {
    @EmbeddedId
    private UserId id;

    @Column(nullable = false, length = 100)
    private String password;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_details_id", nullable = false)
    private UserDetails details;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_friends",
            joinColumns = {
                    @JoinColumn(name = "user_email", referencedColumnName = "user_email"),
                    @JoinColumn(name = "user_username", referencedColumnName = "user_username")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "friend_email", referencedColumnName = "user_email"),
                    @JoinColumn(name = "friend_username", referencedColumnName = "user_username")
            }
    )
    private Set<User> friends;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> createdPosts;
    @ManyToMany(mappedBy = "likedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Post> likedPosts;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> organisedEvents;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> writtenMessages;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> receivedMessages;
}