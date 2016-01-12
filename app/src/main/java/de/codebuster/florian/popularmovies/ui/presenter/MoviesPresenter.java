package de.codebuster.florian.popularmovies.ui.presenter;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.codebuster.florian.popularmovies.data.api.Sort;
import de.codebuster.florian.popularmovies.data.domain.GetMovies;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.ui.activity.Navigator;
import de.codebuster.florian.popularmovies.ui.presenter.view.MoviesView;
import de.codebuster.florian.popularmovies.ui.renderer.movie.MovieCollection;

@Singleton
public class MoviesPresenter extends Presenter {

    private GetMovies getMoviesInteractor;
    private Navigator navigator;

    private MoviesView view;
    private MovieCollection currentMovieCollection;
    private Sort sort;

    @Inject
    public MoviesPresenter(GetMovies getMoviesInteractor, Navigator navigator) {
        this.getMoviesInteractor = getMoviesInteractor;
        this.navigator = navigator;
    }

    public void setView(MoviesView view) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }
        this.view = view;
    }

    public void setSortOrder(Sort sort) {
        this.sort = sort;
    }

    @Override
    public void initialize() {
        checkViewAlreadySetted();
        checkSortOrderIsSet();
        loadMovies();
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    public void loadMovies(final MovieCollection movieCollection) {
        currentMovieCollection = movieCollection;
        showMovies(movieCollection.getAsList());
    }

    public void onMovieThumbnailClicked(final Movie movie) {
        navigator.openMovieDetails(movie);
    }

    public void onMovieClicked(final Movie movie) {
        view.showMovieTitleAsMessage(movie);
    }

    public MovieCollection getCurrentMovies() {
        return currentMovieCollection;
    }

    private void loadMovies() {
        if (view.isReady()) {
            view.showLoading();
        }


        getMoviesInteractor.execute(sort, new GetMovies.Callback() {
            @Override
            public void onMoviesLoaded(Collection<Movie> movies) {
                currentMovieCollection = new MovieCollection(movies);
                showMovies(movies);
            }

            @Override
            public void onConnectionError() {
                notifyConnectionError();
            }
        });
    }

    private void notifyConnectionError() {
        if (view.isReady() && !view.isAlreadyLoaded()) {
            view.hideLoading();
            view.showConnectionErrorMessage();
            view.showEmptyCase();
            view.showDefaultTitle();
        }
    }

    private void showMovies(Collection<Movie> movies) {
        if (view.isReady()) {
            view.renderMovies(movies);
            view.hideLoading();
        }
    }

    private void checkViewAlreadySetted() {
        if (view == null) {
            throw new IllegalArgumentException("Remember to set a view for this presenter");
        }
    }

    private void checkSortOrderIsSet() {
        if (view == null) {
            throw new IllegalArgumentException("Remember to set a sort order for this presenter");
        }
    }

    /**
     * View interface created to abstract the view
     * implementation used in this presenter.
     */

}