package com.wba.pojo.createCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.basePOJO.RequestBase;
import com.wba.pojo.createCollections.basePOJO.RequestRootBase;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRootResponse extends RequestRootBase {
    private RequestResponse request;

    public RequestRootResponse() {
    }

    public RequestRootResponse(String name, RequestResponse request) {
        super(name);
        this.request = request;
    }

    public RequestResponse getRequest() {
        return request;
    }

    public void setRequest(RequestResponse request) {
        this.request = request;
    }
}
