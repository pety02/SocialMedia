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
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 100)
    private String mimeType;
    @Column(nullable = false, length = 10)
    private String extension;
    @Column(nullable = false, length = 500)
    private String fullPath;
    @Lob
    private Byte[] content;
    @OneToMany
    private List<Post> addedInPosts;
    @OneToMany
    private List<Event> addedInEvents;
}