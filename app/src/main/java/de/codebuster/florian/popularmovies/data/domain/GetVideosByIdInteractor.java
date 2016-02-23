package de.codebuster.florian.popularmovies.data.domain;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.data.executor.Executor;
import de.codebuster.florian.popularmovies.data.executor.Interactor;
import de.codebuster.florian.popularmovies.data.executor.MainThread;
import de.codebuster.florian.popularmovies.data.repository.MoviesRepository;

public class GetVideosByIdInteractor implements Interactor, GetVideosById {

    private final MoviesRepository moviesRepository;
    private final Executor executor;
    private final MainThread mainThread;

    private Callback callback;
    private Long movieId;

    @Inject
    GetVideosByIdInteractor(
            MoviesRepository moviesRepository,
            Executor executor,
            MainThread mainThread) {
        this.moviesRepository = moviesRepository;
        this.executor = executor;
        this.mainThread = mainThread;
    }

    @Override
    public void execute(Long movieId, Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException(
                    "Callback can't be null, the client of this interactor needs to get the response "
                            + "in the callback");
        }

        if (movieId == null) {
            throw new IllegalArgumentException("The movie id can't be null");
        }

        this.movieId = movieId;
        this.callback = callback;
        this.executor.run(this);
    }

    @Override
    public void run() {
        try {
            List<Video> videos = moviesRepository.getVideos(movieId);
            nofityVideosLoaded(videos);
        } catch (Exception e) {
            notifyError();
        }
    }

    private void notifyError() {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onConnectionError();
            }
        });
    }

    private void nofityVideosLoaded(final Collection<Video> videos) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onVideosLoaded(videos);
            }
        });
    }
}
