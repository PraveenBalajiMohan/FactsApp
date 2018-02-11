package com.wipro.android.facts.mvp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Facts list item to fetch from service. Parses automatically using jackson parser
 * @author Praveen Balaji
 * @Date 2/11/2018
 */

public class FactItem {
    @JsonProperty("title")
    public String title;

    @JsonProperty("description")
    public String description;

    @JsonProperty("imageHref")
    public String imageHref;

    @Override
    public String toString() {
        return "Item: --> title: " + title + " description: " + description + " imageHref: " + imageHref;
    }
}
