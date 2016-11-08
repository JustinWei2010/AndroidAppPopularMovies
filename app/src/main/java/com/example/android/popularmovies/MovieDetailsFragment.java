package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.task.RenderMovieDetailsTask;

/**
 * Movie details page fragment class.
 */
public class MovieDetailsFragment extends Fragment {
    private static final String LOG_TAG = MovieDetailsFragment.class.getSimpleName();

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        final Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            final String movieId = intent.getStringExtra(Intent.EXTRA_TEXT);
            new RenderMovieDetailsTask(getContext(), rootView).execute(movieId);
        }
        return rootView;
    }
}
