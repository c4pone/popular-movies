package de.codebuster.florian.popularmovies.ui.presenter;

import java.util.Collection;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.domain.FavouriteMovie;
import de.codebuster.florian.popularmovies.data.domain.GetReviewsById;
import de.codebuster.florian.popularmovies.data.domain.GetVideosById;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.activity.Navigator;
import de.codebuster.florian.popularmovies.ui.presenter.view.MovieDetailView;
import de.codebuster.florian.popularmovies.ui.renderer.review.ReviewCollection;
import de.codebuster.florian.popularmovies.ui.renderer.video.VideoCollection;
import de.codebuster.florian.popularmovies.utils.ImageUtils;


public class MovieDetailPresenter extends Presenter {

    private GetReviewsById getReviewsByIdInteractor;
    private GetVideosById getVideosByIdInteractor;
    private FavouriteMovie favouriteMovieInteractor;
    private Navigator navigator;
    private MovieDetailView view;

    private Movie movie;
    private VideoCollection currentVideoCollection;
    private ReviewCollection currentReviewCollection;

    @Inject
    public MovieDetailPresenter(
            GetReviewsById getReviewsByIdInteractor,
            GetVideosById getVideosByIdInteractor,
            FavouriteMovie favouriteMovieInteractor,
            Navigator navigator) {
        this.getReviewsByIdInteractor = getReviewsByIdInteractor;
        this.getVideosByIdInteractor = getVideosByIdInteractor;
        this.favouriteMovieInteractor = favouriteMovieInteractor;
        this.navigator = navigator;
    }

    @Override
    public void initialize() {
        checkViewAlreadySetted();
        checkMovieAlreadySetted();

        loadReviews();
        loadVideos();

        showMovie();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public VideoCollection getCurrentVideos() {
        return this.currentVideoCollection;
    }

    public ReviewCollection getCurrentReviews() {
        return this.currentReviewCollection;
    }

    private void showMovie() {

        if (view.isReady()) {
            view.showLoading();
            view.setTitle(movie.getTitle());
            view.setDescription(movie.getOverview());
            view.setRating("Vote Average: " + movie.getVoteAverage() + " / " + "10");
            view.setReleaseYear(movie.getReleaseYear());
            view.showMoviePoster(ImageUtils.getUrl(185) + movie.getPosterPath());
            view.showMovieBackdrop(ImageUtils.getUrl(500) + movie.getBackdropPath());
            view.hideLoading();
        }
    }

    public void setView(MovieDetailView view) {
        if (view == null) {
            throw new IllegalArgumentException("You can't set a null view");
        }

        this.view = view;
    }

    public void setMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("You can't set a null id");
        }

        this.movie = movie;
    }

    public void onVideoClicked(Video video) {
        navigator.openVideo(video);
    }

    private void checkViewAlreadySetted() {
        if (view == null) {
            throw new IllegalArgumentException("Remember to set a view for this presenter");
        }
    }

    private void checkMovieAlreadySetted() {
        if (movie == null) {
            throw new IllegalArgumentException("Remember to set a movie for this presenter");
        }
    }

    public void loadReviews(final ReviewCollection reviewCollection) {
        currentReviewCollection = reviewCollection;
        showReviews(reviewCollection.getAsList());
    }

    private void loadReviews() {
        if (view.isReady()) {
            getReviewsByIdInteractor.execute(movie.getMovieId(), new GetReviewsById.Callback() {
                @Override
                public void onReviewsLoaded(Collection<Review> reviews) {
                    currentReviewCollection = new ReviewCollection(reviews);
                    showReviews(reviews);
                }

                @Override
                public void onConnectionError() {
                    notifyConnectionError();
                }
            });
        }
    }

    public void loadVideos(final VideoCollection videoCollection) {
        currentVideoCollection = videoCollection;
        showVideos(videoCollection.getAsList());
    }

    private void loadVideos() {
        if (view.isReady()) {
            getVideosByIdInteractor.execute(movie.getMovieId(), new GetVideosById.Callback() {
                @Override
                public void onVideosLoaded(Collection<Video> videos) {
                    currentVideoCollection = new VideoCollection(videos);
                    showVideos(videos);
                }

                @Override
                public void onConnectionError() {
                    notifyConnectionError();
                }
            });
        }
    }

    private void showVideos(Collection<Video> videos) {
        if (view != null && view.isReady()) {
            view.renderVideos(videos);
            view.hideLoading();
        }
    }

    private void showReviews(Collection<Review> reviews) {
        if (view != null && view.isReady()) {
            view.renderReviews(reviews);
            view.hideLoading();
        }
    }

    private void notifyConnectionError() {
        if (view.isReady()) {
            view.hideLoading();
            view.showConnectionErrorMessage();
        }
    }

    public void onReviewAuthorClicked(Review review) {
        navigator.openUrl(review.getUrl());
    }

    public void onMovieFavourite() {
        favouriteMovieInteractor.execute(this.movie, new FavouriteMovie.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
        //call to favourite Movie interactor
        //show message movie got favourite
    }
}
