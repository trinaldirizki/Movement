package movement.com.movement;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;
import movement.com.movement.model.User;
import movement.com.movement.util.ScreenNavigator;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        NewsFragment.OnFragmentInteractionListener {

    NavigationView mNavigationView;
    FrameLayout mFragmentContainer;
    CircleImageView mImageUser;
    TextView mTextUsername, mTextEmail;

    DatabaseReference mUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavigationView = findViewById(R.id.nav_view_right);
        mNavigationView.setNavigationItemSelectedListener(this);
        View view = mNavigationView.getHeaderView(0);
        mImageUser = view.findViewById(R.id.image_header_user);
        mTextUsername = view.findViewById(R.id.text_header_username);
        mTextEmail = view.findViewById(R.id.text_header_email);

        mFragmentContainer = findViewById(R.id.fragment_container_main);

        mUserRef = FirebaseDatabase.getInstance().getReference().child("users");

        openHomeFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();

        initUserRef();
    }

    private void initHeaderView(User user) {
        Glide.with(this).load(user.getPhotoUrl()).into(mImageUser);
        mTextUsername.setText(user.getName());
        mTextEmail.setText(user.getEmail());
    }

    private void openHomeFragment() {
        HomeFragment fragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container_main, fragment, "HOME_FRAGMENT").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
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
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_menu:
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.openDrawer(GravityCompat.END);
                break;
            case R.id.news_feed:
                displayFragment(new NewsFragment());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String text = "";
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_leader_board:
                text = getString(R.string.leader_board);
                break;
            case R.id.nav_news:
                fragment = new NewsFragment();
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
        } else {
            displayFragment(fragment);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    private void displayFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_main, fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(Parcelable parcelable) {

    }

    private void initUserRef() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            final String uid = user.getUid();
            Query query = mUserRef.orderByKey().equalTo(uid);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.child(uid).getValue(User.class);
                        if (user != null) initHeaderView(user);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("FirebaseDatabase", databaseError.getMessage());
                }
            });
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
