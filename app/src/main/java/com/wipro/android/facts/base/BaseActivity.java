package com.wipro.android.facts.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.wipro.android.facts.config.FactsApplication;
import com.wipro.android.facts.di.components.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Extend this activity class for any activity within this app as a Base activity.
 * All basic configuration are initialised here.
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        // Mandatory initialization goes here
        ButterKnife.bind(this);

        // Call optional initialization.
        onViewReady(savedInstanceState);
    }

    /**
     * Promised to call as part of activity creation. Any initialization part of
     * activity creation can be implemented.
     * @param savedInstanceState
     */
    @CallSuper
    protected void onViewReady(@Nullable Bundle savedInstanceState) {
        injectDependencies();
        // other base level option initialisation can be implemented here
    }

    /**
     * Override this method to initialise Dagger dependencies
     */
    protected void injectDependencies() {
    }

    /**
     * Override this method to associate layout to the activity.
     * @return layout ID
     */
    protected abstract int getContentViewId();

    /**
     * Returns dagger application component to inject views.
     * @return {@link ApplicationComponent}
     */
    public ApplicationComponent getApplicationComponent() {
        return ((FactsApplication) getApplication()).getApplicationComponent();
    }


}
