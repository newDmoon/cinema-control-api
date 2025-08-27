package com.example.movie.service;

import com.example.movie.dto.CreateMovieDTO;
import com.example.movie.entity.Movie;
import com.example.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie getOneById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Movie addOne(CreateMovieDTO createMovieDTO) {
        if (movieRepository.existsByTitle(createMovieDTO.title())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Movie newMovie = new Movie();
        newMovie.setTitle(createMovieDTO.title());
        newMovie.setDescription(createMovieDTO.description());
        newMovie.setDirector(createMovieDTO.director());
        newMovie.setAgeRestriction(createMovieDTO.ageRestriction());

        return movieRepository.save(newMovie);
    }
}
