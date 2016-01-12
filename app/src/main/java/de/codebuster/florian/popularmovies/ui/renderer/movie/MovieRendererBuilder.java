package de.codebuster.florian.popularmovies.ui.renderer.movie;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;

public class MovieRendererBuilder extends RendererBuilder<Movie> {

    public MovieRendererBuilder(Collection<Renderer<Movie>> prototypes) {
        super(prototypes);
    }

    @Override
    protected Class getPrototypeClass(Movie movie) {
        return MovieRenderer.class;
    }
}
