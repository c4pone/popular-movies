package de.codebuster.florian.popularmovies.data.api;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public final class ApiBuilder {
    public static final String MOVIE_DB_API_URL = "http://api.themoviedb.org/";

    public static TheMoviesDbApi create() {

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new AddApiKeyInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_DB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(TheMoviesDbApi.class);
    }
}