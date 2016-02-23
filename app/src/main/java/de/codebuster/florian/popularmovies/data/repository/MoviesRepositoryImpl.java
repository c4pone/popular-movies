package de.codebuster.florian.popularmovies.data.repository;

import java.util.List;

import de.codebuster.florian.popularmovies.data.api.MoviesApi;
import de.codebuster.florian.popularmovies.data.api.Sort;
import de.codebuster.florian.popularmovies.data.api.response.MoviesResponse;
import de.codebuster.florian.popularmovies.data.api.response.ReviewsResponse;
import de.codebuster.florian.popularmovies.data.api.response.VideosResponse;
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
        Call<MoviesResponse> discoverMovies = this.moviesApi.discoverMovies(sort, page);
        MoviesResponse movieResponse = discoverMovies.execute().body();

        return movieResponse.getResults();
    }

    @Override
    public List<Video> getVideos(Long movieId) throws Exception {
        Call<VideosResponse> videosResponseCall = this.moviesApi.videos(movieId);
        VideosResponse videosResponse = videosResponseCall.execute().body();

        return videosResponse.getResults();
    }

    @Override
    public List<Review> getReviews(Long movieId, int page) throws Exception {
        Call<ReviewsResponse> reviewsResponseCall = this.moviesApi.reviews(movieId, page);
        ReviewsResponse reviewsResponse = reviewsResponseCall.execute().body();

        return reviewsResponse.getResults();
    }

    @Override
    public void save(Movie movie) throws Exception {
//        if (movie.save() <= 0) {
//            throw new Exception("Could not save the movie");
//        }
    }

    @Override
    public void delete(Movie movie) throws Exception {
//        if ( ! movie.delete()) {
//            throw new Exception("Could not delete the movie");
//        }
    }
}
