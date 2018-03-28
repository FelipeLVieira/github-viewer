package com.app.finxi.githubviewer.api;

import com.app.finxi.githubviewer.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Service {
    @GET("/search/users?q=language:java+location:lagos")
    Call<ItemResponse> getItems();
}
