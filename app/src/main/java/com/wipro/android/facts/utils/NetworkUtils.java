package com.wipro.android.facts.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Common network utilities
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public class NetworkUtils {

    /**
     * Checks the internet connectivity.
     * @param context application context
     * @return true if available, otherwise false
     */
    public static boolean isNetAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
