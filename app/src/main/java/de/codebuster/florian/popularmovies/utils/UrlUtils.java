package de.codebuster.florian.popularmovies.utils;

import de.codebuster.florian.popularmovies.data.domain.movie.Video;

public class UrlUtils {

    public static String videoToUrl(Video video) {
        //TODO refactor this
        return "http://youtube.com/watch?v=" + video.getKey();
    }
}
