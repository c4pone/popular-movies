package de.codebuster.florian.popularmovies.ui.renderer.video;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import de.codebuster.florian.popularmovies.data.domain.movie.Video;
import de.codebuster.florian.popularmovies.ui.renderer.SimpleCollection;

public class VideoCollection extends SimpleCollection<Video> implements Parcelable {

    public static final Creator<VideoCollection> CREATOR = new Creator<VideoCollection>() {
        public VideoCollection createFromParcel(Parcel in) {
            return new VideoCollection(in);
        }

        public VideoCollection[] newArray(int size) {
            return new VideoCollection[size];
        }
    };

    public VideoCollection() {
        this(new LinkedList<Video>());
    }

    public VideoCollection(Collection<? extends Video> collection) {
        this.list = new LinkedList<>();
        this.list.addAll(collection);
    }

    public VideoCollection(Parcel in) {
        List<Video> videos = new LinkedList();
        in.readList(videos, null);

        this.list = videos;
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
