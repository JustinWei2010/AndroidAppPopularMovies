package com.example.android.popularmovies.task;

import android.os.AsyncTask;

import com.example.android.popularmovies.adapter.MoviePosterAdapter;
import com.example.android.popularmovies.json.MovieJSONParser;
import com.example.android.popularmovies.model.MoviePosterModel;
import com.example.android.popularmovies.url.URLBuilder;
import com.example.android.popularmovies.url.URLConnectionHelper;

import java.util.List;

/**
 * Task to fetch movie posters shown on home page.
 */
public class RenderMoviePostersTask extends AsyncTask<String, Void, List<MoviePosterModel>> {
    private static final String LOG_TAG = RenderMoviePostersTask.class.getSimpleName();

    private MoviePosterAdapter mAdapter;

    public RenderMoviePostersTask(final MoviePosterAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    protected List<MoviePosterModel> doInBackground(final String... params) {
        final String path = params[0];
        final String json = URLConnectionHelper.getJsonFromURL(
                URLBuilder.getMovieApiURL(path));
        return MovieJSONParser.getPosterModelsFromResultsJSON(json);
    }

    @Override
    protected void onPostExecute(final List<MoviePosterModel> models) {
        if (models != null) {
            mAdapter.clear();
            for (final MoviePosterModel model : models) {
                mAdapter.add(model);
            }
        }
    }
}
