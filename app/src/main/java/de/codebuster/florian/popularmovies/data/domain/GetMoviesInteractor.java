package de.codebuster.florian.popularmovies.data.domain;

import android.util.Log;

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
    private Sort sort;

    @Inject
    GetMoviesInteractor(
            MoviesRepository moviesRepository,
            Executor executor,
            MainThread mainThread) {
        this.moviesRepository = moviesRepository;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override
    public void execute(Sort sort, final Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException(
                    "Callback can't be null, the client of this interactor needs to get the response "
                            + "in the callback");
        }

        //set default
        if (sort == null) {
            sort = Sort.POPULARITY;
        }

        this.sort = sort;
        this.callback = callback;
        this.executor.run(this);
    }

    @Override
    public void run() {
        //TODO Make the pages dynamic
        try {
            List<Movie> movies = moviesRepository.discoverMovies(sort, 1);
            nofityMoviesLoaded(movies);
        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.toString());
            notifyError();
        }
    }

    private void notifyError() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onConnectionError();
            }
        });
    }

    private void nofityMoviesLoaded(final Collection<Movie> movies) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onMoviesLoaded(movies);
            }
        });
    }
}
