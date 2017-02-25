package com.android.myjasonpostreqretrofit.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PlacesResponse {

    private List<Place> places = new ArrayList<Place>();

    /**
     * @return The places
     */
    public List<Place> getPlaces() {
        return places;
    }

    /**
     * @param places The places
     */
    public void setPlaces(List<Place> places) {
        this.places = places;
    }

}
