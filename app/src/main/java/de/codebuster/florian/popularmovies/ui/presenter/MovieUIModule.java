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
package de.codebuster.florian.popularmovies.ui.presenter;

import com.pedrogomez.renderers.Renderer;

import java.util.LinkedList;

import dagger.Module;
import dagger.Provides;
import de.codebuster.florian.popularmovies.data.domain.movie.Movie;
import de.codebuster.florian.popularmovies.ui.activity.MainActivity;
import de.codebuster.florian.popularmovies.ui.activity.MovieDetailActivity;
import de.codebuster.florian.popularmovies.ui.fragment.MovieDetailFragment;
import de.codebuster.florian.popularmovies.ui.fragment.MoviesFragment;
import de.codebuster.florian.popularmovies.ui.renderer.movies.MovieRenderer;
import de.codebuster.florian.popularmovies.ui.renderer.movies.MovieRendererBuilder;

@Module(complete = false,
    injects = {
        MainActivity.class, MoviesFragment.class,
            MovieDetailActivity.class, MovieDetailFragment.class
    }) public final class MovieUIModule {

  @Provides
  MovieRendererBuilder provideMovieRendererBuilder(MovieRenderer movieRenderer) {
    LinkedList<Renderer<Movie>> renderers = new LinkedList<Renderer<Movie>>();
    renderers.add(movieRenderer);
    return new MovieRendererBuilder(renderers);
  }
}
