package de.codebuster.florian.popularmovies.data.api;

import de.codebuster.florian.popularmovies.data.api.response.MoviesResponse;
import de.codebuster.florian.popularmovies.data.api.response.ReviewsResponse;
import de.codebuster.florian.popularmovies.data.api.response.VideosResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MoviesApi {

    @GET("/3/discover/movie")
    Call<MoviesResponse> discoverMovies(
            @Query("sort_by") Sort sort,
            @Query("page") int page);

    @GET("/3/movie/{id}/videos")
    Call<VideosResponse> videos(
            @Path("id") Long movieId);

    @GET("/3/movie/{id}/reviews")
    Call<ReviewsResponse> reviews(
            @Path("id") Long movieId,
            @Query("page") int page);
}



