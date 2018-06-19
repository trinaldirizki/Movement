package movement.com.movement;

import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import movement.com.movement.model.Sponsor;
import movement.com.movement.util.ScreenNavigator;

public class CountdownActivity extends AppCompatActivity {

    DatabaseReference mSponsorRef;
    ImageView mImageSponsor;
    TextView mTextCountdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        mSponsorRef = FirebaseDatabase.getInstance().getReference().child("sponsors");

        mImageSponsor = findViewById(R.id.image_sponsor);
        mTextCountdown = findViewById(R.id.text_countdown);

        initSponsor();
        initCountdown();
    }

    private void initCountdown() {
        new CountDownTimer(3900, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                String text = String.valueOf(millisUntilFinished / 1000);
                mTextCountdown.setText(text);
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }

    private void initSponsor() {
        Query query = mSponsorRef.orderByKey().equalTo("001");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    Sponsor sponsor = dataSnapshot.child("001").getValue(Sponsor.class);
                    showSponsor(sponsor);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showSponsor(Sponsor sponsor) {
        Glide.with(this).load(sponsor.getLogoUrl()).into(mImageSponsor);
    }
}
