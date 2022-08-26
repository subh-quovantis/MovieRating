package com.example.movieratingpoc.service;


import com.example.movieratingpoc.dto.MovieDTO;
import com.example.movieratingpoc.dto.RateMovieDTO;
import com.example.movieratingpoc.entity.Movie;
import com.example.movieratingpoc.entity.Rating;
import com.example.movieratingpoc.entity.User;
import com.example.movieratingpoc.repository.MovieRepository;
import com.example.movieratingpoc.repository.RatingRepository;
import com.example.movieratingpoc.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MovieService {

   @Autowired
   private  MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;

    public Movie addMovie(MovieDTO movie) {
        ModelMapper modelMapper = new ModelMapper();
        Movie movies = modelMapper.map(movie, Movie.class);
        return movieRepository.save(movies);

    }

    public Movie updateMovie(MovieDTO movie) {
        Movie responseMovie = movieRepository.findById(movie.getId()).get();
        if (responseMovie.getMovieName() != null) {
            responseMovie.setMovieName(movie.getMovieName());
            responseMovie.setGenre(movie.getGenre());
            return movieRepository.save(responseMovie);
        }
        return null;
    }

    public void deleteMovie(int id) {
        Optional<Movie> searchMovieResult = movieRepository.findById(id);
        if (searchMovieResult.isPresent()) {
            movieRepository.deleteById(id);
        } else {
            throw new RuntimeException("Movie id not found");
        }
    }

    public List<Movie> getAllMovies() {

        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(int movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()) {
            return movie;
        } else {
            throw new RuntimeException("Movie not found");
        }

    }

    public Rating rateMovie(RateMovieDTO rating, int movieId, int uid) {

        User userResponse = userRepository.findById(uid).get();
        Movie movieResponse = movieRepository.findById(movieId).get();

        List<Rating> ratedMovies = ratingRepository.getAllMoviesByMovieId(movieId);

        Stream<Rating> userRated = ratedMovies.stream().filter(x->x.getUser().getId()==uid);
        if(movieResponse.getId()==0){
            throw new RuntimeException("Movie id not present");
        }
        if(ratedMovies.isEmpty() ||userRated.count()==0 ){
            Rating rating1 = new Rating();
            rating1.setRating(rating.getRating());
            rating1.setMovie(movieResponse);
            rating1.setUser(userResponse);
            return ratingRepository.save(rating1);
        }
        else{
            throw new RuntimeException("Already rated");
        }

    }

    public int getMovieRating(int movieId) {
        return ratingRepository.findMovieRating(movieId);
    }
}
