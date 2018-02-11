package com.wipro.android.facts.config;

import android.app.Application;

import com.wipro.android.facts.di.components.ApplicationComponent;
import com.wipro.android.facts.di.components.DaggerApplicationComponent;
import com.wipro.android.facts.di.modules.ApplicationModule;

/**
 * Application class to initialize DI and other configurations.
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public class FactsApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    /**
     *  To get Application level dagger component for injection.
     * @return {@link ApplicationComponent}
     */
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
