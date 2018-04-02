package com.app.finxi.githubviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;


public class PullRequestItemResponse {

    private List<PullRequest> prList;

    public List<PullRequest> getPullRequests() {
        return prList;
    }

    public void add(PullRequest pullRequest) {
        this.prList = prList;

        if(prList == null){
            prList = new LinkedList<>();
        }
        prList.add(pullRequest);
    }
}
