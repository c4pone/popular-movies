package de.codebuster.florian.popularmovies.data.domain;

import java.util.Collection;

import de.codebuster.florian.popularmovies.data.domain.movie.Video;

public interface GetVideosById {

    interface Callback {
        void onVideosLoaded(final Collection<Video> videos);

        void onConnectionError();
    }

    void execute(final Integer movieId, final Callback callback);
}