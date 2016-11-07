package com.example.android.popularmovies.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Image adapter for movie posters.
 */
public class MovieImageAdapter extends ArrayAdapter {
    private static final String LOG_TAG = MovieImageAdapter.class.getSimpleName();

    private Context mContext;
    private int mLayoutResourceId;
    private List<String> mData;

    public MovieImageAdapter(final Context context, final int layoutResourceId,
                             final List<String> data) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            final LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);
        }
        imageView = (ImageView) convertView;
        Picasso.with(mContext).load(mData.get(position)).into(imageView);
        return imageView;
    }
}
