package de.codebuster.florian.popularmovies.ui.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.pedrogomez.renderers.RendererAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.api.Sort;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.ui.presenter.MoviesPresenter;
import de.codebuster.florian.popularmovies.ui.presenter.view.MoviesView;
import de.codebuster.florian.popularmovies.ui.renderer.movie.MovieCollection;
import de.codebuster.florian.popularmovies.ui.renderer.movie.MovieRendererAdapterFactory;
import de.codebuster.florian.popularmovies.utils.ToastUtils;

public class MoviesFragment extends BaseFragment implements MoviesView {

    private static final String EXTRA_MOVIES = "extra_movies";

    @Bind(R.id.pb_loading)
    ProgressBar pb_loading;
    @Bind(R.id.popular_movies_grid)
    GridView movies_grid;
    @Bind(R.id.v_empty_case)
    View v_empty_case;
    @BindString(R.string.connection_error_message)
    String connectionErrorMessage;
    @BindString(R.string.pref_sort_key)
    String prefSortKey;
    @BindString(R.string.pref_sort_default)
    String prefSortDefault;

    @Inject
    MoviesPresenter moviesPresenter;
    @Inject
    MovieRendererAdapterFactory movieRendererAdapterFactory;

    private RendererAdapter<Movie> adapter;
    private MovieCollection movies = new MovieCollection();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeGridView();
        initializeMoviesPresenter();
    }

    private void initializeMoviesPresenter() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = prefs.getString(prefSortKey, prefSortDefault);

        moviesPresenter.setView(this);
        moviesPresenter.setSortOrder(Sort.fromString(sortOrder));
        moviesPresenter.initialize();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        moviesPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        moviesPresenter.pause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_MOVIES, moviesPresenter.getCurrentMovies());
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            final MovieCollection movieCollection = savedInstanceState.getParcelable(EXTRA_MOVIES);
            updatePresenterWithSavedMovies(movieCollection);
        }
    }

    @Override
    public void hideLoading() {
        pb_loading.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        pb_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void renderMovies(final Collection<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        refreshAdapter();
    }

    @Override
    public void showConnectionErrorMessage() {
        ToastUtils.showShortMessage(connectionErrorMessage, getActivity());
    }

    @Override
    public void showEmptyCase() {
        v_empty_case.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDefaultTitle() {
        getActivity().setTitle(R.string.app_name);
    }

    @Override
    public void showMovieTitleAsMessage(Movie movie) {
        ToastUtils.showShortMessage(movie.getTitle(), getActivity());
    }

    @Override
    public boolean isReady() {
        return isAdded();
    }

    @Override
    public boolean isAlreadyLoaded() {
        return adapter.getCount() > 0;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_movies;
    }

    private void initializeGridView() {
        adapter = movieRendererAdapterFactory.getMovieRendererAdapter(movies);
        movies_grid.setAdapter(adapter);
    }

    private void updatePresenterWithSavedMovies(MovieCollection movieCollection) {
        if (movieCollection != null) {
            moviesPresenter.loadMovies(movieCollection);
        }
    }

    private void refreshAdapter() {
        adapter.notifyDataSetChanged();
    }
}
