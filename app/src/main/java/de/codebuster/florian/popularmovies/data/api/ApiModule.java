/*
 * Copyright 2015.  Emin Yahyayev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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


    @Provides @Singleton @Named("Api")
    OkHttpClient provideApiClient(OkHttpClient client) {
        client.interceptors().add(new AddApiKeyInterceptor());
        return client;
    }

    @Provides @Singleton Retrofit provideRestAdapter(@Named("Api") OkHttpClient client) {

       return new Retrofit.Builder()
                .baseUrl(MOVIE_DB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides @Singleton MoviesApi provideMoviesApi(Retrofit retrofit) {
        return retrofit.create(MoviesApi.class);
    }
}