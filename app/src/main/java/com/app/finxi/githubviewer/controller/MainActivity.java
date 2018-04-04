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
import com.app.finxi.githubviewer.adapter.ItemAdapter;
import com.app.finxi.githubviewer.api.Client;
import com.app.finxi.githubviewer.api.Service;
import com.app.finxi.githubviewer.model.Item;
import com.app.finxi.githubviewer.model.ItemResponse;
import com.app.finxi.githubviewer.util.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView Disconnected;
    ProgressDialog pd;
    private RecyclerView recyclerView;
    private Item item;
    private SwipeRefreshLayout swipeContainer;
    private LinearLayoutManager linearLayoutManager;
    private int pageCount = 1;
    private String repoUrl = "https://api.github.com/search/repositories?q=language:java&page=";
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> newItems = new ArrayList<>();
    private ArrayAdapter<Item> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {

        swipeContainer = findViewById(R.id.swipeContainerRep);

        pd = new ProgressDialog(this);
        pd.setMessage("Carregando repositórios do Github...");
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

                loadJSON(repoUrl + pageCount);
            }
        });

        loadJSON(repoUrl + pageCount);
    }


    private void loadJSON(String apiUrl) {
        Disconnected = findViewById(R.id.disconnected);
        try {
            Client client = new Client();
            Service apiService =
                    client.getClient().create(Service.class);
            Call<ItemResponse> call = apiService.getItems(apiUrl);
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    newItems = new ArrayList<>(response.body().getItems());
                    items.addAll(newItems);
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(items.size() - newItems.size());
                    pd.hide();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Erro ao carregar os dados!", Toast.LENGTH_SHORT).show();
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
