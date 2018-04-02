package com.app.finxi.githubviewer.api;

import com.app.finxi.githubviewer.model.ItemResponse;
import com.app.finxi.githubviewer.model.PullRequestItemResponse;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface Service {
    //"/search/repositories?q=language:java"
    //"/search/users?q=language:java+location:lagos"
    @GET("/search/repositories?q=language:java")
    Call<ItemResponse> getItems();

    @GET
    Call<PullRequestItemResponse> getPullRequests(@Url String repoUrl);

    @GET
    Call<JSONArray> getPullRequestJSON(@Url String repoUrl);
}
