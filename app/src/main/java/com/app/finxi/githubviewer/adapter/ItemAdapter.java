package com.app.finxi.githubviewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.finxi.githubviewer.R;
import com.app.finxi.githubviewer.controller.PullRequestActivity;
import com.app.finxi.githubviewer.model.Item;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(Context applicationContext, List<Item> itemArrayList) {
        this.context = applicationContext;
        this.items = new ArrayList<>(itemArrayList);
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_repo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.username.setText(items.get(i).getItemOwner().getLogin());
        viewHolder.repo_name.setText(items.get(i).getRepoName());
        viewHolder.description.setText(items.get(i).getDescription());
        viewHolder.forks.setText(items.get(i).getForks());
        viewHolder.stars.setText(items.get(i).getStars());
        viewHolder.possui_issue.setText(items.get(i).isHasIssues() ? "Sim" : "Não");

        Glide.with(context)
                .load(items.get(i).getItemOwner().getAvatar_url())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.load))
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username, repo_name, description, forks, stars, possui_issue;
        private ImageView imageView;


        ViewHolder(final View view) {
            super(view);

            imageView = view.findViewById(R.id.cover);
            username = view.findViewById(R.id.username);
            repo_name = view.findViewById(R.id.repo_name);
            forks = view.findViewById(R.id.forks);
            stars = view.findViewById(R.id.stars);
            description = view.findViewById(R.id.descricao);
            possui_issue = view.findViewById(R.id.possui_issue);

            //ir para a nova activity para gerar a lista de repositórios
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final int pos = getAdapterPosition();

                    String repoUrl = "https://api.github.com/search/issues?q="
                            + items.get(pos).getRepoName() + "&type=Issues&page=";

                    Intent intent = new Intent(context, PullRequestActivity.class);
                    intent.putExtra("repo_url", repoUrl);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
