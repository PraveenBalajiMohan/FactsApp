package com.wipro.android.facts.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 *  Custom activity scope for dagger
 * @author Praveen Balaji
 * @Date 2/11/2018
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
