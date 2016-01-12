package de.codebuster.florian.popularmovies.ui.renderer.review;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.codebuster.florian.popularmovies.data.domain.movie.Review;
import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.renderer.SimpleCollection;

public class ReviewCollection extends SimpleCollection<Review> implements Parcelable {

    public static final Creator<ReviewCollection> CREATOR = new Creator<ReviewCollection>() {
        public ReviewCollection createFromParcel(Parcel in) {
            return new ReviewCollection(in);
        }

        public ReviewCollection[] newArray(int size) {
            return new ReviewCollection[size];
        }
    };

    public ReviewCollection() {
        this(new LinkedList<Review>());
    }

    public ReviewCollection(Collection<? extends Review> collection) {
        this.list = new LinkedList<>();
        this.list.addAll(collection);
    }

    public ReviewCollection(Parcel in) {
        List<Review> reviews = new LinkedList();
        in.readList(reviews, null);

        this.list = reviews;
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
