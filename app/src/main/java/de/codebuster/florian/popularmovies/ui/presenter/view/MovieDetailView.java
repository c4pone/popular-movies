package de.codebuster.florian.popularmovies.ui.presenter.view;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;

public interface MovieDetailView {

    void showMoviePoster(String url);

    void showMovieBackdrop(String url);

    void setTitle(String title);

    void setDescription(String description);

    void setRating(String rating);

    void setReleaseYear(String releaseYear);

    void renderReviews(Collection<Review> reviews);

    void renderVideos(Collection<Video> videos);

    void showConnectionErrorMessage();

    boolean isReady();
}
