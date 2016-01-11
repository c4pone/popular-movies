package de.codebuster.florian.popularmovies.ui.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.activity.BaseActivity;
import de.codebuster.florian.popularmovies.ui.presenter.MovieDetailPresenter;
import de.codebuster.florian.popularmovies.utils.ImageUtils;

public class MovieDetailFragment extends BaseFragment implements MovieDetailPresenter.View {

    @Bind(R.id.movie_detail_poster)ImageView poster;
    //@Bind(R.id.backdrop) ImageView backdrop;
    @Bind(R.id.movie_detail_vote_average) TextView voteAverage;
    @Bind(R.id.movie_detail_description) TextView description;
    @Bind(R.id.movie_detail_year) TextView releaseYear;

    @Inject MovieDetailPresenter movieDetailPresenter;

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override public void onAttach(Activity activity) { super.onAttach(activity); }

    @Override public void onDetach() {
        super.onDetach();
    }

    public void showMovie(Movie movie) {
        if (isAdded()) {
            movieDetailPresenter.setMovie(movie);
            movieDetailPresenter.setView(this);
            movieDetailPresenter.initialize();
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    public void showMoviePoster(String posterUrl) {
        Picasso.with(getActivity()).load(posterUrl).placeholder(R.color.main_color).into(poster);
    }

    @Override
    public void showMovieBackdrop(String backdropUrl) {
        ImageView backdrop = (ImageView) getActivity().findViewById(R.id.backdrop);
        Picasso
                .with(getContext())
                .load(backdropUrl)
                .placeholder(R.color.main_color)
                .into(backdrop);
    }

    @Override
    public void setTitle(String title) {
        CollapsingToolbarLayout collapsingToolbar =
                                    (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(title);
    }

    @Override
    public void setDescription(String description) {
        this.description.setText(description);
    }

    @Override
    public void setRating(String rating) {
        this.voteAverage.setText(rating);
    }

    @Override
    public void setReleaseYear(String releaseYear) {
        this.releaseYear.setText(releaseYear);
    }

    @Override
    public void showReviews(Collection<Review> reviews) {

    }

    @Override
    public void showVideos(Collection<Video> videos) {

    }

    @Override
    public boolean isReady() {
        return isAdded();
    }

    @Override
    public void hideLoading() { }

    @Override
    public void showLoading() { }
}
