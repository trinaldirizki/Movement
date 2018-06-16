package movement.com.movement;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OverviewActivity extends AppCompatActivity {

    DatabaseReference mCharityMovementRef;
    DatabaseReference mMovementRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        mCharityMovementRef = FirebaseDatabase.getInstance().getReference().child("charity_movements");
        mMovementRef = FirebaseDatabase.getInstance().getReference().child("movements");

        String charityUid;
        charityUid = getIntent().getExtras().getString("charity_uid");
        Log.d("charity_uid", charityUid);
    }

    public void startMoving(View view) {
        Intent intent = new Intent(this, ProgressActivity.class);
        startActivity(intent);
    }
}
