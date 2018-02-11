package com.wipro.android.facts.api;

import com.wipro.android.facts.mvp.model.FactsData;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Retrofit service api to make facts requests.
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public interface FactsApiService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Observable<Response<FactsData>> getFacts();
}
