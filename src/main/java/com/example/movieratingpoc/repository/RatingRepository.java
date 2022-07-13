package com.example.movieratingpoc.repository;

import com.example.movieratingpoc.entity.Movie;
import com.example.movieratingpoc.entity.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {

    @Query(value = "select * from rating where movie_id=?1",nativeQuery = true)
    public List<Rating> getAllMoviesByMovieId(int movieId);

    public List<Rating> findByMovie(Movie movie);

}
