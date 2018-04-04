package com.app.finxi.githubviewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        this.pullRequests = new ArrayList<>(pullRequests);
    }

    @Override
    public PRAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_pull, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.pr_name.setText(pullRequests.get(i).getTitle());
        viewHolder.pr_body.setText(pullRequests.get(i).getBody());
        viewHolder.pr_date.setText(pullRequests.get(i).getCreated_at());

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

                    PullRequest clickedDataPR = pullRequests.get(pos);

                    String url = clickedDataPR.getHtml_url();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);
                    Toast.makeText(v.getContext(), "Selecionado o user " + clickedDataPR.getUser().getLogin(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
