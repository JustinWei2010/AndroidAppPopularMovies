package com.example.android.popularmovies.url;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Helper class to send requests to a specified url.
 */
public class URLConnectionHelper {
    private static final String LOG_TAG = URLConnectionHelper.class.getSimpleName();

    public static String getJsonFromURL(final String urlName) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String json = "";

        try {
            final URL url = new URL(urlName);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // For debugging readability
                buffer.append(line + "\n");
            }
            json = buffer.toString();
        } catch (final IOException e) {
            Log.e(LOG_TAG, "Error while sending request to url", e);
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return json;
    }

    public static boolean isDeviceOnline(final Context activityContext) {
        final ConnectivityManager cm = (ConnectivityManager) activityContext.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
