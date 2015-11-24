package de.codebuster.florian.popularmovies.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.model.Movie;
import de.codebuster.florian.popularmovies.ui.WantsMovies;
import de.codebuster.florian.popularmovies.ui.activity.MovieDetailActivity;
import de.codebuster.florian.popularmovies.ui.adapter.MovieListAdapter;
import de.codebuster.florian.popularmovies.ui.task.GetPopularMoviesTask;
import icepick.Icepick;
import icepick.State;


public class MoviesFragment extends Fragment implements WantsMovies {

    private ArrayAdapter movieAdapter;

    @State ArrayList<Movie> movies;

    @BindString(R.string.pref_sort_key) String prefSortKey;
    @BindString(R.string.pref_sort_default) String prefSortDefault;
    @Bind(R.id.popular_movies_grid) GridView moviesGrid;

    @Override
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;

        fillAdapterWithMovies(movies);
    }

    public void fillAdapterWithMovies(ArrayList<Movie> movies)
    {
        movieAdapter.clear();
        for (Movie movie : movies) {
            movieAdapter.add(movie);
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (movies == null) {
            getMovies();
        } else {
            fillAdapterWithMovies(movies);
        }
    }

    /**
     * Refresh the movie list
     */
    public void getMovies() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = prefs.getString(prefSortKey, prefSortDefault);

        GetPopularMoviesTask getPopularMoviesTask = new GetPopularMoviesTask(this);
        getPopularMoviesTask.execute(sortOrder);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);

        movieAdapter = new MovieListAdapter(getActivity(), new ArrayList<Movie>());

        moviesGrid.setAdapter(movieAdapter);
        moviesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Movie movie = (Movie) movieAdapter.getItem(position);

                Intent detailIntent = new Intent(getActivity(), MovieDetailActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(Movie.class.toString(), movie);
                detailIntent.putExtras(mBundle);

                startActivity(detailIntent);
            }
        });

        return view;
    }
}