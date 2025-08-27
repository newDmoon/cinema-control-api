package com.example.movie.controller;

import com.example.movie.dto.CreateMovieDTO;
import com.example.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getOneById(id));
    }

    @PostMapping
    public ResponseEntity<?> addOne(@RequestBody CreateMovieDTO createMovieDTO) {
        return ResponseEntity.ok(movieService.addOne(createMovieDTO));
    }
}
