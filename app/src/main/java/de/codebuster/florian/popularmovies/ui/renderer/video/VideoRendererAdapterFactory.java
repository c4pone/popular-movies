package de.codebuster.florian.popularmovies.ui.renderer.video;

import android.view.LayoutInflater;

import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.domain.movie.Video;

public class VideoRendererAdapterFactory {
    private final VideoRendererBuilder rendererBuilder;
    private final LayoutInflater layoutInflater;

    @Inject
    public VideoRendererAdapterFactory(VideoRendererBuilder rendererBuilder,
                                       LayoutInflater layoutInflater) {
        this.rendererBuilder = rendererBuilder;
        this.layoutInflater = layoutInflater;
    }

    public RendererAdapter<Video> getVideoRendererAdapter(final VideoCollection videos) {
        return new RendererAdapter<>(layoutInflater, rendererBuilder, videos);
    }
}