package com.wba.pojo.createCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.basePOJO.RequestBase;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRequest extends RequestBase {
    private String url;

    public RequestRequest() {
    }

    public RequestRequest(String url, String method, ArrayList<Header> header, Body body, String description) {
        super(method, header, body, description);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
