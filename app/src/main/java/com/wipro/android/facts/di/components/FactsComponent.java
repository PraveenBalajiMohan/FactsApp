package com.wipro.android.facts.di.components;

import com.wipro.android.facts.FactsActivity;
import com.wipro.android.facts.di.modules.FactsModule;
import com.wipro.android.facts.di.scope.PerActivity;

import dagger.Component;

/**
 * Dagger component for facts module
 * @author Praveen Balaji
 * @Date 2/11/2018
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = FactsModule.class)
public interface FactsComponent {
    void inject(FactsActivity factsActivity);

}
