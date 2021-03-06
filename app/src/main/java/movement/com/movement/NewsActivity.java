package movement.com.movement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import movement.com.movement.adapter.NewsAdapter;
import movement.com.movement.model.News;
import movement.com.movement.viewholder.NewsViewHolder;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerNews;
    private DatabaseReference mNewsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mNewsRef = FirebaseDatabase.getInstance().getReference().child("news");
        mNewsRef.keepSynced(true);

        mRecyclerNews = findViewById(R.id.recycler_news);
        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<News> options = new FirebaseRecyclerOptions.Builder<News>()
                                                    .setQuery(mNewsRef, News.class)
                                                    .build();

        FirebaseRecyclerAdapter<News, NewsViewHolder> newsAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>(options){
            @NonNull
            @Override
            public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.card_news, parent, false);
                return new NewsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull NewsViewHolder holder, int position, @NonNull News model) {
                holder.setTitle(model.getTitle());
                holder.setDate(model.getDate());
                holder.setImage(getApplicationContext(), model.getImageUrl());
            }
        };

        newsAdapter.startListening();
        mRecyclerNews.setAdapter(newsAdapter);

    }
}
