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

import com.app.finxi.githubviewer.adapter.PRAdapter;
import com.app.finxi.githubviewer.R;
import com.app.finxi.githubviewer.api.Client;
import com.app.finxi.githubviewer.api.Service;
import com.app.finxi.githubviewer.model.Item;
import com.app.finxi.githubviewer.model.ItemResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryActivity extends AppCompatActivity {

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
                loadJSON();
                Toast.makeText(RepositoryActivity.this, "Pull requests do Github recarregados", Toast.LENGTH_SHORT).show();
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
        loadJSON();
    }

    private void loadJSON() {
        Disconnected = findViewById(R.id.disconnectedRep);
        try {

            repoUrl = getIntent().getExtras().getString("repo_url");

            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<JSONArray> call = apiService.getRepository(repoUrl);
            call.enqueue(new Callback<JSONArray>() {
                @Override
                public void onResponse(Call<JSONArray> call, Response<JSONArray> response) {

                    JSONArray json = null;
                    HashMap<String, String> map = null;
                    JSONObject jsonObject = null;
                    JSONObject innerJSONobj = null;
                    List<HashMap<String, String>> repoItems = null;

                    try {
                        json = new JSONArray(response.body());

                        for(int i=0;i<json.length();i++){
                            map = new HashMap<String, String>();
                            jsonObject = json.getJSONObject(i);
                            innerJSONobj = jsonObject.getJSONObject("user");

                            map.put("id",  jsonObject.getString("id"));
                            map.put("number", jsonObject.getString("number"));
                            map.put("title", jsonObject.getString("title"));
                            map.put("body", jsonObject.getString("body"));
                            map.put("created_at", jsonObject.getString("created_at"));
                            map.put("login", innerJSONobj.getString("login"));
                            map.put("avatar_url", innerJSONobj.getString("avatar_url"));

                            repoItems.add(map);
                        }

                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    recyclerView.setAdapter(new PRAdapter(getApplicationContext(), repoItems));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<JSONArray> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(RepositoryActivity.this, "Erro ao carregar os dados!", Toast.LENGTH_SHORT).show();
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
