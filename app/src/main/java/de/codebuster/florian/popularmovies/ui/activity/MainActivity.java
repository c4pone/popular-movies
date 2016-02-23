package de.codebuster.florian.popularmovies.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.List;

import butterknife.OnClick;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.ui.fragment.MovieDetailFragment;
import de.codebuster.florian.popularmovies.ui.fragment.MoviesFragment;
import de.codebuster.florian.popularmovies.ui.presenter.MovieUIModule;

public class MainActivity extends BaseActivity {

    private MoviesFragment moviesFragment;
    private MovieDetailFragment movieDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeMoviesFragment();
        initializeMovieDetailFragment();
    }

    private void initializeMovieDetailFragment() {
        movieDetailFragment = (MovieDetailFragment) getSupportFragmentManager().findFragmentById(R.id.movie_detail_fragment);
    }

    private void initializeMoviesFragment() {
        moviesFragment = (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.popular_movies_fragment);
    }

    @Override
    protected List<Object> getModules() {
        LinkedList<Object> modules = new LinkedList<>();
        modules.add(new MovieUIModule());
        return modules;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable @OnClick(R.id.movie_favourite_button)
    public void onFavouriteBtnClicked() {
        if (isTwoPane()) {
            movieDetailFragment.onMovieFavourite();
        }
    }

    private boolean isTwoPane() {
        return movieDetailFragment != null;
    }
}
