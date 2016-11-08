package com.example.android.popularmovies.json;

import android.util.Log;

import com.example.android.popularmovies.model.MovieDetailsModel;
import com.example.android.popularmovies.model.MoviePosterModel;

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
    private static final String OWM_ORIGINAL_TITLE = "original_title";
    private static final String OWM_POSTER_PATH = "poster_path";
    private static final String OWM_OVERVIEW = "overview";
    private static final String OWM_VOTE_AVERAGE = "vote_average";
    private static final String OWM_RELEASE_DATE = "release_date";

    public static List<MoviePosterModel> getPosterModelsFromResultsJSON(final String jsonStr) {
        final List<MoviePosterModel> moviePosterModels = new LinkedList();
        try {
            final JSONObject json = new JSONObject(jsonStr);
            final JSONArray resultsArray = json.getJSONArray(OWM_RESULTS);

            for (int i = 0; i < resultsArray.length(); i++) {
                final JSONObject movieJson = resultsArray.getJSONObject(i);
                final MoviePosterModel model = new MoviePosterModel(
                        movieJson.getString(OWM_MOVIE_ID), movieJson.getString(OWM_POSTER_PATH));
                moviePosterModels.add(model);
            }
        } catch (final JSONException e) {
            Log.e(LOG_TAG, "Error while parsing json", e);
        }
        Log.v(LOG_TAG, "weijusti moviePosterModels: " + moviePosterModels);
        return moviePosterModels;
    }

    public static MovieDetailsModel getDetailModelsFromMovieJSON(final String jsonStr) {
        MovieDetailsModel movieDetailsModel = null;
        try {
            final JSONObject movieJson = new JSONObject(jsonStr);
            movieDetailsModel = new MovieDetailsModel(movieJson.getString(OWM_ORIGINAL_TITLE),
                    movieJson.getString(OWM_POSTER_PATH), movieJson.getString(OWM_OVERVIEW),
                    movieJson.getString(OWM_VOTE_AVERAGE), movieJson.getString(OWM_RELEASE_DATE));
        } catch (final JSONException e) {
            Log.e(LOG_TAG, "Error while parsing json", e);
        }
        Log.v(LOG_TAG, "weijusti movieDetailsModel: " + movieDetailsModel);
        return movieDetailsModel;
    }
}
