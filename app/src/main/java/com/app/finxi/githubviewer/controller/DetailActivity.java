package com.app.finxi.githubviewer.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.finxi.githubviewer.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class DetailActivity extends AppCompatActivity {
    TextView Link, Username;
    Toolbar mActionBarToolbar;
    ImageView imageView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.user_image);
        Username = findViewById(R.id.username);

        Link = findViewById(R.id.pull_request_name);

        String username = getIntent().getExtras().getString("login");
        String avatarUrl = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        Username.setText(username);

        Glide.with(this)
                .load(avatarUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.load))
                .into(imageView);

        getSupportActionBar().setTitle("Details Activity");
    }

    private Intent createShareForcastIntent() {
        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("link");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome developer @" + username + ", " + link)
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForcastIntent());
        return true;
    }
}
