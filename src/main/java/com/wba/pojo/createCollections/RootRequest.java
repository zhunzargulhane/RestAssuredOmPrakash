package com.wba.pojo.createCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.basePOJO.CollectionBase;
import com.wba.pojo.createCollections.basePOJO.RootBase;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RootRequest extends RootBase {
    private CollectionRequest collection;

    public RootRequest() {
    }

    public RootRequest(CollectionRequest collection) {
        this.collection = collection;
    }

    public CollectionBase getCollection() {
        return collection;
    }

    public void setCollection(CollectionRequest collection) {
        this.collection = collection;
    }
}
