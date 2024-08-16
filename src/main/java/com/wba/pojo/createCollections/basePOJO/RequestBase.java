package com.wba.pojo.createCollections.basePOJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.Body;
import com.wba.pojo.createCollections.Header;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class RequestBase {
    private String method;
    private ArrayList<Header> header;
    private Body body;
    private String description;

    public RequestBase() {
    }

    public RequestBase(String method, ArrayList<Header> header, Body body, String description) {
        this.method = method;
        this.header = header;
        this.body = body;
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ArrayList<Header> getHeader() {
        return header;
    }

    public void setHeader(ArrayList<Header> header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
