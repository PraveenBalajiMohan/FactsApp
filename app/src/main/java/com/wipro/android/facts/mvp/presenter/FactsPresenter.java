package com.wipro.android.facts.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.wipro.android.facts.R;
import com.wipro.android.facts.api.FactsApiService;
import com.wipro.android.facts.base.BasePresenter;
import com.wipro.android.facts.mvp.model.FactItem;
import com.wipro.android.facts.mvp.model.FactsData;
import com.wipro.android.facts.mvp.model.FactsStorage;
import com.wipro.android.facts.mvp.view.FactsView;
import com.wipro.android.facts.utils.NetworkUtils;

import java.util.Iterator;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Presenter associated with {@link FactsView} it has 1 to t relationship
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public class FactsPresenter extends BasePresenter<FactsView>  implements Observer<Response<FactsData>> {

    private static final String TAG = "FactsPresenter";
    private static final int CODE_SUCCESS = 200;

    // Dagger supplied dependencies
    @Inject protected FactsApiService factsApiService;
    @Inject Context context;
    @Inject FactsStorage factsStorage;

    // Constructor should be marked Injected if we are not using providers
    @Inject
    public FactsPresenter(){}


    /**
     * Call this from associated view to fetch facts data
     * @param doRefresh pass true to reload latest data
     */
    public void fetchFactsData(boolean doRefresh){
        if(factsStorage.getFactsData() != null && !doRefresh){
            // if data available in storage object and not a refresh call then display cached data.
            Log.i(TAG,"Retrieving data from storage.");
            filterAndDelegateResults(factsStorage.getFactsData());
            return;
        }

        Log.i(TAG,"Retrieving data from source.");

        // either new call to service or refresh call
        if(NetworkUtils.isNetAvailable(context)) {
            // if network available make a service call to retrieve facts data using retrofit.
            Observable<Response<FactsData>> observable = factsApiService.getFacts();

            // Binds observable with observer
            subscribe(observable, this);
        } else {
            Log.w(TAG,"Network not available.");
            // Network not available; Display error message.
            getView().showError(R.string.error_check_internet_connection);
        }
    }

    /**
     * Filters null values returned by the service. And calls view to display result.
     * @param factsData {@link FactsData}
     */
    private void filterAndDelegateResults(FactsData factsData) {
        for (Iterator<FactItem> iterator = factsData.factItems.iterator(); iterator.hasNext(); ) {
            FactItem factItem = iterator.next();

            /*Filters null description and title AND null description. If no description filter it.*/
            if ((TextUtils.isEmpty(factItem.title) && TextUtils.isEmpty(factItem.description)) ||
                    TextUtils.isEmpty(factItem.description)) {
                iterator.remove();
            }
        }

        // delegates facts data to display
        Log.i(TAG,"Show title and items");
        getView().showFactsList(factsData.factItems);
        getView().showFactsTitle(factsData.title);
    }

    /**
     * Notifies base presenter to dispose service processing if any when view's destroy event.
     */
    public void onDetach(){
        super.detach();
        Log.i(TAG,"Detaching the presenter to clear disposables.");
    }


    @Override
    public void onSubscribe(Disposable d) {
        Log.i(TAG,"Observer: onSubscribe.");
        // add subscription to base presenter to manage effectively with lifecycle events.
        addSubscription(d);
    }

    @Override
    public void onNext(Response<FactsData> factsDataResponse) {
        Log.i(TAG,"Observer: onNext");
        if (factsDataResponse.code() == CODE_SUCCESS) {
            Log.i(TAG,"Observer: onNext: "+CODE_SUCCESS);
            if (factsDataResponse.body() != null) {
                Log.i(TAG,"Observer: onNext: response received.");
                factsStorage.setFactsData(factsDataResponse.body());
                //  Result success, process the response.
                filterAndDelegateResults(factsDataResponse.body());
            } else {
                Log.w(TAG,"Observer: onNext: null response received.");
                // Null data received; so allow refresh.
                getView().showError(R.string.error_no_data);
            }
        } else {
            Log.w(TAG,"Observer: onNext: non success status code: "+factsDataResponse.code());
            // Non success status code received from server; hence technical error.
            getView().showTechnicalError();

        }
    }

    @Override
    public void onError(Throwable e) {
        // Most probable host not reachable or similar kind; Display generic try again message
        getView().showError(R.string.error_not_reachable);
        Log.e(TAG,e.getMessage(),e);
    }

    @Override
    public void onComplete() {
        Log.i(TAG,"Observer: onComplete");
    }
}
