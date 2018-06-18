package movement.com.movement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import movement.com.movement.util.ScreenNavigator;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    FrameLayout mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view_right);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentContainer = findViewById(R.id.fragment_container_main);

        openHomeFragment();
    }

    private void openHomeFragment() {
        HomeFragment fragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container_main, fragment, "HOME_FRAGMENT").commit();
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
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        if (id == R.id.navigation_menu) {
            drawerLayout.openDrawer(GravityCompat.END);
        } else if (id == R.id.news_feed) {
            ScreenNavigator.navigateTo(getApplicationContext(), NewsActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String text = "";
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_profile:
                ScreenNavigator.navigateTo(getApplicationContext(), ProfileActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
            case R.id.nav_leader_board:
                text = getString(R.string.leader_board);
                break;
            case R.id.nav_news:
                ScreenNavigator.navigateTo(getApplicationContext(), NewsActivity.class);
                break;
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
                ScreenNavigator.navigateTo(getApplicationContext(), LoginActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
        }

        if (text.length() > 0) {
            Toast.makeText(this, text + " currently unavailable.", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    @Override
    public void onFragmentInteraction(Parcelable parcelable) {

    }
}
