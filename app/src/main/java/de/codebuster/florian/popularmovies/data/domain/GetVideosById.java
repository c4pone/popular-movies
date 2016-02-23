package de.codebuster.florian.popularmovies.data.domain;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.domain.movie.Video;

public interface GetVideosById {

    void execute(final Long movieId, final Callback callback);

    interface Callback {
        void onVideosLoaded(final Collection<Video> videos);

        void onConnectionError();
    }
}