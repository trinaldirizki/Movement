package movement.com.movement;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import movement.com.movement.model.Movement;
import movement.com.movement.util.StringFormatter;

public class OverviewActivity extends AppCompatActivity {

    ImageView mImage;
    TextView mTextTargetDonation, mTextDistance, mTextCurrentDonation, mTextProgress, mTextTitle, mTextDetail;
    ProgressBar mProgressBar;

    Movement mMovement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        mImage = findViewById(R.id.image_overview);
        mTextTargetDonation = findViewById(R.id.text_overview_goal);
        mTextDistance = findViewById(R.id.text_overview_distance);
        mTextCurrentDonation = findViewById(R.id.text_overview_donation);
        mTextProgress = findViewById(R.id.text_overview_progress);
        mTextTitle = findViewById(R.id.text_overview_title);
        mTextDetail = findViewById(R.id.text_overview_detail);
        mProgressBar = findViewById(R.id.progress_overview);

        mMovement = getIntent().getParcelableExtra("parcel_movement");
        if (mMovement != null) showMovement(mMovement);
    }


    private void showMovement(Movement movement) {
        Glide.with(this).load(movement.getImageUrl()).into(mImage);
        mTextTargetDonation.setText(StringFormatter.convertCurrency(movement.getTargetDonation()));
        mTextDistance.setText(StringFormatter.convertDistance(movement.getTargetDistance()));
        mTextCurrentDonation.setText(StringFormatter.convertCurrency(movement.getCurrentDonation()));
        mTextTitle.setText(movement.getName());
        mTextDetail.setText(movement.getDetail());

        int progress = (int) ((float) movement.getCurrentDistance() / movement.getTargetDistance() * 100);
        mProgressBar.setProgress(progress);
        String progressText = String.valueOf(progress) + "% achieved";
        mTextProgress.setText(progressText);
    }

    public void startMoving(View view) {
        Intent intent = new Intent(this, CountdownActivity.class);
        startActivity(intent);
    }
}
