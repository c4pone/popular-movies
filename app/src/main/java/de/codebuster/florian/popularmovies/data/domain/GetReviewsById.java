package de.codebuster.florian.popularmovies.data.domain;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.domain.movie.Review;

public interface GetReviewsById {

    void execute(final Integer movieId, final Callback callback);

    interface Callback {
        void onReviewsLoaded(final Collection<Review> reviews);

        void onConnectionError();
    }
}