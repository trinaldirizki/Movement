package movement.com.movement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import movement.com.movement.adapter.CharityAdapter;
import movement.com.movement.model.Charity;

public class SelectCharityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_charity);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        CharityAdapter charityAdapter = new CharityAdapter(this, Arrays.asList(Charity.charities));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(charityAdapter);
    }
}
