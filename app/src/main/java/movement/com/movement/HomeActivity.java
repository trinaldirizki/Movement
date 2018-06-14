package movement.com.movement;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    Button mStartActivity;
    ImageView mImageWalking, mImageRunning, mImageCycling, mImageChecked;
    ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view_right);
        navigationView.setNavigationItemSelectedListener(this);

        mLayout = findViewById(R.id.layoutMain);
        mImageWalking = findViewById(R.id.imageWalking);
        mImageRunning = findViewById(R.id.imageRunning);
        mImageCycling = findViewById(R.id.imageCycling);
        mImageChecked = findViewById(R.id.imageChecked);
        mStartActivity = findViewById(R.id.buttonStartActivity);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Closing App")
                    .setMessage("Are you sure you want to close this activity?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        if (id == R.id.navigation_menu) {
            drawerLayout.openDrawer(GravityCompat.END);
        } else if (id == R.id.news_feed) {
            navigateTo(NewsActivity.class, false);
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateTo(Class<?> activity, boolean clearTask) {
        Intent intent = new Intent(getApplicationContext(), activity);
        if (clearTask) intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String text = "";
        switch (item.getItemId()){
            case R.id.nav_home:
                navigateTo(HomeActivity.class,false);
                return true;
            case R.id.nav_profile:
                navigateTo(ProfileActivity.class,false);
                return true;
            case R.id.nav_leader_board:
                text = getString(R.string.leader_board);
                break;
            case R.id.nav_news:
                navigateTo(NewsActivity.class,false);
                return true;
            case R.id.nav_help:
                text = getString(R.string.help_center);
                break;
            case R.id.nav_share:
                text = getString(R.string.share);
                break;
            case R.id.nav_settings:
                text = getString(R.string.settings);
                break;
            case R.id.nav_sign_out:
                FirebaseAuth.getInstance().signOut();
                navigateTo(LoginActivity.class, true);
                return true;
        }

        Toast.makeText(this, text + " currently unavailable.", Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
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

        Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
    }

    public void selectCharity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
