package de.codebuster.florian.popularmovies.data.domain;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;

public interface GetMovieById {
    interface Callback {
        void onMovieLoaded(final Movie movie);

        void onMovieNotFound();

        void onConnectionError();
    }

    void execute(final String movieId, final Callback callback);
}
