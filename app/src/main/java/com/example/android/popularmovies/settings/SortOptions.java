package com.example.android.popularmovies.settings;

import java.util.HashMap;
import java.util.Map;

/**
 * SortOptions for settings.
 */
public final class SortOptions {
    private static final String POPULAR_OPTION_LABEL = "POPULARITY";
    private static final String RATING_OPTION_LABEL = "RATING";
    private static final String POPULAR_PATH = "popular";
    private static final String RATING_PATH = "top_rated";

    private static Map<String, String> mSortOptionPaths;

    static {
        mSortOptionPaths = new HashMap();
        mSortOptionPaths.put(POPULAR_OPTION_LABEL, POPULAR_PATH);
        mSortOptionPaths.put(RATING_OPTION_LABEL, RATING_PATH);
    }

    public static String getSortOptionPath(final String sortOption) {
        return mSortOptionPaths.get(sortOption);
    }
}
