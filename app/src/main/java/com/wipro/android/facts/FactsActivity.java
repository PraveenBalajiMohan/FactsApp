package com.wipro.android.facts;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.wipro.android.facts.adapters.FactsListViewAdapter;
import com.wipro.android.facts.base.BaseActivity;
import com.wipro.android.facts.di.components.DaggerFactsComponent;
import com.wipro.android.facts.di.modules.FactsModule;
import com.wipro.android.facts.mvp.model.FactItem;
import com.wipro.android.facts.mvp.presenter.FactsPresenter;
import com.wipro.android.facts.mvp.view.FactsView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Android activity to host and display facts data.
 */
public class FactsActivity extends BaseActivity implements FactsView {

    // Binding UI components using Butter knife.
    @BindView(R.id.swipeToRefreshView) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.listView) ListView mListView;
    @BindView(R.id.emptyView) View emptyView;
    @BindView(R.id.btn_retry) Button retry;
    @BindView(R.id.retryPlaceHolder) TextView retryPlaceHolder;

    // Injecting presenter to associate with this activity.
    @Inject protected FactsPresenter presenter;


    // Returning layout id for this activity to display
    @Override
    protected int getContentViewId() {
        return R.layout.activity_facts;
    }

    // Will be called once the base initialisation are completed.
    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);
        initialViewAndListeners();
    }

    // Inject dependencies
    @Override
    protected void injectDependencies() {
        DaggerFactsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .factsModule(new FactsModule(this))
                .build().inject(this);
    }

    // setting initial view properties and listeners
    private void initialViewAndListeners(){
        retry.setEnabled(false);
        retry.setVisibility(View.GONE);
        mListView.setEmptyView(emptyView);

        // setting onRefresh listener for swipe refresh layout
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchFactsData();
            }
        });

        // First time loading the data
        fetchFactsData();
    }

    @Override
    public void showError(int id) {
        mSwipeRefreshLayout.setRefreshing(false);
        retry.setVisibility(View.VISIBLE);
        retry.setEnabled(true);
        if(id != 0){
            retryPlaceHolder.setText(id);
        } else{
            retryPlaceHolder.setText(R.string.error_check_internet_connection);
        }
    }

    @Override
    public void showFactsList(ArrayList<FactItem> factItems) {
        // Assign facts data to the adapter to delegate and display the data.
        FactsListViewAdapter factsListViewAdapter = new FactsListViewAdapter(factItems);
        mListView.setAdapter(factsListViewAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showFactsTitle(String title) {
        // Setting the page title
        if(!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
    }

    @Override
    public void showTechnicalError() {

        // Technical error cannot be resolved so disable refreshing optoin.

        retryPlaceHolder.setText(R.string.error_technical);
        retry.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @OnClick(R.id.btn_retry)
    public void onRetryButtonClick() {
        retry.setEnabled(false);
        retryPlaceHolder.setText(R.string.please_wait);
        fetchFactsData();
    }

    /**
     * Call presenter to fetch facts data
     */
    private void fetchFactsData() {
        mSwipeRefreshLayout.setRefreshing(true);
        presenter.fetchFactsData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // notify presenter about activity destroy.
        presenter.onDetach();
    }
}
