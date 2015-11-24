package de.codebuster.florian.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.model.Movie;
import de.codebuster.florian.popularmovies.helper.ImageHelper;

public class MovieListAdapter extends ArrayAdapter {
    @Bind(R.id.movie_poster_image) ImageView poster;

    private Context context;
    private LayoutInflater inflater;
    private List<Movie> movies;

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.grid_item_movie, movies);

        this.context = context;
        this.movies = movies;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (null == convertView) {
            convertView = inflater.inflate(R.layout.grid_item_movie, parent, false);
        }

        ButterKnife.bind(this, convertView);

        Movie movie = movies.get(position);
        if (movie != null) {

            Picasso
                    .with(context)
                    .load(ImageHelper.getUrl(185) + movie.getPosterPath())
                    .placeholder(R.drawable.poster_placeholder)
                    .error(R.drawable.poster_placeholder)
                    .fit()
                    .into(poster);
        }


        return convertView;
    }
}
