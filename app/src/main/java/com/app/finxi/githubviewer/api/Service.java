package com.app.finxi.githubviewer.api;

import com.app.finxi.githubviewer.model.ItemResponse;
import com.app.finxi.githubviewer.model.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface Service {
    @GET("/search/repositories?q=language:java")
    Call<ItemResponse> getItems();

    @GET
    Call<List<PullRequest>> getPullRequests(@Url String url);

}
