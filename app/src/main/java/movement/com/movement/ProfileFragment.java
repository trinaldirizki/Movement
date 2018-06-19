package movement.com.movement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import movement.com.movement.model.User;
import movement.com.movement.util.StringFormatter;


public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    BarChart mBarChart;
    DatabaseReference mUserRef;

    CircleImageView mProfileImage;
    ProgressBar mProgressLevel;
    TextView mProfileName, mLevelMin, mLevelMax, mLevel, mDonation, mMovement, mDistance;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mUserRef = FirebaseDatabase.getInstance().getReference().child("users");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mBarChart = view.findViewById(R.id.bar_chart);
        mProfileImage = view.findViewById(R.id.image_profile);
        mProfileName = view.findViewById(R.id.text_profile_name);
        mProgressLevel = view.findViewById(R.id.progress_level);
        mLevelMin = view.findViewById(R.id.text_level_min);
        mLevelMax = view.findViewById(R.id.text_level_max);
        mLevel = view.findViewById(R.id.text_level);
        mDonation = view.findViewById(R.id.text_profile_donation);
        mMovement = view.findViewById(R.id.text_profile_movement);
        mDistance = view.findViewById(R.id.text_profile_distance);

        initBarDataset();
        setHasOptionsMenu(true);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
        if (compatActivity != null){
            ActionBar actionBar = compatActivity.getSupportActionBar();
            if (actionBar != null){
                actionBar.setTitle("Profile");
            }
        }

        if (this.isAdded()) initUserRef();
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_share_profile:
                break;
            case R.id.menu_edit_profile:
                break;
        }

        Toast.makeText(getContext(), "Currently available", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    private void initUserRef() {
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = mUserRef.orderByKey().equalTo(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    User user = dataSnapshot.child(uid).getValue(User.class);
                    addProfileData(getContext(), user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addProfileData(Context context, User user) {
        mProfileName.setText(user.getName());
        Glide.with(context).load(user.getPhotoUrl()).into(mProfileImage);
        mDonation.setText(StringFormatter.convertCurrency(user.getTotalDonation()));
        mMovement.setText(String.valueOf(user.getTotalMovement()));
        mDistance.setText(StringFormatter.convertDistance(user.getTotalDistance()));
    }
}
