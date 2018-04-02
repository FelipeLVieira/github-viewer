package com.app.finxi.githubviewer.model;

import com.google.gson.annotations.SerializedName;

public class UserAttributes {

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatar_url;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
