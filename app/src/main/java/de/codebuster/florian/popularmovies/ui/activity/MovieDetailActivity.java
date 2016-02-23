package de.codebuster.florian.popularmovies.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import java.util.LinkedList;
import java.util.List;

import butterknife.OnClick;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.ui.fragment.MovieDetailFragment;
import de.codebuster.florian.popularmovies.ui.presenter.MovieUIModule;

public class MovieDetailActivity extends BaseActivity {

    private static final String EXTRA_MOVIE = "extra_movie";

    private MovieDetailFragment detailFragment;
    private Movie movie;

    public static Intent getLaunchIntent(final Context context, final Movie movie) {
        if (movie == null) {
            throwIllegalArgumentException();
        }

        Intent intent = new Intent(context, MovieDetailActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(EXTRA_MOVIE, movie);
        intent.putExtras(mBundle);

        return intent;
    }

    private static void throwIllegalArgumentException() {
        throw new IllegalArgumentException(
                "MovieDetailActivity has to be launched using a Movie identifier as extra");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (toolbar != null) {
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
                ab.setDisplayHomeAsUpEnabled(true);
                ab.setDisplayShowHomeEnabled(true);
            }
        }

        mapExtras();
        initializeFragment();
    }

    @Override
    protected List<Object> getModules() {
        List<Object> modules = new LinkedList<Object>();
        modules.add(new MovieUIModule());
        return modules;
    }

    private void mapExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            throwIllegalArgumentException();
        }
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if (movie == null) {
            throwIllegalArgumentException();
        }
    }

    private void initializeFragment() {
        detailFragment = (MovieDetailFragment) getSupportFragmentManager().findFragmentById(R.id.movie_detail_fragment);
        detailFragment.showMovie(movie);
    }

    @OnClick(R.id.movie_favourite_button)
    public void onFavouriteBtnClicked() {
        detailFragment.onMovieFavourite();
    }
}
