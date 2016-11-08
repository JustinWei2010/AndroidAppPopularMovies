package com.example.android.popularmovies.url;

import android.net.Uri;

/**
* Helper class to create urls.
        */
public class URLBuilder {
    private static final String LOG_TAG = URLBuilder.class.getSimpleName();
    //TODO: Insert API_KEY here.
    private static final String API_KEY = "";
    private static final String API_KEY_PARAM = "api_key";
    private static final String MOVIE_API_BASE_URL = "http://api.themoviedb.org/3/movie";
    private static final String MOVIE_POSTER_QUALITY = "w185";
    private static final String MOVIE_POSTER_BASE_URL = "http://image.tmdb.org/t/p";

    public static String getMovieApiURL(final String path) {
        return Uri.parse(MOVIE_API_BASE_URL)
                .buildUpon()
                .appendPath(path)
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build()
                .toString();
    }

    public static String getMoviePosterURL(final String posterPath) {
        return Uri.parse(MOVIE_POSTER_BASE_URL)
                .buildUpon()
                .appendPath(MOVIE_POSTER_QUALITY)
                .appendEncodedPath(posterPath)
                .build()
                .toString();
    }
}
