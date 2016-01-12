package de.codebuster.florian.popularmovies.ui.renderer.review;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.ui.presenter.MovieDetailPresenter;

public class ReviewRenderer extends Renderer<Review> {


    private final Context context;
    private final MovieDetailPresenter movieDetailPresenter;

    @Bind(R.id.review_author)
    TextView author;

    @Bind(R.id.review_content)
    TextView content;

    @Inject
    public ReviewRenderer(Context context, MovieDetailPresenter movieDetailPresenter) {
        this.context = context;
        this.movieDetailPresenter = movieDetailPresenter;
    }

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.row_review, parent, false);
    }

    @OnClick(R.id.review_author)
    void onVideoClicked() {
        movieDetailPresenter.onReviewAuthorClicked(getContent());
    }

    @Override
    public void render() {
        Review review = getContent();
        renderAuthor(review);
        renderContent(review);
    }

    private void renderAuthor(Review review) {
        author.setText(review.getAuthor());
    }

    private void renderContent(Review review) {
        content.setText(review.getContent());
    }
}