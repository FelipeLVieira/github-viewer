package com.app.finxi.githubviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PullRequest {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("user")
    @Expose
    private UserAttributes user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public UserAttributes getUser() {
        return user;
    }

    public void setUser(UserAttributes user) {
        this.user = user;
    }
}
