package com.example.movie.dto;

public record CreateMovieDTO(String title, String description, short ageRestriction, String director) {
}
