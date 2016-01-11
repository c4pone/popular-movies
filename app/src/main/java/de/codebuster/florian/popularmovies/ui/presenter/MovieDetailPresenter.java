package de.codebuster.florian.popularmovies.ui.presenter;

import android.graphics.Bitmap;

import java.util.Collection;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.domain.GetMovieById;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.activity.Navigator;
import de.codebuster.florian.popularmovies.utils.ImageUtils;


public class MovieDetailPresenter extends Presenter {

    private GetMovieById getMovieByIdInteractor;
    private Navigator navigator;
    private View view;

    private Movie movie;

    @Inject
    public MovieDetailPresenter(GetMovieById getMovieByIdInteractor, Navigator navigator) {
        this.getMovieByIdInteractor = getMovieByIdInteractor;
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

    private void showMovie() {

        if (view.isReady()) {
            view.showLoading();
            view.setTitle(movie.getTitle());
            view.setDescription(movie.getOverview());
            view.setRating(movie.getVoteAverage() + " / " + "10");
            view.setReleaseYear(movie.getReleaseYear());
            view.showMoviePoster(ImageUtils.getUrl(185) + movie.getPosterPath());
            view.showMovieBackdrop(ImageUtils.getUrl(500) + movie.getBackdropPath());
            view.hideLoading();
        }
    }

    public void setView(View view) {
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

    private void loadVideos() {

    }

    public interface View {

        void showMoviePoster(String url);

        void showMovieBackdrop(String url);

        void setTitle (String title);

        void setDescription(String description);

        void setRating (String rating);

        void setReleaseYear (String releaseYear);

        void showReviews(Collection<Review> reviews);

        void showVideos(Collection<Video> videos);

        boolean isReady();

        void hideLoading();

        void showLoading();
    }
}
