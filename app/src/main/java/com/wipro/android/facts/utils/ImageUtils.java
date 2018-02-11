package com.wipro.android.facts.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wipro.android.facts.R;

/**
 * Image utilities are available here
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public class ImageUtils {
    /**
     * Call this method to load images in to the view using glide.
     * @param ctx activity context
     * @param source image path
     * @param imageView Image view to be loaded
     */
    public static void loadImage(Context ctx, String source, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.error).fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(false);

        Glide.with(ctx)
                .load(source)
                .apply(requestOptions)
                .into(imageView);
    }
}
