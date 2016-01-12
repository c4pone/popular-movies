package de.codebuster.florian.popularmovies.data.domain;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;

public interface GetMovieById {
    void execute(final String movieId, final Callback callback);

    interface Callback {
        void onMovieLoaded(final Movie movie);

        void onMovieNotFound();

        void onConnectionError();
    }
}
