package com.wba.pojo.createCollections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wba.pojo.createCollections.basePOJO.CollectionBase;
import com.wba.pojo.createCollections.basePOJO.FolderBase;

import java.util.ArrayList;

@JsonIgnoreProperties(value = {"id", "name", "uid"}, allowSetters = true)
public class CollectionRequest extends CollectionBase {
    private ArrayList<FolderRequest> item;

    public CollectionRequest() {

    }
    public CollectionRequest(Info info, ArrayList<FolderRequest> item) {
        super(info);
        this.item = item;
    }

    public ArrayList<FolderRequest> getItem() {
        return item;
    }

    public void setItem(ArrayList<FolderRequest> item) {
        this.item = item;
    }
}
