package com.app.finxi.githubviewer.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.finxi.githubviewer.R;
import com.app.finxi.githubviewer.adapter.PRAdapter;
import com.app.finxi.githubviewer.api.Client;
import com.app.finxi.githubviewer.api.Service;
import com.app.finxi.githubviewer.model.Item;
import com.app.finxi.githubviewer.model.PullRequest;
import com.app.finxi.githubviewer.model.PullRequestItemResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestActivity extends AppCompatActivity {

    TextView Disconnected;
    ProgressDialog pd;
    private RecyclerView recyclerView;
    private Item item;
    private SwipeRefreshLayout swipeContainer;
    private String repoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        initViews();

        swipeContainer = findViewById(R.id.swipeContainerRep);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPrJSON();
                Toast.makeText(PullRequestActivity.this, "Pull requests do Github recarregados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        pd = new ProgressDialog(this);
        pd.setMessage("Carregando pull requests do Github...");
        pd.setCancelable(false);
        pd.show();
        recyclerView = findViewById(R.id.recyclerViewRep);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadPrJSON();
    }

    private void loadPrJSON() {
        Disconnected = findViewById(R.id.disconnectedRep);
        try {

            repoUrl = Objects.requireNonNull(getIntent().getExtras()).getString("repo_url");

            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<PullRequestItemResponse> call = apiService.getPullRequests(repoUrl);
            call.enqueue(new Callback<PullRequestItemResponse>() {
                @Override
                public void onResponse(Call<PullRequestItemResponse> call, Response<PullRequestItemResponse> response) {

                    /*JSONArray json = null;
                    HashMap<String, String> map = null;
                    JSONObject jsonObject = new JSONObject();
                    JSONObject innerJSONobj = new JSONObject();
                    List<HashMap<String, String>> repoItems = null;

                    try {

                        //prList = response.body().getPullRequests();

                        json = new JSONArray(response.body());

                        for (int i = 0; i < json.length(); i++) {
                            map = new HashMap<>();
                            jsonObject = json.getJSONObject(i);
                            innerJSONobj = jsonObject.getJSONObject("user");

                            map.put("id", jsonObject.getString("id"));
                            map.put("number", jsonObject.getString("number"));
                            map.put("title", jsonObject.getString("title"));
                            map.put("body", jsonObject.getString("body"));
                            map.put("created_at", jsonObject.getString("created_at"));
                            map.put("login", innerJSONobj.getString("login"));
                            map.put("avatar_url", innerJSONobj.getString("avatar_url"));

                            repoItems.add(map);
                        }

                    } catch (Exception ex) {
                        Log.d("Error", ex.getMessage());
                    }*/

                    List<PullRequest> prList = response.body().getPullRequests();
                    recyclerView.setAdapter(new PRAdapter(getApplicationContext(), prList));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<PullRequestItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(PullRequestActivity.this, "Erro ao carregar os dados!", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();

                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
