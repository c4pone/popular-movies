package de.codebuster.florian.popularmovies.ui.presenter.view;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;

public interface MoviesView {

    void hideLoading();

    void showLoading();

    void renderMovies(final Collection<Movie> movies);

    void showConnectionErrorMessage();

    void showEmptyCase();

    void showDefaultTitle();

    void showMovieTitleAsMessage(Movie movie);

    boolean isReady();

    boolean isAlreadyLoaded();
}