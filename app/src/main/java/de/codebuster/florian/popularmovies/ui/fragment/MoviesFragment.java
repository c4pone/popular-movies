package de.codebuster.florian.popularmovies.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.model.Movie;
import de.codebuster.florian.popularmovies.ui.WantsMovies;
import de.codebuster.florian.popularmovies.ui.activity.MovieDetailActivity;
import de.codebuster.florian.popularmovies.ui.adapter.MovieListAdapter;
import de.codebuster.florian.popularmovies.ui.task.GetPopularMoviesTask;


public class MoviesFragment extends Fragment implements WantsMovies {

    private final String LOG_TAG = MoviesFragment.class.getSimpleName();
    private ArrayAdapter movieAdapter;

    @Override
    public void setMovies(List<Movie> movies) {
        movieAdapter.clear();
        for (Movie movie : movies) {
            movieAdapter.add(movie);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    /**
     * Refresh the movie list
     */
    public void refresh() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = prefs.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_default));

        GetPopularMoviesTask getPopularMoviesTask = new GetPopularMoviesTask(this);
        getPopularMoviesTask.execute(sortOrder);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_movies, container, false);

        movieAdapter = new MovieListAdapter(getActivity(), new ArrayList<Movie>());

        GridView gridView = (GridView) v.findViewById(R.id.popular_movies_grid);
        gridView.setAdapter(movieAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Movie movie = (Movie) movieAdapter.getItem(position);

                Intent detailIntent = new Intent(getActivity(), MovieDetailActivity.class);
                detailIntent.putExtra(Intent.EXTRA_TEXT, movie);
                startActivity(detailIntent);
            }
        });

        return v;
    }
}