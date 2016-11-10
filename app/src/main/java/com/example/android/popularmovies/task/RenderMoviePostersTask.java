package com.example.android.popularmovies.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

    private Context mContext;
    private MoviePosterAdapter mAdapter;

    public RenderMoviePostersTask(final Context context, final MoviePosterAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
    }

    @Override
    protected List<MoviePosterModel> doInBackground(final String... params) {
        final String path = params[0];

        if (URLConnectionHelper.isDeviceOnline(mContext)) {
            final String json = URLConnectionHelper.getJsonFromURL(
                    URLBuilder.getMovieApiURL(path));
            return MovieJSONParser.getPosterModelsFromResultsJSON(json);
        } else {
            Log.e(LOG_TAG, "Device is not currently connected to internet.");
            return null;
        }
    }

    @Override
    protected void onPostExecute(final List<MoviePosterModel> models) {
        if (models != null) {
            mAdapter.clear();
            for (final MoviePosterModel model : models) {
                mAdapter.add(model);
            }
        } else {
            Log.e(LOG_TAG, "No results returned from call to movie api.");
        }
    }
}
