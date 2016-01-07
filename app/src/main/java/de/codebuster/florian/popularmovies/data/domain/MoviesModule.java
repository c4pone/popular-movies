/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.codebuster.florian.popularmovies.data.domain;

import dagger.Module;
import dagger.Provides;
import de.codebuster.florian.popularmovies.data.api.ApiModule;
import de.codebuster.florian.popularmovies.data.executor.ExecutorModule;
import de.codebuster.florian.popularmovies.data.repository.RepositoryModule;

/**
 * Dagger module created to provide every domain dependencies as interactors or the main class of
 * this application: Catalog.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
@Module(
        includes = {
            RepositoryModule.class, ApiModule.class
        },library = true, complete = false)
public final class MoviesModule {

  @Provides GetMovies provideGetMoviesInteractor(GetMoviesInteractor interactor) {
    return interactor;
  }

  @Provides GetMovieById provideGetTvShowbyIdInteractor(GetMovieByIdInteractor interactor) {
    return interactor;
  }
}
