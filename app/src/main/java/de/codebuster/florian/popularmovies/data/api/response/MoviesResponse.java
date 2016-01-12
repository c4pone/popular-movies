package de.codebuster.florian.popularmovies.data.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import de.codebuster.florian.popularmovies.data.domain.movie.Movie;

public class MoviesResponse {

    @Expose
    private Integer page;

    @Expose
    @SerializedName("results")
    private List<Movie> movies = new ArrayList<>();

    @Expose
    @SerializedName("total_pages")
    private Integer totalPages;

    @Expose
    @SerializedName("total_results")
    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return movies;
    }

    public void setResults(List<Movie> movies) {
        this.movies = movies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}