package com.wba.pojo.createCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.basePOJO.FolderBase;
import com.wba.pojo.createCollections.basePOJO.RequestRootBase;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderRequest extends FolderBase {
    private ArrayList<RequestRootRequest> item;

    public FolderRequest() {
    }

    public FolderRequest(String name, ArrayList<RequestRootRequest> item) {
        super(name);
        this.item = item;
    }

    public ArrayList<RequestRootRequest> getItem() {
        return item;
    }

    public void setItem(ArrayList<RequestRootRequest> item) {
        this.item = item;
    }
}
