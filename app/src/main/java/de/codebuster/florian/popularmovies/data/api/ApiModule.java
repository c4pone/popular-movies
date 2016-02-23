package de.codebuster.florian.popularmovies.data.api;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module(library = true, complete = false)
public final class ApiModule {
    public static final String MOVIE_DB_API_URL = "http://api.themoviedb.org/3";


    @Provides
    @Singleton
    @Named("Api")
    OkHttpClient provideApiClient() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new AddApiKeyInterceptor());
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRestAdapter(@Named("Api") OkHttpClient client) {

        return new Retrofit.Builder()
                .baseUrl(MOVIE_DB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    MoviesApi provideMoviesApi(Retrofit retrofit) {
        return retrofit.create(MoviesApi.class);
    }
}