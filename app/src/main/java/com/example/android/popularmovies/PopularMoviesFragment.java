package com.example.android.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.popularmovies.adapter.MoviePosterAdapter;
import com.example.android.popularmovies.settings.SortOptions;
import com.example.android.popularmovies.task.RenderMoviePostersTask;

import java.util.LinkedList;

/**
 * Popular movies page fragment class.
 */
public class PopularMoviesFragment extends Fragment {
    private static final String LOG_TAG = PopularMoviesFragment.class.getSimpleName();

    private MoviePosterAdapter mMoviePosterAdapter;

    public PopularMoviesFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovieImages();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        final GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid_view);
        mMoviePosterAdapter = new MoviePosterAdapter(rootView.getContext(), R.layout.grid_view_item,
                new LinkedList());
        gridView.setAdapter(mMoviePosterAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String movieId = view.getTag().toString();
                final Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, movieId);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void updateMovieImages() {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(
                getContext());
        final String sortOption = prefs.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_popular));
        new RenderMoviePostersTask(getContext(), mMoviePosterAdapter).execute(
                SortOptions.getSortOptionPath(sortOption));
    }
}
