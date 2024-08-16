package com.wba.pojo.createCollections.basePOJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class RootBase {
    public RootBase() {
    }

}
