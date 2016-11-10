package com.example.android.popularmovies.task;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
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
    private static final double GOOD_RATING_THRESHOLD = 7.0;
    private static final double BAD_RATING_THRESHOLD = 5.0;
    private static final String RATING_DELIMITER = "/";
    private static final int MAX_RATING = 10;

    private Context mContext;
    private View mView;

    public RenderMovieDetailsTask(final Context context, final View view) {
        mContext = context;
        mView = view;
    }

    @Override
    protected MovieDetailsModel doInBackground(final String... params) {
        final String movieId = params[0];

        if (URLConnectionHelper.isDeviceOnline(mContext)) {
            final String json = URLConnectionHelper.getJsonFromURL(
                    URLBuilder.getMovieApiURL(movieId));
            return MovieJSONParser.getDetailModelsFromMovieJSON(json);
        } else {
            Log.e(LOG_TAG, "Device is not currently connected to internet.");
            return null;
        }
    }

    @Override
    protected void onPostExecute(final MovieDetailsModel model) {
        if (model != null) {
            ((TextView)mView.findViewById(R.id.movie_title)).setText(model.getTitle());
            ((TextView)mView.findViewById(R.id.movie_release_date)).setText(
                    getFormattedReleaseDate(model.getReleaseDate()));
            ((TextView)mView.findViewById(R.id.movie_overview)).setText(
                    model.getOverview());

            //Set user ratings
            final TextView userRatingView = ((TextView)mView.findViewById(R.id.movie_user_rating));
            final String userRating = model.getUserRating();
            userRatingView.setText(userRating);
            setColorForUserRatings(userRatingView);
            userRatingView.setText(getFormattedUserRatings(userRating));

            //Set thumbnail picture
            final String posterURL = URLBuilder.getMoviePosterURL(model.getImagePath());
            final ImageView imageView = (ImageView) mView.findViewById(R.id.movie_poster_thumbnail);
            Picasso.with(mContext).load(posterURL).into(imageView);
        } else {
            Log.e(LOG_TAG, "No results returned from call to movie api.");
        }
    }

    private String getFormattedReleaseDate(final String releaseDate) {
        return releaseDate.replaceAll("-", ".");
    }

    private String getFormattedUserRatings(final String userRatings) {
        return userRatings + RATING_DELIMITER + MAX_RATING;
    }

    private void setColorForUserRatings(final TextView textView) {
        final double ratings = Double.parseDouble(textView.getText().toString());
        final GradientDrawable bgShape = (GradientDrawable)textView.getBackground();
        if (ratings <= GOOD_RATING_THRESHOLD && ratings > BAD_RATING_THRESHOLD) {
            bgShape.setColor(mContext.getResources().getColor(R.color.colorDecentRating));
        } else if (ratings <= BAD_RATING_THRESHOLD) {
            bgShape.setColor(mContext.getResources().getColor(R.color.colorBadRating));
        } else {
            bgShape.setColor(mContext.getResources().getColor(R.color.colorGoodRating));
        }
    }
}
