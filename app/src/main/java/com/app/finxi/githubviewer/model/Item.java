package com.app.finxi.githubviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("name")
    @Expose
    private String repoName;

    @SerializedName("login")
    @Expose
    private String login;

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
    private Owner owner = new Owner();

    public Item(String repoName, String login, String description, String forks, String stars) {
        this.repoName = repoName;
        this.login = login;
        this.description = description;
        this.forks = forks;
        this.stars = stars;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
