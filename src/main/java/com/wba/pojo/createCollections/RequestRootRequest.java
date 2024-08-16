package com.wba.pojo.createCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.basePOJO.RequestBase;
import com.wba.pojo.createCollections.basePOJO.RequestRootBase;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRootRequest extends RequestRootBase {
    private RequestRequest request;

    public RequestRootRequest() {
    }

    public RequestRootRequest(String name, RequestRequest request) {
        super(name);
        this.request = request;
    }

    public RequestRequest getRequest() {
        return request;
    }

    public void setRequest(RequestRequest request) {
        this.request = request;
    }
}
