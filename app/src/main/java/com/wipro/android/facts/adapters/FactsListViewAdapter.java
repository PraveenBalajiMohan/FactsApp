package com.wipro.android.facts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wipro.android.facts.R;
import com.wipro.android.facts.mvp.model.FactItem;
import com.wipro.android.facts.utils.ImageUtils;
import com.wipro.android.facts.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter class to display facts data.
 * @author Praveen Balaji
 * @Date 2/11/2018
 */
public class FactsListViewAdapter extends BaseAdapter {

    private ArrayList<FactItem> factItems;

    /**
     * Pass fact items to display in list
     * @param factItems
     */
    public FactsListViewAdapter(ArrayList<FactItem> factItems) {
        this.factItems = factItems;

    }

    @Override
    public int getCount() {
        return factItems.size();
    }

    @Override
    public FactItem getItem(int i) {
        return factItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.facts_list_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        setValues(position, viewHolder);

        return view;
    }

     /*sets data to views.*/
    private void setValues(int position, ViewHolder viewHolder) {
        String title = factItems.get(position).title;
        String description = factItems.get(position).description;
        String imagePath = factItems.get(position).imageHref;
        viewHolder.title.setText(title != null ? title.trim() : "");
        viewHolder.description.setText(description != null ? description.trim() : "");

        if (Utils.isValidUrl(imagePath)) {
            viewHolder.image.setVisibility(View.VISIBLE);
            ImageUtils.loadImage(viewHolder.image.getContext(), imagePath, viewHolder.image);
        } else {
            /*If url is null or not a valid url then hide the image view and display description
            in full width*/
            viewHolder.image.setVisibility(View.GONE);
        }
    }

    /**
     * ViewHolder pattern for the list view to hold the views to recycle.
     * This pattern enables smooth scrolling of list items.
     * Otherwise we can use "RecyclerView" to achieve the same.
     */
    static class ViewHolder {
        @BindView(R.id.title) TextView title;
        @BindView(R.id.description) TextView description;
        @BindView(R.id.image) ImageView image;

        ViewHolder(View view) {
            /*injects butterknife for infalted view*/
            ButterKnife.bind(this, view);
        }
    }

}
