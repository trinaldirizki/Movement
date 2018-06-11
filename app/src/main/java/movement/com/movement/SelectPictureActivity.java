package movement.com.movement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import movement.com.movement.adapter.PictureAdapter;

public class SelectPictureActivity extends AppCompatActivity {

    private List<String> mImageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);

        initImageList();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recycler_picture);
        recyclerView.setLayoutManager(layoutManager);
        PictureAdapter adapter = new PictureAdapter(this, mImageList);
        recyclerView.setAdapter(adapter);
    }

    private void initImageList() {
        mImageList.add("http://via.placeholder.com/200.png");
        mImageList.add("http://via.placeholder.com/150.png");
        mImageList.add("http://via.placeholder.com/250.png");
        mImageList.add("http://via.placeholder.com/300.png");
        mImageList.add("http://via.placeholder.com/350.png");
        mImageList.add("http://via.placeholder.com/400.png");
        mImageList.add("http://via.placeholder.com/225.png");
        mImageList.add("http://via.placeholder.com/175.png");
        mImageList.add("http://via.placeholder.com/275.png");
        mImageList.add("http://via.placeholder.com/325.png");
    }

}
