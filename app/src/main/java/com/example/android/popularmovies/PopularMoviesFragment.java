package com.example.android.popularmovies;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.popularmovies.adapter.MovieImageAdapter;
import com.example.android.popularmovies.settings.SortOptions;
import com.example.android.popularmovies.json.MovieJSONParser;
import com.example.android.popularmovies.url.URLBuilder;
import com.example.android.popularmovies.url.URLConnectionHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PopularMoviesFragment extends Fragment {
    private static final String LOG_TAG = PopularMoviesFragment.class.getSimpleName();

    private MovieImageAdapter mMovieImageAdapter;

    public PopularMoviesFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovieImages();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        final GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid_view);
        mMovieImageAdapter = new MovieImageAdapter(rootView.getContext(), R.layout.grid_view_item,
                new LinkedList());
        gridView.setAdapter(mMovieImageAdapter);
        return rootView;
    }

    private void updateMovieImages() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(
                getContext());
        final String sortOption = prefs.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_popular));
        new FetchMoviesTask().execute(sortOption);
    }

    private class FetchMoviesTask extends AsyncTask<String, Void, List<String>> {
        @Override
        protected List<String> doInBackground(final String... params) {
            final String sortOption = params[0];
            final String json = URLConnectionHelper.getJsonFromURL(URLBuilder.getMovieListURLName(
                    SortOptions.getSortOptionPath(sortOption)));
            return MovieJSONParser.getPosterPathsFromJson(json);
        }

        @Override
        protected void onPostExecute(final List<String> result) {
            if (result != null) {
                mMovieImageAdapter.clear();
                for (final String backdropPath : result) {
                    final String posterURL = URLBuilder.getFullPosterURL(backdropPath);
                    Log.v(LOG_TAG, "weijusti posterURL: " + posterURL);
                    mMovieImageAdapter.add(posterURL);
                }
            }
        }
    }
}
