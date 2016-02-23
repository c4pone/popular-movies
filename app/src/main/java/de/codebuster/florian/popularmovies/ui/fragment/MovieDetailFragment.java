package de.codebuster.florian.popularmovies.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pedrogomez.renderers.RendererAdapter;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.activity.SettingsActivity;
import de.codebuster.florian.popularmovies.ui.presenter.MovieDetailPresenter;
import de.codebuster.florian.popularmovies.ui.presenter.view.MovieDetailView;
import de.codebuster.florian.popularmovies.ui.renderer.movie.MovieCollection;
import de.codebuster.florian.popularmovies.ui.renderer.review.ReviewCollection;
import de.codebuster.florian.popularmovies.ui.renderer.review.ReviewRendererAdapterFactory;
import de.codebuster.florian.popularmovies.ui.renderer.video.VideoCollection;
import de.codebuster.florian.popularmovies.ui.renderer.video.VideoRendererAdapterFactory;
import de.codebuster.florian.popularmovies.utils.UrlUtils;

public class MovieDetailFragment extends BaseFragment implements MovieDetailView {

    private static final String EXTRA_VIDEOS = "extra_videos";
    private static final String EXTRA_REVIEWS = "extra_reviews";

    @Bind(R.id.movie_detail_poster)
    ImageView poster;
    @Bind(R.id.movie_detail_vote_average)
    TextView voteAverage;
    @Bind(R.id.movie_detail_description)
    TextView description;
    @Bind(R.id.movie_detail_year)
    TextView releaseYear;
    @Bind(R.id.movie_detail_video_list)
    ListView video_list;
    @Bind(R.id.movie_detail_review_list)
    ListView review_list;
    @Bind(R.id.pb_loading)
    ProgressBar progressBar;
    @Bind(R.id.movie_detail_container)
    LinearLayout movieDetailContainer;

    @Bind(R.id.movie_detail_title) TextView title;

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    @Inject
    VideoRendererAdapterFactory videoRendererAdapterFactory;
    @Inject
    ReviewRendererAdapterFactory reviewRendererAdapterFactory;

    private RendererAdapter<Video> videoRendererAdapter;
    private RendererAdapter<Review> reviewRendererAdapter;
    private VideoCollection videoCollection = new VideoCollection();
    private ReviewCollection reviewCollection = new ReviewCollection();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeVideoList();
        initializeReviewList();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.details_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v("test", "An option got selected");

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        movieDetailPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        movieDetailPresenter.pause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_VIDEOS, movieDetailPresenter.getCurrentVideos());
        outState.putParcelable(EXTRA_REVIEWS, movieDetailPresenter.getCurrentReviews());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            final VideoCollection videoCollection = savedInstanceState.getParcelable(EXTRA_VIDEOS);
            updatePresenterWithSavedVideos(videoCollection);

            final ReviewCollection reviewCollection = savedInstanceState.getParcelable(EXTRA_REVIEWS);
            updatePresenterWithSavedReviews(reviewCollection);
        }
    }

    //Called from the Navigator
    public void showMovie(Movie movie) {
        if (isAdded()) {
            movieDetailPresenter.setMovie(movie);
            movieDetailPresenter.setView(this);
            movieDetailPresenter.initialize();
        }

        movieDetailContainer.setVisibility(View.VISIBLE);
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
        if (backdrop != null) {
            Picasso
                .with(getContext())
                .load(backdropUrl)
                .placeholder(R.color.main_color)
                .into(backdrop);
        }
    }

    @Override
    public void setTitle(String title) {
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        if (collapsingToolbar != null) {
            collapsingToolbar.setTitle(title);
        }

        this.title.setText(title);
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
    public void renderReviews(Collection<Review> reviews) {
        this.reviewCollection.clear();
        this.reviewCollection.addAll(reviews);
        refreshReviewAdapter();
    }


    @Override
    public void renderVideos(Collection<Video> videos) {
        this.videoCollection.clear();
        this.videoCollection.addAll(videos);
        refreshVideoAdapter();
    }

    @Override
    public boolean isReady() {
        return isAdded();
    }

    @Override
    public void showConnectionErrorMessage() {

    }

    private void initializeVideoList() {
        videoRendererAdapter = videoRendererAdapterFactory.getVideoRendererAdapter(videoCollection);
        video_list.setAdapter(videoRendererAdapter);
    }

    private void initializeReviewList() {
        reviewRendererAdapter = reviewRendererAdapterFactory.getReviewRendererAdapter(reviewCollection);
        review_list.setAdapter(reviewRendererAdapter);
    }

    private void updatePresenterWithSavedVideos(VideoCollection videoCollection) {
        if (videoCollection != null) {
            movieDetailPresenter.loadVideos(videoCollection);
        }
    }

    private void updatePresenterWithSavedReviews(ReviewCollection reviewCollection) {
        if (reviewCollection != null) {
            movieDetailPresenter.loadReviews(reviewCollection);
        }
    }

    private void refreshVideoAdapter() {
        videoRendererAdapter.notifyDataSetChanged();
    }

    private void refreshReviewAdapter() {
        reviewRendererAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void onMovieFavourite() {
        Log.v("test","movie favourite");
        movieDetailPresenter.onMovieFavourite();
    }
}
