package movement.com.movement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import movement.com.movement.adapter.NewsAdapter;
import movement.com.movement.model.News;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initRecylerNews();
    }

    private void initRecylerNews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerNews = findViewById(R.id.recycler_news);
        recyclerNews.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(this, new List<News>());
        recyclerNews.setAdapter(adapter);
    }
}
