package com.wba.pojo.createCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.basePOJO.CollectionBase;
import com.wba.pojo.createCollections.basePOJO.RootBase;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootResponse extends RootBase {
    private CollectionResponse collection;

    public RootResponse() {
    }

    public RootResponse(CollectionResponse collection) {
        this.collection = collection;
    }

    public CollectionResponse getCollection() {
        return collection;
    }

    public void setCollection(CollectionResponse collection) {
        this.collection = collection;
    }
}
