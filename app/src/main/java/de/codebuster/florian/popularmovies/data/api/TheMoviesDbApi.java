package de.codebuster.florian.popularmovies.data.api;

import de.codebuster.florian.popularmovies.data.model.MovieDiscoveryResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface TheMoviesDbApi {
    @GET("/3/discover/movie")
    Call<MovieDiscoveryResponse> discoverMovies(@Query("sort_by") String sort);
}



