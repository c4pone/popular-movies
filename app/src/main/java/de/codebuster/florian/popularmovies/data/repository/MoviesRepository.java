package de.codebuster.florian.popularmovies.data.repository;

import java.util.List;

import de.codebuster.florian.popularmovies.data.api.Sort;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;

public interface MoviesRepository {
    List<Movie> discoverMovies(Sort sort, int page) throws Exception;

    List<Video> getVideos(Long movieId) throws Exception;

    List<Review> getReviews(Long movieId, int page) throws Exception;

    void save(Movie movie) throws Exception;

    void delete(Movie movie) throws Exception;
}
