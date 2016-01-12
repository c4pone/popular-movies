package de.codebuster.florian.popularmovies.ui.renderer.review;

import android.view.LayoutInflater;

import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.renderer.video.VideoCollection;
import de.codebuster.florian.popularmovies.ui.renderer.video.VideoRendererBuilder;

public class ReviewRendererAdapterFactory {
    private final ReviewRendererBuilder rendererBuilder;
    private final LayoutInflater layoutInflater;

    @Inject
    public ReviewRendererAdapterFactory(ReviewRendererBuilder rendererBuilder,
                                        LayoutInflater layoutInflater) {
        this.rendererBuilder = rendererBuilder;
        this.layoutInflater = layoutInflater;
    }

    public RendererAdapter<Review> getReviewRendererAdapter(final ReviewCollection reviews) {
        return new RendererAdapter<>(layoutInflater, rendererBuilder, reviews);
    }
}