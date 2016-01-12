package de.codebuster.florian.popularmovies.ui.renderer.video;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.domain.movie.Video;

public class VideoRendererBuilder extends RendererBuilder<Video> {

    public VideoRendererBuilder(Collection<Renderer<Video>> prototypes) {
        super(prototypes);
    }

    @Override
    protected Class getPrototypeClass(Video video) {
        return VideoRenderer.class;
    }
}
