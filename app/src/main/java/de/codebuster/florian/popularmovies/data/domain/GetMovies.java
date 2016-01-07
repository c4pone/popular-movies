package de.codebuster.florian.popularmovies.data.domain;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;


public interface GetMovies {
    interface Callback {
        void onMoviesLoaded(final Collection<Movie> movies);

        void onConnectionError();
    }

    void execute(Callback callback);
}
