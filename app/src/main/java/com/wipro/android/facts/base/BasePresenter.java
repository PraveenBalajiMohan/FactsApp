package com.wipro.android.facts.base;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * To bind view with presenter. All presenter of this app must extend this.
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public class BasePresenter<V extends BaseView> {

    // DI resolved view for MVP. Appropriate view must be provided from dagger modules.
    @Inject
    protected V view;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    /**
     * proides the view injected by DI
     * @return view
     */
    protected V getView() {
        return view;
    }

    /**
     * Binds producer and consumer with threading rules.
     * @param observable producer component
     * @param observer consumer
     *
     */
    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * Call this to keep tracking the requests.
     * @param disposable {@link Disposable} passed from onSubscribe
     */
    protected void addSubscription(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    /**
     * Clears all observables if activity destroys.
     */
    protected void detach(){
        compositeDisposable.clear();
    }

}
