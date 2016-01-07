package de.codebuster.florian.popularmovies.data.repository;

import java.util.List;

import de.codebuster.florian.popularmovies.data.api.Sort;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;

public interface MoviesRepository {
    List<Movie> discoverMovies(Sort sort, int page) throws Exception;
}
