package com.wipro.android.facts.mvp.view;

import com.wipro.android.facts.base.BaseView;
import com.wipro.android.facts.mvp.model.FactItem;

import java.util.ArrayList;

/**
 * Interface to implement by the {@link com.wipro.android.facts.FactsActivity}
 * All presenter interactions with view mush present here
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public interface FactsView extends BaseView {
    void showError(int id);

    void showFactsList(ArrayList<FactItem> factItems);

    void showFactsTitle(String title);

    void showTechnicalError();
}
