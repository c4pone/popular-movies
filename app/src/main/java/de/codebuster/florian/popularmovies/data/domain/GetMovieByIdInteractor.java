package de.codebuster.florian.popularmovies.data.domain;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.executor.Executor;
import de.codebuster.florian.popularmovies.data.executor.MainThread;

public class GetMovieByIdInteractor implements GetMovieById {

    private final Executor executor;
    private final MainThread mainThread;

    private String movieId;
    private Callback callback;

    @Inject
    GetMovieByIdInteractor(Executor executor, MainThread mainThread) {
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override
    public void execute(String movieId, Callback callback) {

    }
}
