package com.wipro.android.facts.mvp.model;

import javax.inject.Singleton;

/**
 * Facts storage pojo to persist data offline and to withstand orientation change.
 * @author Praveen Balaji
 * @Date 2/12/2018
 */
@Singleton
public class FactsStorage {
    private FactsData factsData;

    public FactsData getFactsData() {
        return factsData;
    }

    public void setFactsData(FactsData factsData) {
        this.factsData = factsData;
    }
}
