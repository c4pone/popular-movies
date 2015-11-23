package de.codebuster.florian.popularmovies.ui.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.codebuster.florian.popularmovies.data.api.ApiBuilder;
import de.codebuster.florian.popularmovies.data.api.TheMoviesDbApi;
import de.codebuster.florian.popularmovies.data.model.Movie;
import de.codebuster.florian.popularmovies.data.model.MovieDiscoveryResponse;
import de.codebuster.florian.popularmovies.ui.WantsMovies;
import retrofit.Call;


public class GetPopularMoviesTask extends AsyncTask<String, Void, List<Movie>> {
    private final String LOG_TAG = GetPopularMoviesTask.class.getSimpleName();

    private WantsMovies wantsMovies;

    public GetPopularMoviesTask(WantsMovies wantsMovies) {
        this.wantsMovies = wantsMovies;
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        if (params.length == 0) {
            return new ArrayList<Movie>();
        }

        MovieDiscoveryResponse movieDiscoveryResponse = new MovieDiscoveryResponse();

        try {
            TheMoviesDbApi api = ApiBuilder.create();
            Call<MovieDiscoveryResponse> discoverMovies = api.discoverMovies(params[0]);
            movieDiscoveryResponse = discoverMovies.execute().body();

        } catch (IOException e) {
            Log.e(LOG_TAG, "Background task failed", e);
        }

        return movieDiscoveryResponse.getResults();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);

        this.wantsMovies.setMovies(movies);
    }
}
