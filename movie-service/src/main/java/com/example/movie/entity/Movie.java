package com.example.movie.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Movie {
    private UUID id;
    private String title;
    private String description;
    private ArrayList<String> genres;
    private short ageRestriction;
    private String director;
    private short durationInMinutes;
    private LocalDateTime showFromDate;
    private LocalDateTime showToDate;
    private ArrayList<String> inRoles;

    private ArrayList<UUID> showTimes;
}
