package com.example.android.popularmovies.model;

/**
 * Model to hold movie details.
 */
public class MovieDetailsModel {
    private String mTitle;
    private String mImagePath;
    private String mOverview;
    private String mUserRating;
    private String mReleaseDate;

    public MovieDetailsModel(final String title, final String imagePath, final String overview,
                             final String userRating, final String releaseDate) {
        mTitle = title;
        mImagePath = imagePath;
        mOverview = overview;
        mUserRating = userRating;
        mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getUserRating() {
        return mUserRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String toString() {
        return String.format("Title: %s\nImagePath: %s\nOverview: %s\nUserRating: %s\n " +
                "ReleaseDate: %s\n", mTitle, mImagePath, mOverview, mUserRating, mReleaseDate);
    }
}
