package movement.com.movement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import movement.com.movement.model.User;

public class ProfileActivity extends AppCompatActivity {

    BarChart mBarChart;
    DatabaseReference mDatabase;

    CircleImageView mProfileImage;
    ProgressBar mProgressLevel;
    TextView mProfileName, mLevelMin, mLevelMax, mLevel, mDonation, mMovement, mDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mBarChart = findViewById(R.id.bar_chart);
        mProfileImage = findViewById(R.id.image_profile);
        mProfileName = findViewById(R.id.text_profile_name);
        mProgressLevel = findViewById(R.id.progress_level);
        mLevelMin = findViewById(R.id.text_level_min);
        mLevelMax = findViewById(R.id.text_level_max);
        mLevel = findViewById(R.id.text_level);
        mDonation = findViewById(R.id.text_profile_donation);
        mMovement = findViewById(R.id.text_profile_movement);
        mDistance = findViewById(R.id.text_profile_distance);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        initBarDataset();
    }

    @Override
    protected void onStart() {
        super.onStart();

        initProfile();
    }

    private void initProfile() {
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = mDatabase.child("users").orderByKey().equalTo(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    addProfileData(dataSnapshot.child(uid));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addProfileData(DataSnapshot dataSnapshot) {
        User user = dataSnapshot.getValue(User.class);
        mProfileName.setText(user.getName());
        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).into(mProfileImage);
        mDonation.setText(convertCurrency(user.getTotalDonation()));
        mMovement.setText(String.valueOf(user.getTotalMovement()));
        mDistance.setText(convertDistance(user.getTotalDistance()));
    }

    private String convertCurrency(int value){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("Rp ###,###,###,###", symbols);
        String formattedText = decimalFormat.format(value);
        return formattedText;
    }

    private String convertDistance(int value){
        float km = (float) value / 1000;
        String formattedText = km + " km";
        return formattedText;
    }

    private void initBarDataset() {
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0f, 8));
        barEntries.add(new BarEntry(1f, 1));
        barEntries.add(new BarEntry(2f, 2));
        barEntries.add(new BarEntry(3f, 3));
        barEntries.add(new BarEntry(4f, 4));
        barEntries.add(new BarEntry(6f, 5));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Months");

        final List<String> months = new ArrayList<>();
        months.add("Mon");
        months.add("Tue");
        months.add("Wed");
        months.add("Thu");
        months.add("Fri");
        months.add("Sat");
        months.add("Sun");

        BarData barData = new BarData(barDataSet);
        mBarChart.setData(barData);
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months.get((int) value);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_share_profile:
                break;
            case R.id.menu_edit_profile:
                break;
        }

        Toast.makeText(this, "Currently available", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
