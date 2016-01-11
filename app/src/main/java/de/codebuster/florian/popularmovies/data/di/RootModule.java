package de.codebuster.florian.popularmovies.data.di;

import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import de.codebuster.florian.popularmovies.PopularMoviesApplication;
import de.codebuster.florian.popularmovies.data.executor.ExecutorModule;
import de.codebuster.florian.popularmovies.data.domain.MoviesModule;

@Module(
        includes = {
                ExecutorModule.class, MoviesModule.class
        },
        injects = {
                PopularMoviesApplication.class
        }, library = true)

public final class RootModule {

    private final Context context;

    public RootModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }
}
