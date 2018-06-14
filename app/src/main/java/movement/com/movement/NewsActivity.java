package movement.com.movement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import movement.com.movement.adapter.NewsAdapter;
import movement.com.movement.model.News;

public class NewsActivity extends AppCompatActivity {

    private List<News> mNewsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initNewsList();
        initRecyclerNews();
    }

    private void initNewsList() {
        mNewsList.add(new News("1", "25 million rupiah have been raised for Difable Empowering", "25 May 2018", "http://via.placeholder.com/400.png"));
        mNewsList.add(new News("2", "25 million rupiah have been raised for Difable Empowering", "25 May 2018", "http://via.placeholder.com/400.png"));
        mNewsList.add(new News("3", "25 million rupiah have been raised for Difable Empowering", "25 May 2018", "http://via.placeholder.com/400.png"));
        mNewsList.add(new News("4", "25 million rupiah have been raised for Difable Empowering", "25 May 2018", "http://via.placeholder.com/400.png"));
        mNewsList.add(new News("5", "25 million rupiah have been raised for Difable Empowering", "25 May 2018", "http://via.placeholder.com/400.png"));
    }

    private void initRecyclerNews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerNews = findViewById(R.id.recycler_news);
        recyclerNews.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(this, mNewsList);
        recyclerNews.setAdapter(adapter);
    }
}
