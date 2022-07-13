package com.example.movieratingpoc.controller;

import com.example.movieratingpoc.dto.MovieDTO;
import com.example.movieratingpoc.dto.RateMovieDTO;
import com.example.movieratingpoc.entity.Movie;
import com.example.movieratingpoc.entity.Rating;
import com.example.movieratingpoc.model.ApiResponse;
import com.example.movieratingpoc.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/admin/movie")
    private ResponseEntity<ApiResponse> addMovie(@RequestBody MovieDTO movie) {
        try {
            Movie serviceResponse = movieService.addMovie(movie);
            return new ResponseEntity(serviceResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/admin/movie")
    private ResponseEntity<ApiResponse> updateMovie(@RequestBody MovieDTO movie) {
        try {
            Movie movieServiceResponse = movieService.updateMovie(movie);
            return new ResponseEntity(movieServiceResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/admin/movie/{movieId}")
    private ResponseEntity<ApiResponse> deleteMovie(@PathVariable("movieId") int movieId) {

        try {
            movieService.deleteMovie(movieId);
            return new ResponseEntity("Movie deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movie")
    private ResponseEntity<ApiResponse> getAllMovie() {
        try {
            List<Movie> movies = movieService.getAllMovies();
            return new ResponseEntity(movies, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movie/{movieId}")
    private ResponseEntity<ApiResponse> getMovieById(@PathVariable("movieId") int movieId) {
        try {
            Optional<Movie> movies = movieService.getMovieById(movieId);
            return new ResponseEntity(movies, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/rate/movie/{movieId}/user/{uid}")
    private ResponseEntity<ApiResponse> rateMovie(@Valid @RequestBody RateMovieDTO rating ,
                                     @PathVariable("movieId") int movieId,
                                     @PathVariable("uid") int uid
                            ) {
        try {
            Rating serviceResponse = movieService.rateMovie(rating ,movieId,uid);
            return new ResponseEntity(serviceResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rate/movie/{movieId}")
    private ResponseEntity<ApiResponse> getMovieRating(@PathVariable("movieId") int movieId) {
        try {
            int serviceResponse = movieService.getMovieRating(movieId);
            return new ResponseEntity("The average rating is "+serviceResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
