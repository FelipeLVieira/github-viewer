package com.app.finxi.githubviewer.api;

import com.app.finxi.githubviewer.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Service {
    //"/search/repositories?q=language:java"
    //"/search/users?q=language:java+location:lagos"
    @GET("/search/repositories?q=language:java")
    Call<ItemResponse> getItems();
}
