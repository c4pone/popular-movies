package de.codebuster.florian.popularmovies.helper;

public class ImageHelper {

    public static String getUrl(Integer width) {
        String url = "http://image.tmdb.org/t/p";

        if (width <= 92)
            url += "/w92";
        else if (width <= 154)
            url += "/w154";
        else if (width <= 185)
            url += "/w185";
        else if (width <= 342)
            url += "/w342";
        else if (width <= 500)
            url += "/w500";
        else
            url += "/w780";

        return url;
    }
}
