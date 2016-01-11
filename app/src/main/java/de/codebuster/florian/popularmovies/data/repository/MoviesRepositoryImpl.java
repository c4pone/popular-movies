package de.codebuster.florian.popularmovies.data.repository;

import java.util.List;

import de.codebuster.florian.popularmovies.data.api.MovieDiscoveryResponse;
import de.codebuster.florian.popularmovies.data.api.MoviesApi;
import de.codebuster.florian.popularmovies.data.api.Sort;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import retrofit.Call;

public class MoviesRepositoryImpl implements MoviesRepository {
    private MoviesApi moviesApi;

    MoviesRepositoryImpl(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }

    @Override
    public List<Movie> discoverMovies(Sort sort, int page) throws Exception {
        Call<MovieDiscoveryResponse> discoverMovies = this.moviesApi.discoverMovies(sort, page);
        MovieDiscoveryResponse movieDiscoveryResponse = discoverMovies.execute().body();

        return movieDiscoveryResponse.getResults();
    }

    @Override
    public List<Video> getVideos(Integer movieId) throws Exception {
        throw new Exception("NOT IMPLEMENTED YET");
    }

    @Override
    public List<Review> getReviews(Integer movieId) throws Exception {
        throw new Exception("NOT IMPLEMENTED YET");
    }
}
