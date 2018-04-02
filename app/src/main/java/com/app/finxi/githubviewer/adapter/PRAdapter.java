package com.app.finxi.githubviewer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.finxi.githubviewer.R;
import com.app.finxi.githubviewer.model.PullRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;


public class PRAdapter extends RecyclerView.Adapter<PRAdapter.ViewHolder> {
    private List<PullRequest> pullRequests;
    private Context context;

    public PRAdapter(Context applicationContext, List<PullRequest> pullRequests) {
        this.context = applicationContext;
        this.pullRequests = new ArrayList<PullRequest>(pullRequests);
    }

    @Override
    public PRAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_pull, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        /*viewHolder.username.setText(items.get(i).get("login"));
        viewHolder.pr_name.setText(items.get(i).get("name"));
        viewHolder.pr_body.setText(items.get(i).get("body"));
        viewHolder.pr_date.setText(items.get(i).get("created_at"));*/

        viewHolder.username.setText(pullRequests.get(i).getUser().getLogin());

        Glide.with(context)
                .load(pullRequests.get(i).getUser().getAvatar_url())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.load))
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView username, pr_body, pr_name, pr_date;
        private ImageView imageView;


        ViewHolder(final View view) {
            super(view);

            imageView = view.findViewById(R.id.cover);
            username = view.findViewById(R.id.username);
            pr_name = view.findViewById(R.id.pull_request_name);
            pr_date = view.findViewById(R.id.pull_request_date);
            pr_body = view.findViewById(R.id.pull_request_body);

            //ir para o detalhe do repositório
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final int pos = getAdapterPosition();

                    /*call.enqueue(new Callback<RepositoryItem>() {
                        @Override
                        public void onResponse(Call<RepositoryItem> call, Response<RepositoryItem> response) {
                            if (pos != RecyclerView.NO_POSITION) {

                                Item clickedDataItem = items.get(pos);

                                Intent intent = new Intent(context, DetailActivity.class);
                                intent.putExtra("login", items.get(pos).getItemOwner().getLogin());
                                intent.putExtra("avatar_url", items.get(pos).getItemOwner().getAvatar_url());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                context.startActivity(intent);
                                Toast.makeText(v.getContext(), "You clicked on user" + clickedDataItem.getItemOwner().getLogin(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RepositoryItem> call, Throwable t) {
                            Log.d("Error", t.getMessage());
                            Toast.makeText(context, "Erro ao carregar os dados!", Toast.LENGTH_SHORT).show();

                        }
                    });*/
                }
            });
        }
    }
}
