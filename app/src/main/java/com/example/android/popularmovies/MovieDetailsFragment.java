package com.example.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.adapter.MoviePosterAdapter;
import com.example.android.popularmovies.json.MovieJSONParser;
import com.example.android.popularmovies.model.MovieDetailsModel;
import com.example.android.popularmovies.url.URLBuilder;
import com.example.android.popularmovies.url.URLConnectionHelper;
import com.squareup.picasso.Picasso;

/**
 * Movie details page fragment class.
 */
public class MovieDetailsFragment extends Fragment {
    private static final String LOG_TAG = MovieDetailsFragment.class.getSimpleName();

    private MoviePosterAdapter mMoviePosterAdapter;

    public MovieDetailsFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        final Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            final String movieId = intent.getStringExtra(Intent.EXTRA_TEXT);
            new FetchMovieInfoTask().execute(movieId);
        }
        return rootView;
    }

    private class FetchMovieInfoTask extends AsyncTask<String, Void, MovieDetailsModel> {
        @Override
        protected MovieDetailsModel doInBackground(final String... params) {
            final String movieId = params[0];
            final String json = URLConnectionHelper.getJsonFromURL(
                    URLBuilder.getMovieApiURL(movieId));
            return MovieJSONParser.getDetailModelsFromMovieJSON(json);
        }

        @Override
        protected void onPostExecute(final MovieDetailsModel model) {
            if (model != null) {
                ((TextView)getView().findViewById(R.id.movie_title)).setText(model.getTitle());
                ((TextView)getView().findViewById(R.id.movie_user_rating)).setText(
                        model.getUserRating());
                ((TextView)getView().findViewById(R.id.movie_release_date)).setText(
                        model.getReleaseDate());
                ((TextView)getView().findViewById(R.id.movie_overview)).setText(
                        model.getOverview());

                final String posterURL = URLBuilder.getMoviePosterURL(model.getImagePath());
                Log.v(LOG_TAG, "weijusti posterURL: " + posterURL);
                final ImageView imageView = (ImageView) getView().findViewById(
                        R.id.movie_poster_thumbnail);
                Picasso.with(getContext()).load(posterURL).into(imageView);
            }
        }
    }
}
