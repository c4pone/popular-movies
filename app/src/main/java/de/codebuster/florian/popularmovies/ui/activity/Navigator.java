package de.codebuster.florian.popularmovies.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.di.ActivityContext;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.fragment.MovieDetailFragment;
import de.codebuster.florian.popularmovies.utils.UrlUtils;

public class Navigator {

    private final Context activityContext;
    private MovieDetailFragment movieDetailFragment;

    @Inject
    public Navigator(@ActivityContext Context activityContext) {
        this.activityContext = activityContext;
    }

    public void openMovieDetails(Movie movie) {

        if (canInteractWithFragments()) {
            showMovieOnMovieDetailsFragment(movie);
        } else {
            openMovieDetailsActivity(movie);
        }
    }

    private FragmentManager getFragmentManager() {
        return ((FragmentActivity) activityContext).getSupportFragmentManager();
    }

    private boolean canInteractWithFragments() {
        movieDetailFragment = (MovieDetailFragment) getFragmentManager().findFragmentById(R.id.movie_detail_fragment);

        return movieDetailFragment != null;
    }

    private void showMovieOnMovieDetailsFragment(Movie movie) {
        if (isFragmentAvailable(movieDetailFragment)) {
            movieDetailFragment.showMovie(movie);
        }
    }

    /**
     * Check if the fragment is ready to be notified of a new Movie loaded.
     *
     * @return true if the Fragment instance is not null and is attached.
     */
    private boolean isFragmentAvailable(Fragment fragment) {
        return fragment != null && fragment.isAdded();
    }

    /**
     * Open MovieDetailActivity using a Movie.
     */
    public void openMovieDetailsActivity(Movie movie) {
        Intent intent = MovieDetailActivity.getLaunchIntent(activityContext, movie);
        startActivity(intent);
    }

    private void startActivity(Intent intent) {
        activityContext.startActivity(intent);
    }

    public void openVideo(Video video) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(UrlUtils.videoToUrl(video)));
        activityContext.startActivity(browserIntent);
    }
}
