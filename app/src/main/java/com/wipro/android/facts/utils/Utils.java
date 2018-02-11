package com.wipro.android.facts.utils;

import android.util.Patterns;

/**
 * General static utilities
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public class Utils {

    /**
     * Check valid URL format or not
     * @param url endpoint
     * @return true if valid, false  otherwise.
     */
    public static boolean isValidUrl(String url) {
        return url != null && url.length() > 4 && Patterns.WEB_URL.matcher(url).matches();
    }
}
