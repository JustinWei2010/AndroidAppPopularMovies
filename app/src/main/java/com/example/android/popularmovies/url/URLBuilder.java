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
    private static final String API_BASE_URL = "http://api.themoviedb.org/3/movie";
    private static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";

    public static String getMovieListURLName(final String sortPath) {
        return Uri.parse(API_BASE_URL)
                .buildUpon()
                .appendPath(sortPath)
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build()
                .toString();
    }

    public static String getFullPosterURL(final String posterPath) {
        return Uri.parse(POSTER_BASE_URL)
                .buildUpon()
                .appendEncodedPath(posterPath)
                .build()
                .toString();
    }
}
