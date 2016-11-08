package com.example.android.popularmovies.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.popularmovies.json.MoviePosterModel;
import com.example.android.popularmovies.url.URLBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Image adapter for movie posters.
 */
public class MoviePosterAdapter extends ArrayAdapter {
    private static final String LOG_TAG = MoviePosterAdapter.class.getSimpleName();

    private Context mContext;
    private int mLayoutResourceId;
    private List<MoviePosterModel> mData;

    public MoviePosterAdapter(final Context context, final int layoutResourceId,
                              final List<MoviePosterModel> data) {
        super(context, layoutResourceId, data);
        mContext = context;
        mLayoutResourceId = layoutResourceId;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            final LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            imageView = (ImageView) inflater.inflate(mLayoutResourceId, parent, false);
        } else {
            imageView = (ImageView) convertView;
        }
        final MoviePosterModel model = mData.get(position);
        imageView.setTag(model.getMovieId());
        final String posterURL = URLBuilder.getFullPosterURL(model.getImagePath());
        Log.v(LOG_TAG, "weijusti posterURL: " + posterURL);
        Picasso.with(mContext).load(posterURL).into(imageView);
        return imageView;
    }
}
