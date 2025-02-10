package com.example.socialmedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventId implements Serializable {
    private String title;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}