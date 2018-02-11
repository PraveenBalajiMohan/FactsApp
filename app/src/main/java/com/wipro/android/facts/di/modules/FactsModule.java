package com.wipro.android.facts.di.modules;

import com.wipro.android.facts.api.FactsApiService;
import com.wipro.android.facts.di.scope.PerActivity;
import com.wipro.android.facts.mvp.view.FactsView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Activity module provider scoped to activity life.
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
@Module
public class FactsModule {
    private FactsView factsView;

    /**
     * pass Activity's view object for dagger to inject in {@link com.wipro.android.facts.base.BasePresenter}
     * @param factsView
     */
    public FactsModule(FactsView factsView) {
        this.factsView = factsView;
    }

    @Provides
    @PerActivity
    FactsView providesFactsView() {
        return factsView;
    }

    @Provides
    FactsApiService providesFactsApiService(Retrofit retrofit) {
        // Returns retrofit service object
        return retrofit.create(FactsApiService.class);
    }


}
