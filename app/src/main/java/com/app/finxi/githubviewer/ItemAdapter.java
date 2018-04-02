package com.app.finxi.githubviewer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.finxi.githubviewer.controller.DetailActivity;
import com.app.finxi.githubviewer.model.Item;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


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

        /*Picasso.Builder builder = new Picasso.Builder(MyApplication.getAppContext());
        Picasso picasso = builder.build();
        picasso.load(items.get(i).getAvatarUrl() + "")
                .fit()
                .placeholder(R.drawable.load)
                .noFade()
                .into(viewHolder.imageView);*/

        /*Picasso.get()
                .load(items.get(i).getAvatarUrl())
                .placeholder(R.drawable.load)
                .into(viewHolder.imageView);*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username, repo_name, description, forks, stars;
        private ImageView imageView;


        public ViewHolder(View view) {
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
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Item clickedDataItem = items.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("login", items.get(pos).getLogin());
                        //intent.putExtra("html_url", items.get(pos).getHtmlUrl());
                        //intent.putExtra("avatar_url", items.get(pos).getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked on user" + clickedDataItem.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }
}
