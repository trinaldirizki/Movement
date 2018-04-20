package movement.com.movement;

import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import movement.com.movement.util.DesignUtil;

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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.news_feed:
                return true;
            case R.id.navigation_menu:
                return true;
        }

        return super.onOptionsItemSelected(item);
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
