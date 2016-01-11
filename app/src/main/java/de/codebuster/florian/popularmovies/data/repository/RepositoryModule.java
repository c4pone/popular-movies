package de.codebuster.florian.popularmovies.data.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.codebuster.florian.popularmovies.data.api.MoviesApi;

@Module(library = true, complete = false)
public final class RepositoryModule {

  @Provides @Singleton MoviesRepository provideMoviesRepository(MoviesApi moviesApi) {
    return new MoviesRepositoryImpl(moviesApi);
  }
}
