package movement.com.movement;

import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    Button mStartActivity;
    ImageView mImageWalking, mImageRunning, mImageCycling, mImageChecked;
    ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.layoutMain);
        mImageWalking = findViewById(R.id.imageWalking);
        mImageRunning = findViewById(R.id.imageRunning);
        mImageCycling = findViewById(R.id.imageCycling);
        mImageChecked = findViewById(R.id.imageChecked);
        mStartActivity = findViewById(R.id.buttonStartActivity);
    }

    public void selectActivity(View view){
        String selected = "";
        if (view == mImageWalking){
            selected = "walking";
        } else if (view == mImageRunning){
            selected = "running";
        } else {
            selected = "cycling";
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mLayout);

        constraintSet.connect(mImageChecked.getId(), ConstraintSet.BOTTOM, view.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(mImageChecked.getId(), ConstraintSet.END, view.getId(), ConstraintSet.END);
        constraintSet.applyTo(mLayout);
    }

}
