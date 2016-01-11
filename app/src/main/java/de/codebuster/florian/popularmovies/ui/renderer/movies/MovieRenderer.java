package de.codebuster.florian.popularmovies.ui.renderer.movies;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.ui.presenter.MoviesPresenter;
import de.codebuster.florian.popularmovies.utils.ImageUtils;

public class MovieRenderer extends Renderer<Movie> {


    private final Context context;
    private final MoviesPresenter moviesPresenter;

    @Bind(R.id.iv_thumbnail) ImageView thumbnailImageView;
    @Bind(R.id.tv_title) TextView titleTextView;

    @Inject
    public MovieRenderer(Context context, MoviesPresenter moviesPresenter) {
        this.context = context;
        this.moviesPresenter = moviesPresenter;
    }

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {
        //Empty because we are using ButterKnife library
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.grid_item_movie, parent, false);
    }

    @Override
    public void render() {
        Movie movie = getContent();
        renderThumbnail(movie);
        renderTitle(movie);
    }

    @OnClick(R.id.iv_thumbnail)
    void onThumbnailClicked() {
        moviesPresenter.onMovieThumbnailClicked(getContent());
    }

    @OnClick(R.id.v_row_container)
    void onBackgroundClicked() {
        moviesPresenter.onMovieClicked(getContent());
    }

    private Movie renderThumbnail(Movie movie) {
        Picasso.with(context)
                .load(ImageUtils.getUrl(185) + movie.getPosterPath())
                .into(thumbnailImageView);
        return movie;
    }

    private void renderTitle(Movie movie) {
        titleTextView.setText(movie.getTitle().toUpperCase());
    }
}