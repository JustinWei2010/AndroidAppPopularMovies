package com.example.android.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Justin Wei on 11/6/2016.
 */

public class MovieImageAdapter extends ArrayAdapter {
    private Context mContext;
    private int mLayoutResourceId;
    private List<Integer> mData;

    public MovieImageAdapter(Context context, int layoutResourceId, List data) {
        super(context, layoutResourceId, data);
        mContext = context;
        mLayoutResourceId = layoutResourceId;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            final LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);
        }
        imageView = (ImageView) convertView;
        imageView.setImageResource(mData.get(position));
        return imageView;
    }
}
