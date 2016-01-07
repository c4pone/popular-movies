package de.codebuster.florian.popularmovies.data.api;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MoviesApi {

    @GET("/3/discover/movie") Call<MovieDiscoveryResponse> discoverMovies(
            @Query("sort_by") Sort sort,
            @Query("page") int page);
}



