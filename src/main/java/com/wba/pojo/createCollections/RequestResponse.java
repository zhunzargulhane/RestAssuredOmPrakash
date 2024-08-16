package com.wba.pojo.createCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.basePOJO.RequestBase;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse extends RequestBase {
    private URL url;

    public RequestResponse() {
    }

    public RequestResponse(URL url, String method, ArrayList<Header> header, Body body, String description) {
        super(method, header, body, description);
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
