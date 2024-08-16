package com.wba.pojo.createCollections.basePOJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.Info;

//@JsonIgnoreProperties(value = {"id", "name", "uid"}, allowSetters = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CollectionBase {
    private Info info;
    public CollectionBase() {

    }
    public CollectionBase(Info info) {
        this.info = info;

    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }


}
