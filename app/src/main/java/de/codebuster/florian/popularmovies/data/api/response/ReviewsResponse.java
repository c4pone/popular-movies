package de.codebuster.florian.popularmovies.data.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.codebuster.florian.popularmovies.data.domain.movie.Review;

public class ReviewsResponse {

    @Expose
    public int id;

    @Expose
    public int page;

    @Expose
    @SerializedName("results")
    public List<Review> reviews;

    @Expose
    @SerializedName("total_pages")
    public int totalPages;

    @Expose
    @SerializedName("total_results")
    public int totalResults;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Review> getResults() {
        return reviews;
    }

    public void setResults(List<Review> reviews) {
        this.reviews = reviews;
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
