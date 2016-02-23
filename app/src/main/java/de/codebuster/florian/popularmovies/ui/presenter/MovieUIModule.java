package de.codebuster.florian.popularmovies.ui.presenter;

import com.pedrogomez.renderers.Renderer;

import java.util.LinkedList;

import dagger.Module;
import dagger.Provides;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.activity.MainActivity;
import de.codebuster.florian.popularmovies.ui.activity.MovieDetailActivity;
import de.codebuster.florian.popularmovies.ui.fragment.MovieDetailFragment;
import de.codebuster.florian.popularmovies.ui.fragment.MoviesFragment;
import de.codebuster.florian.popularmovies.ui.renderer.movie.MovieRenderer;
import de.codebuster.florian.popularmovies.ui.renderer.movie.MovieRendererBuilder;
import de.codebuster.florian.popularmovies.ui.renderer.review.ReviewRenderer;
import de.codebuster.florian.popularmovies.ui.renderer.review.ReviewRendererBuilder;
import de.codebuster.florian.popularmovies.ui.renderer.video.VideoRenderer;
import de.codebuster.florian.popularmovies.ui.renderer.video.VideoRendererBuilder;

@Module(complete = false,
        injects = {
                MainActivity.class, MoviesFragment.class,
                MovieDetailActivity.class, MovieDetailFragment.class
        })
public final class MovieUIModule {

    @Provides
    MovieRendererBuilder provideMovieRendererBuilder(MovieRenderer movieRenderer) {
        LinkedList<Renderer<Movie>> renderers = new LinkedList<Renderer<Movie>>();
        renderers.add(movieRenderer);
        return new MovieRendererBuilder(renderers);
    }

    @Provides
    VideoRendererBuilder provideVideoRendererBuilder(VideoRenderer videoRenderer) {
        LinkedList<Renderer<Video>> renderers = new LinkedList<Renderer<Video>>();
        renderers.add(videoRenderer);
        return new VideoRendererBuilder(renderers);
    }

    @Provides
    ReviewRendererBuilder provideReviewRendererBuilder(ReviewRenderer reviewRenderer) {
        LinkedList<Renderer<Review>> renderers = new LinkedList<Renderer<Review>>();
        renderers.add(reviewRenderer);
        return new ReviewRendererBuilder(renderers);
    }
}
