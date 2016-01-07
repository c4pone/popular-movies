package de.codebuster.florian.popularmovies.data.api;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import de.codebuster.florian.popularmovies.BuildConfig;

public class AddApiKeyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.httpUrl().newBuilder()
                .addQueryParameter("api_key", BuildConfig.MOVIE_API_KEY)
                .build();
        Request newRequest = chain.request().newBuilder().url(url).build();
        return chain.proceed(newRequest);
    }
}