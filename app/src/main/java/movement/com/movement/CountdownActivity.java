package movement.com.movement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CountdownActivity extends AppCompatActivity {

    DatabaseReference mSponsorRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        mSponsorRef = FirebaseDatabase.getInstance().getReference().child("sponsor");

        initSponsor();
    }

    private void initSponsor() {

    }
}
