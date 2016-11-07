package com.example.android.popularmovies.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Extract useful movie data from JSON object.
 */
public class MovieJSONParser {
    private static final String LOG_TAG = MovieJSONParser.class.getSimpleName();
    private static final String OWM_RESULTS = "results";
    private static final String OWM_MOVIE_ID = "id";
    private static final String OWM_POSTER_PATH = "poster_path";

    public static List<String> getPosterPathsFromJson(final String jsonStr) {
        final List<String> movieImagePaths = new LinkedList();
        try {
            final JSONObject json = new JSONObject(jsonStr);
            final JSONArray resultsArray = json.getJSONArray(OWM_RESULTS);

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject movieJson = resultsArray.getJSONObject(i);
                movieImagePaths.add(movieJson.getString(OWM_POSTER_PATH));
            }
        } catch (final JSONException e) {
            Log.e(LOG_TAG, "Error while parsing json", e);
        }
        Log.v(LOG_TAG, "weijusti movieImagePaths: " + movieImagePaths);
        return movieImagePaths;
    }
}
