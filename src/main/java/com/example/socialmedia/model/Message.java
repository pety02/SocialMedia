package com.example.socialmedia.model;

import jakarta.persistence.*;
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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 500)
    private String content;
    @Column
    private LocalDateTime sentDateTime;
    @Column
    private LocalDateTime updateDateTime;
    @Column
    private LocalDateTime deleteDateTime;
    @OneToOne(fetch = FetchType.EAGER)
    private User benefactor;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> recipients;
}