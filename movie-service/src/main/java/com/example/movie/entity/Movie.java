package com.example.movie.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "movies", schema = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
//    private ArrayList<String> genres;
    private short ageRestriction;
    private String director;
    private short durationInMinutes;
    private LocalDateTime showFromDate;
    private LocalDateTime showToDate;
//    private ArrayList<String> inRoles;

//    private ArrayList<UUID> showTimes;
}
