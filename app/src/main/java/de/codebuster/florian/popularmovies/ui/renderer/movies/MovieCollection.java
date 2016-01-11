package de.codebuster.florian.popularmovies.ui.renderer.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.pedrogomez.renderers.AdapteeCollection;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;

public class MovieCollection implements AdapteeCollection<Movie>, Parcelable {
    private static final long serialVersionUID = 8799677673292716638L;

    private final List<Movie> movies;

    public MovieCollection() {
        this(new LinkedList<Movie>());
    }

    public MovieCollection(Collection<Movie> movies) {
        this.movies = new LinkedList<Movie>();
        this.movies.addAll(movies);
    }

    @Override public int size() {
        return movies.size();
    }

    @Override public Movie get(int index) {
        return movies.get(index);
    }

    @Override public void add(Movie movie) {
        movies.add(movie);
    }

    @Override public void remove(Movie movie) {
        movies.remove(movie);
    }

    @Override public void addAll(Collection<Movie> movies) {
        this.movies.addAll(movies);
    }

    @Override public void removeAll(Collection<Movie> movies) {
        this.movies.removeAll(movies);
    }

    public void clear() {
        movies.clear();
    }

    public List<Movie> getAsList() {
        return (List<Movie>) ((LinkedList<Movie>) movies).clone();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(movies);
    }
}
