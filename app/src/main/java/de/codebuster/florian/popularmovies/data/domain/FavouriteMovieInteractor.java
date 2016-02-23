package de.codebuster.florian.popularmovies.data.domain;


import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.executor.Executor;
import de.codebuster.florian.popularmovies.data.executor.Interactor;
import de.codebuster.florian.popularmovies.data.executor.MainThread;
import de.codebuster.florian.popularmovies.data.repository.MoviesRepository;

public class FavouriteMovieInteractor implements Interactor, FavouriteMovie {

    private final MoviesRepository moviesRepository;
    private final Executor executor;
    private final MainThread mainThread;

    private Movie movie;
    private Callback callback;

    @Inject
    FavouriteMovieInteractor(MoviesRepository moviesRepository,
                             Executor executor,
                             MainThread mainThread) {
        this.moviesRepository = moviesRepository;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override
    public void execute(Movie movie, Callback callback) {


        if (callback == null) {
            throw new IllegalArgumentException(
                    "Callback can't be null, the client of this interactor needs to get the response "
                            + "in the callback");
        }

        if (movie == null) {
            throw new IllegalArgumentException("The movie can't be null");
        }

        this.movie = movie;
        this.callback = callback;
        this.executor.run(this);

    }

    @Override
    public void run() {
        try {
            movie.toggleFavourite();

            if (movie.isFavourite()) {
                moviesRepository.save(movie);
            }
            else {
                moviesRepository.delete(movie);
            }

            callback.onSuccess();

        } catch (Exception e) {
            callback.onError();
        }
    }
}