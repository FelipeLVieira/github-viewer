package com.app.finxi.githubviewer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.finxi.githubviewer.api.Client;
import com.app.finxi.githubviewer.api.Service;
import com.app.finxi.githubviewer.controller.DetailActivity;
import com.app.finxi.githubviewer.controller.MainActivity;
import com.app.finxi.githubviewer.model.Item;
import com.app.finxi.githubviewer.model.ItemResponse;
import com.app.finxi.githubviewer.model.RepositoryItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(Context applicationContext, List<Item> itemArrayList) {
        this.context = applicationContext;
        this.items = itemArrayList;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.username.setText(items.get(i).getOwner().getLogin());
        viewHolder.repo_name.setText(items.get(i).getRepoName());
        viewHolder.description.setText(items.get(i).getDescription());
        viewHolder.forks.setText(items.get(i).getForks());
        viewHolder.stars.setText(items.get(i).getStars());

        Glide.with(context)
                .load(items.get(i).getOwner().getAvatar_url())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.load))
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username, repo_name, description, forks, stars;
        private ImageView imageView;


        ViewHolder(final View view) {
            super(view);


            imageView = view.findViewById(R.id.cover);
            username = view.findViewById(R.id.username);
            repo_name = view.findViewById(R.id.repo_name);
            forks = view.findViewById(R.id.forks);
            stars = view.findViewById(R.id.stars);
            description = view.findViewById(R.id.descricao);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final int pos = getAdapterPosition();

                    Client Client = new Client();
                    Service apiService =
                            Client.getClient().create(Service.class);

                    Call<RepositoryItem> call = apiService.getRepository("");
                    call.enqueue(new Callback<RepositoryItem>() {
                        @Override
                        public void onResponse(Call<RepositoryItem> call, Response<RepositoryItem> response) {
                            if (pos != RecyclerView.NO_POSITION) {

                                Item clickedDataItem = items.get(pos);

                                Intent intent = new Intent(context, DetailActivity.class);
                                intent.putExtra("login", items.get(pos).getOwner().getLogin());
                                intent.putExtra("avatar_url", items.get(pos).getOwner().getAvatar_url());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                context.startActivity(intent);
                                Toast.makeText(v.getContext(), "You clicked on user" + clickedDataItem.getOwner().getLogin(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RepositoryItem> call, Throwable t) {
                            Log.d("Error", t.getMessage());
                            Toast.makeText(context, "Erro ao carregar os dados!", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
        }
    }
}
