package com.example.android.popularmovies.json;

/**
 * Model for storing tag and image path for movie poster.
 */
public class MoviePosterModel {
    private String mMovieId;
    private String mImagePath;

    public MoviePosterModel(final String movieId, final String imagePath) {
        mMovieId = movieId;
        mImagePath = imagePath;
    }

    public String getMovieId() {
        return mMovieId;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public String toString() {
        return String.format("Tag: %s\n ImagePath: %s\n", mMovieId, mImagePath);
    }
}
