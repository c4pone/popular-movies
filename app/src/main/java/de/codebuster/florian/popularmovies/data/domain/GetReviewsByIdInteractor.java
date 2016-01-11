package de.codebuster.florian.popularmovies.data.domain;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.executor.Executor;
import de.codebuster.florian.popularmovies.data.executor.Interactor;
import de.codebuster.florian.popularmovies.data.executor.MainThread;
import de.codebuster.florian.popularmovies.data.repository.MoviesRepository;

public class GetReviewsByIdInteractor implements Interactor, GetReviewsById {

    private final MoviesRepository moviesRepository;
    private final Executor executor;
    private final MainThread mainThread;

    private Callback callback;
    private Integer movieId;

    @Inject
    GetReviewsByIdInteractor(
            MoviesRepository moviesRepository,
            Executor executor,
            MainThread mainThread) {
        this.moviesRepository = moviesRepository;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override public void execute(final Integer movieId, final Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException(
                    "Callback can't be null, the client of this interactor needs to get the response "
                            + "in the callback");
        }

        if (movieId == null) {
            throw new IllegalArgumentException("THe movie id can't be null");
        }

        this.movieId = movieId;
        this.callback = callback;
        this.executor.run(this);
    }

    @Override public void run() {
        try {
            List<Review> reviews = moviesRepository.getReviews(movieId);
            nofityReviewsLoaded(reviews);
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

    private void nofityReviewsLoaded(final Collection<Review> reviews) {
        mainThread.post(new Runnable() {
            @Override public void run() {
                callback.onReviewsLoaded(reviews);
            }
        });
    }

}
