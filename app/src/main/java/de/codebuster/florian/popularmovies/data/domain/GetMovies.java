package de.codebuster.florian.popularmovies.data.domain;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.api.Sort;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;


public interface GetMovies {
    void execute(Sort sort, Callback callback);

    interface Callback {
        void onMoviesLoaded(final Collection<Movie> movies);

        void onConnectionError();
    }
}
