package de.codebuster.florian.popularmovies.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.codebuster.florian.popularmovies.R;
import de.codebuster.florian.popularmovies.data.model.Movie;
import de.codebuster.florian.popularmovies.helper.ImageHelper;
import de.codebuster.florian.popularmovies.ui.fragment.MovieDetailFragment;

public class MovieDetailActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.backdrop) ImageView backdrop;

    ShareActionProvider shareActionProvider;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            movie = (Movie)getIntent().getParcelableExtra(Movie.class.toString());

            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            collapsingToolbar.setTitle(movie.getTitle());

            Picasso
                    .with(this)
                    .load(ImageHelper.getUrl(500) + movie.getBackdropPath())
                    .fit()
                    .into(backdrop);


            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = MovieDetailFragment.newInstance(movie);
            ft.replace(R.id.content, fragment);
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);


        MenuItem menuItem = menu.findItem(R.id.action_share);
        // Get the provider and hold onto it to set/change the share intent.

        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        String shareText = getString(R.string.share_message, movie.getTitle());
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>" + shareText + "</p>"));
        startActivity(Intent.createChooser(sharingIntent, "Share using"));

        shareActionProvider.setShareIntent(sharingIntent);


        return true;
    }
}
