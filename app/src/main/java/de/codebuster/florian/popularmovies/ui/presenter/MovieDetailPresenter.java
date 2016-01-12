package de.codebuster.florian.popularmovies.ui.presenter;

import android.content.Intent;
import android.net.Uri;

import java.util.Collection;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.domain.GetReviewsById;
import de.codebuster.florian.popularmovies.data.domain.GetVideosById;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.activity.Navigator;
import de.codebuster.florian.popularmovies.ui.presenter.view.MovieDetailView;
import de.codebuster.florian.popularmovies.ui.renderer.movie.MovieCollection;
import de.codebuster.florian.popularmovies.ui.renderer.video.VideoCollection;
import de.codebuster.florian.popularmovies.utils.ImageUtils;
import de.codebuster.florian.popularmovies.utils.UrlUtils;


public class MovieDetailPresenter extends Presenter {

    private GetReviewsById getReviewsByIdInteractor;
    private GetVideosById getVideosByIdInteractor;
    private Navigator navigator;
    private MovieDetailView view;

    private Movie movie;
    private VideoCollection currentVideoCollection;

    @Inject
    public MovieDetailPresenter(
            GetReviewsById getReviewsByIdInteractor,
            GetVideosById getVideosByIdInteractor,
            Navigator navigator) {
        this.getReviewsByIdInteractor = getReviewsByIdInteractor;
        this.getVideosByIdInteractor = getVideosByIdInteractor;
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

    private void showMovie() {

        if (view.isReady()) {
//            view.showLoading();
            view.setTitle(movie.getTitle());
            view.setDescription(movie.getOverview());
            view.setRating("Vote Average: " + movie.getVoteAverage() + " / " + "10");
            view.setReleaseYear(movie.getReleaseYear());
            view.showMoviePoster(ImageUtils.getUrl(185) + movie.getPosterPath());
            view.showMovieBackdrop(ImageUtils.getUrl(500) + movie.getBackdropPath());
//            view.hideLoading();
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

    private void loadReviews() {

    }

    public void loadVideos(final VideoCollection videoCollection) {
        currentVideoCollection = videoCollection;
        showVideos(videoCollection.getAsList());
    }

    private void loadVideos() {
        if (view.isReady()) {
            getVideosByIdInteractor.execute(movie.getId(), new GetVideosById.Callback() {
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
        if (view.isReady()) {
            view.renderVideos(videos);
//            view.hideLoading();
        }
    }

    private void notifyConnectionError() {
        if (view.isReady()) {
//            view.hideLoading();
            view.showConnectionErrorMessage();
        }
    }

}
