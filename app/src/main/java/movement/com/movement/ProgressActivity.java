package movement.com.movement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import movement.com.movement.model.Sponsor;
import movement.com.movement.util.ScreenNavigator;

public class ProgressActivity extends AppCompatActivity {

    ImageView mImageLogo, mImageAd;
    TextView mTextDonation, mTextDuration, mTextDistance;
    ImageButton mButtonMusic;
    Button mButtonFinish;

    Sponsor mSponsor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        mImageLogo = findViewById(R.id.image_progress_sponsor);
        mImageAd = findViewById(R.id.image_progress_ad);
        mTextDonation = findViewById(R.id.text_progress_donation);
        mTextDuration = findViewById(R.id.text_progress_duration);
        mTextDistance = findViewById(R.id.text_progress_distance);
        mButtonFinish = findViewById(R.id.button_progress_finish);
        mButtonMusic = findViewById(R.id.button_play_music);

        mSponsor = getIntent().getParcelableExtra("parcel_sponsor");
        if (mSponsor != null) showSponsor();

        mButtonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenNavigator.navigateTo(getApplicationContext(), SelectPictureActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        });
    }

    private void showSponsor() {
        Glide.with(this).load(mSponsor.getLogoUrl()).into(mImageLogo);
        Glide.with(this).load(mSponsor.getAdUrl()).into(mImageAd);

    }
}
