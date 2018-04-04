package com.app.finxi.githubviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PRItemResponse {

    @SerializedName("items")
    @Expose
    private List<PullRequest> items;

    public List<PullRequest> getItems() {
        return items;
    }

    public void setItems(List<PullRequest> items) {
        this.items = items;
    }
}
