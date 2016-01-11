package de.codebuster.florian.popularmovies.ui.renderer.movies;

import android.view.LayoutInflater;
import com.pedrogomez.renderers.RendererAdapter;
import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;

public class MovieRendererAdapterFactory {
    private final MovieRendererBuilder rendererBuilder;
    private final LayoutInflater layoutInflater;

    @Inject
    public MovieRendererAdapterFactory(MovieRendererBuilder rendererBuilder,
                                        LayoutInflater layoutInflater) {
        this.rendererBuilder = rendererBuilder;
        this.layoutInflater = layoutInflater;
    }

    public RendererAdapter<Movie> getMovieRendererAdapter(final MovieCollection movies) {
        return new RendererAdapter<>(layoutInflater, rendererBuilder, movies);
    }
}