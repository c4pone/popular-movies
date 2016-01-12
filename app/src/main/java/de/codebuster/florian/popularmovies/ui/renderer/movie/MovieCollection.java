package de.codebuster.florian.popularmovies.ui.renderer.movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.ui.renderer.SimpleCollection;

public class MovieCollection extends SimpleCollection<Movie> implements Parcelable {

    public static final Parcelable.Creator<MovieCollection> CREATOR = new Parcelable.Creator<MovieCollection>() {
        public MovieCollection createFromParcel(Parcel in) {
            return new MovieCollection(in);
        }

        public MovieCollection[] newArray(int size) {
            return new MovieCollection[size];
        }
    };

    public MovieCollection() {
        this(new LinkedList<Movie>());
    }

    public MovieCollection(Collection<Movie> collection) {
        this.list = new LinkedList<>();
        this.list.addAll(collection);
    }

    public MovieCollection(Parcel in) {
        List<Movie> movies = new LinkedList();
        in.readList(movies, null);

        this.list = movies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.list);
    }
}
