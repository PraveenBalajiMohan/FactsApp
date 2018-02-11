package com.wipro.android.facts.di.modules;

import android.content.Context;

import com.wipro.android.facts.BuildConfig;
import com.wipro.android.facts.mvp.model.FactsStorage;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Praveen Balaji
 * @Date 2/11/2018
 */

@Module
public class ApplicationModule {
    private Context context;

    /**
     * Pass application context for dagger to provide app context.
     * @param context
     */
    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        // returns appllication context
        return context;
    }

    @Named("baseUrl")
    @Provides
    @Singleton
    String providesBaseUrl() {
        // Endpoint retrieved based on build variant.
        return BuildConfig.HOST;
    }

    @Provides
    Retrofit providesRetrofit(@Named("baseUrl") String hostUrl) {
        // provides retrofit object for the service.
        return new Retrofit.Builder()
                .baseUrl(hostUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    FactsStorage providesFactsStorage(){
        // provides singleton data storage object to persist in memory.
        return new FactsStorage();
    }




}
