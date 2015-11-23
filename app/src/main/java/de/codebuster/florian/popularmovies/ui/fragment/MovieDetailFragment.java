package de.codebuster.florian.popularmovies.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.model.Movie;
import de.codebuster.florian.popularmovies.helper.ImageHelper;

public class MovieDetailFragment extends Fragment {

    private static final String ARG_PARAM = "MOVIE";
    private Movie movie;

    public MovieDetailFragment() {
        movie = new Movie();
    }

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = (Movie) getArguments().getSerializable(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        ImageView poster = (ImageView) view.findViewById(R.id.movie_detail_poster);

        Picasso
                .with(getContext())
                .load(ImageHelper.getUrl(185) + movie.getPosterPath())
                .fit()
                .into(poster);

        TextView year = (TextView) view.findViewById(R.id.movie_detail_year);
        year.setText(movie.getReleaseYear());

        TextView vote_average = (TextView) view.findViewById(R.id.movie_detail_vote_average);
        vote_average.setText(movie.getVoteAverage() + " / " + "10");

        TextView description = (TextView) view.findViewById(R.id.movie_detail_description);
        description.setText(movie.getOverview());

        return view;
    }
}
