package com.wba.pojo.createCollections;

import com.wba.pojo.createCollections.basePOJO.CollectionBase;
import java.util.ArrayList;

public class CollectionResponse extends CollectionBase {
    private ArrayList<FolderResponse> item;

    public CollectionResponse() {

    }

    public CollectionResponse(Info info, ArrayList<FolderResponse> item) {
        super(info);
        this.item = item;
    }

    public ArrayList<FolderResponse> getItem() {
        return item;
    }

    public void setItem(ArrayList<FolderResponse> item) {
        this.item = item;
    }
}
