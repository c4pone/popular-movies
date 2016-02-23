package de.codebuster.florian.popularmovies.data.domain;


import de.codebuster.florian.popularmovies.data.domain.movie.Movie;

public interface FavouriteMovie {
    void execute(Movie movie, Callback callback);

    interface Callback {
        void onSuccess();

        void onError();
    }
}
