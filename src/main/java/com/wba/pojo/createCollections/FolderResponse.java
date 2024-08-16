package com.wba.pojo.createCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.basePOJO.FolderBase;
import com.wba.pojo.createCollections.basePOJO.RequestRootBase;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderResponse extends FolderBase {

    private ArrayList<RequestRootResponse> item;

    public FolderResponse() {
    }

    public FolderResponse(String name, ArrayList<RequestRootResponse> item) {
        super(name);
        this.item = item;
    }

    public ArrayList<RequestRootResponse> getItem() {
        return item;
    }

    public void setItem(ArrayList<RequestRootResponse> item) {
        this.item = item;
    }
}
