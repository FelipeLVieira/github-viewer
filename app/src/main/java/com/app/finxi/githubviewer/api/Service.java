package com.app.finxi.githubviewer.api;

import com.app.finxi.githubviewer.model.ItemResponse;
import com.app.finxi.githubviewer.model.PRItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface Service {
    //"https://api.github.com/search/repositories?q=language:java&page="
    @GET
    Call<ItemResponse> getItems(@Url String url);

    @GET
    Call<PRItemResponse> getPullRequests(@Url String url);

}
