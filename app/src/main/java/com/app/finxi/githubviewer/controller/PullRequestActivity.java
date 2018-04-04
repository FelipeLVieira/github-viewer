package com.app.finxi.githubviewer.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.finxi.githubviewer.R;
import com.app.finxi.githubviewer.adapter.PRAdapter;
import com.app.finxi.githubviewer.api.Client;
import com.app.finxi.githubviewer.api.Service;
import com.app.finxi.githubviewer.model.Item;
import com.app.finxi.githubviewer.model.PRItemResponse;
import com.app.finxi.githubviewer.model.PullRequest;
import com.app.finxi.githubviewer.util.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
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
    private LinearLayoutManager linearLayoutManager;
    private String repoUrl;
    private int pageCount = 1;
    private ArrayList<PullRequest> items = new ArrayList<>();
    private ArrayList<PullRequest> newItems = new ArrayList<>();
    private ArrayAdapter<PullRequest> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        repoUrl = getIntent().getExtras().getString("repo_url");

        swipeContainer = findViewById(R.id.swipeContainerRep);

        pd = new ProgressDialog(this);
        pd.setMessage("Carregando pull requests do Github...");
        pd.setCancelable(false);
        pd.show();

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.smoothScrollToPosition(items.size() - newItems.size());

        recyclerView.setOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                pd.show();
                pageCount += 1;

                loadPrJSON(repoUrl + pageCount);
            }
        });

        loadPrJSON(repoUrl + pageCount);
    }

    private void loadPrJSON(String apiUrl) {
        Disconnected = findViewById(R.id.disconnectedRep);
        try {

            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<PRItemResponse> call = apiService.getPullRequests(apiUrl);
            call.enqueue(new Callback<PRItemResponse>() {
                @Override
                public void onResponse(Call<PRItemResponse> call, Response<PRItemResponse> response) {
                    newItems = new ArrayList<>(response.body().getItems());
                    items.addAll(newItems);
                    recyclerView.setAdapter(new PRAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(items.size() - newItems.size());
                    pd.hide();

                }

                @Override
                public void onFailure(Call<PRItemResponse> call, Throwable t) {
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

    protected void onPause() {
        super.onPause();
        pd.dismiss();
    }
}
