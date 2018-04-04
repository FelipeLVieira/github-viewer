package com.app.finxi.githubviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Item {

    @SerializedName("name")
    @Expose
    private String repoName;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("forks")
    @Expose
    private String forks;

    @SerializedName("stargazers_count")
    @Expose
    private String stars;

    @SerializedName("owner")
    @Expose
    private ItemOwner itemOwner = new ItemOwner();

    @SerializedName("id")
    private String repoId;

    @SerializedName("has_issues")
    private boolean hasIssues;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public ItemOwner getItemOwner() {
        return itemOwner;
    }

    public void setItemOwner(ItemOwner itemOwner) {
        this.itemOwner = itemOwner;
    }

    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public boolean isHasIssues() {
        return hasIssues;
    }

    public void setHasIssues(boolean hasIssues) {
        this.hasIssues = hasIssues;
    }


    public static List<Item> createItems(int itemCount) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Item item = new Item();
            items.add(item);
        }
        return items;
    }
}
