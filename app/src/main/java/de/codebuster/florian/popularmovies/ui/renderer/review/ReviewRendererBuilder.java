package de.codebuster.florian.popularmovies.ui.renderer.review;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.domain.movie.Review;

public class ReviewRendererBuilder extends RendererBuilder<Review> {

    public ReviewRendererBuilder(Collection<Renderer<Review>> prototypes) {
        super(prototypes);
    }

    @Override
    protected Class getPrototypeClass(Review review) {
        return ReviewRenderer.class;
    }
}
