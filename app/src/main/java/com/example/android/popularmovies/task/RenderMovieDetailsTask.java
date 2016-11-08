package com.example.android.popularmovies.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.json.MovieJSONParser;
import com.example.android.popularmovies.model.MovieDetailsModel;
import com.example.android.popularmovies.url.URLBuilder;
import com.example.android.popularmovies.url.URLConnectionHelper;
import com.squareup.picasso.Picasso;

/**
 * Task to fetch movie details.
 */
public class RenderMovieDetailsTask extends AsyncTask<String, Void, MovieDetailsModel> {
    private static final String LOG_TAG = RenderMovieDetailsTask.class.getSimpleName();

    private Context mContext;
    private View mView;

    public RenderMovieDetailsTask(final Context context, final View view) {
        mContext = context;
        mView = view;
    }

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
            ((TextView)mView.findViewById(R.id.movie_title)).setText(model.getTitle());
            ((TextView)mView.findViewById(R.id.movie_user_rating)).setText(
                    model.getUserRating());
            ((TextView)mView.findViewById(R.id.movie_release_date)).setText(
                    model.getReleaseDate());
            ((TextView)mView.findViewById(R.id.movie_overview)).setText(
                    model.getOverview());

            final String posterURL = URLBuilder.getMoviePosterURL(model.getImagePath());
            Log.v(LOG_TAG, "weijusti posterURL: " + posterURL);
            final ImageView imageView = (ImageView) mView.findViewById(
                    R.id.movie_poster_thumbnail);
            Picasso.with(mContext).load(posterURL).into(imageView);
        }
    }
}
