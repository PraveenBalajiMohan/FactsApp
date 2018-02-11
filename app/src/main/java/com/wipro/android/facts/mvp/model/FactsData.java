package com.wipro.android.facts.mvp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * FactsData object to fetch from service. Contains title and {@link FactItem} list
 * Parses automatically using jackson parser
 * @author Praveen Balaji
 * @Date 2/11/2018
 */

public class FactsData {

    @JsonProperty("title")
    public String title;

    @JsonProperty("rows")
    public ArrayList<FactItem> factItems;


}
