package de.codebuster.florian.popularmovies.data.domain;

import dagger.Module;
import dagger.Provides;
import de.codebuster.florian.popularmovies.data.api.ApiModule;
import de.codebuster.florian.popularmovies.data.repository.RepositoryModule;

@Module(
        includes = {
                RepositoryModule.class, ApiModule.class
        }, library = true, complete = false)
public final class MoviesModule {

    @Provides
    GetMovies provideGetMoviesInteractor(GetMoviesInteractor interactor) {
        return interactor;
    }

    @Provides
    GetVideosById provideGetVideosbyIdInteractor(GetVideosByIdInteractor interactor) {
        return interactor;
    }

    @Provides
    GetReviewsById provideGetReviewsbyIdInteractor(GetReviewsByIdInteractor interactor) {
        return interactor;
    }

    @Provides
    FavouriteMovie provideFavouriteMovieInteractor(FavouriteMovieInteractor interactor) {
        return interactor;
    }
}
