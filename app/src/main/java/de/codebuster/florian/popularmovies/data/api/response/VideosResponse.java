package de.codebuster.florian.popularmovies.data.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.codebuster.florian.popularmovies.data.domain.movie.Video;

public class VideosResponse {
    @Expose
    public int id;

    @Expose
    @SerializedName("results")
    public List<Video> videos;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return videos;
    }

    public void setResults(List<Video> videos) {
        this.videos = videos;
    }
}
