package de.codebuster.florian.popularmovies.ui.renderer.video;


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
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.presenter.MovieDetailPresenter;

public class VideoRenderer extends Renderer<Video> {


    private final Context context;
    private final MovieDetailPresenter movieDetailPresenter;

    @Bind(R.id.video_title)
    TextView video_title;

    @Inject
    public VideoRenderer(Context context, MovieDetailPresenter movieDetailPresenter) {
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
        return inflater.inflate(R.layout.row_video, parent, false);
    }

    @Override
    public void render() {
        Video video = getContent();
        renderTitle(video);
    }

    @OnClick(R.id.video_title_container)
    void onVideoClicked() {
        movieDetailPresenter.onVideoClicked(getContent());
    }

    private void renderTitle(Video video) {
        video_title.setText(video.getName());
    }
}