package de.codebuster.florian.popularmovies.data.domain;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.api.Sort;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.executor.Executor;
import de.codebuster.florian.popularmovies.data.executor.Interactor;
import de.codebuster.florian.popularmovies.data.executor.MainThread;
import de.codebuster.florian.popularmovies.data.repository.MoviesRepository;

public class GetMoviesInteractor implements Interactor, GetMovies {

    private final MoviesRepository moviesRepository;
    private final Executor executor;
    private final MainThread mainThread;

    private Callback callback;

    @Inject
    GetMoviesInteractor(MoviesRepository moviesRepository, Executor executor, MainThread mainThread) {
        this.moviesRepository = moviesRepository;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override public void execute(final Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException(
                    "Callback can't be null, the client of this interactor needs to get the response "
                            + "in the callback");
        }
        this.callback = callback;
        this.executor.run(this);
    }

    @Override public void run() {
        //TODO
        //get shared preferences somehow :D
        //probably inject them over di

        try {
            List<Movie> movies = moviesRepository.discoverMovies(Sort.POPULARITY, 1);
            nofityMoviesLoaded(movies);
        } catch (Exception e) {
            notifyError();
        }
    }

    private void notifyError() {
        mainThread.post(new Runnable() {
            @Override public void run() {
                callback.onConnectionError();
            }
        });
    }

    private void nofityMoviesLoaded(final Collection<Movie> movies) {
        mainThread.post(new Runnable() {
            @Override public void run() {
                callback.onMoviesLoaded(movies);
            }
        });
    }
}
