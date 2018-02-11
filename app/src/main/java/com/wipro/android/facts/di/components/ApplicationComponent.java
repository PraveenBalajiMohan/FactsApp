package com.wipro.android.facts.di.components;

import android.content.Context;

import com.wipro.android.facts.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Application level singleton objects for dagger graph.
 * @author Praveen Balaji
 * @Date 2/11/2018
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context exposeContext();

    Retrofit exposeRetrofit();

}
