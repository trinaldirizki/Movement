package movement.com.movement;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import movement.com.movement.adapter.CharityAdapter;
import movement.com.movement.model.Charity;
import movement.com.movement.viewholder.CharityViewHolder;

public class SelectCharityActivity extends AppCompatActivity {

    DatabaseReference mCharityRef;
    RecyclerView mRecyclerCharity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_charity);

        mCharityRef = FirebaseDatabase.getInstance().getReference().child("charities");

        mRecyclerCharity = findViewById(R.id.recycler_view);
        mRecyclerCharity.setHasFixedSize(true);
        mRecyclerCharity.setLayoutManager(new GridLayoutManager(this, 3));

        // initRecyclerCharity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Charity> options = new FirebaseRecyclerOptions.Builder<Charity>()
                                                    .setQuery(mCharityRef, Charity.class)
                                                    .build();

        FirebaseRecyclerAdapter<Charity, CharityViewHolder> charityAdapter = new FirebaseRecyclerAdapter<Charity, CharityViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CharityViewHolder holder, int position, Charity model) {
                holder.setImageCharity(getApplicationContext(), model.getImageUrl());
                holder.setOnSelectedCharity(getApplicationContext(), model.getUid());
            }

            @Override
            public CharityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.cardview_item_charity, parent, false);
                return new CharityViewHolder(view);
            }
        };

        charityAdapter.startListening();
        mRecyclerCharity.setAdapter(charityAdapter);
    }

    //    private void initRecyclerCharity() {
//        CharityAdapter charityAdapter = new CharityAdapter(this, Arrays.asList(Charity.charities));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setAdapter(charityAdapter);
//    }
}
