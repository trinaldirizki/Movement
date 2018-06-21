package movement.com.movement;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

public class ProgressFragment extends Fragment {
    private static final String PARCEL_SPONSOR = "parcelSponsor";

    private OnFragmentInteractionListener mListener;

    ImageView mImageLogo, mImageAd;
    static TextView mTextDonation, mTextDuration, mTextDistance;
    ImageButton mButtonMusic, mButtonStop, mButtonPause;

    DatabaseReference mSponsorRef;
    Sponsor mSponsor;

    LocationManager mLocationManager;
    static boolean status;
    static long startTime, endTime;
    static ProgressDialog progressDialog;
    static int p = 0;

    LocationService mService;

    public ProgressFragment() {
        // Required empty public constructor
    }

    public static ProgressFragment newInstance(Parcelable parcelable) {
        ProgressFragment fragment = new ProgressFragment();
        Bundle args = new Bundle();
        args.putParcelable(PARCEL_SPONSOR, parcelable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSponsor = getArguments().getParcelable(PARCEL_SPONSOR);
        }

        mSponsorRef = FirebaseDatabase.getInstance().getReference().child("sponsors");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        mImageLogo = view.findViewById(R.id.image_progress_sponsor);
        mImageAd = view.findViewById(R.id.image_progress_ad);
        mTextDonation = view.findViewById(R.id.text_progress_donation);
        mTextDuration = view.findViewById(R.id.text_progress_duration);
        mTextDistance = view.findViewById(R.id.text_progress_distance);
        mButtonStop = view.findViewById(R.id.button_progress_stop);
        mButtonPause = view.findViewById(R.id.button_progress_pause);
        mButtonMusic = view.findViewById(R.id.button_play_music);
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenNavigator.navigateTo(getContext(), SelectPictureActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onProgressFragmentInteraction(uri);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        initSponsor();
    }

    public interface OnFragmentInteractionListener {
        void onProgressFragmentInteraction(Parcelable parcelable);
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
        if (getContext() == null) return;
        Glide.with(getContext()).load(sponsor.getLogoUrl()).into(mImageLogo);
        Glide.with(getContext()).load(sponsor.getAdUrl()).into(mImageAd);
    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationService.LocalBinder binder = (LocationService.LocalBinder) service;
            mService = binder.getService();
            status = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            status = false;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (status == true){
            unbindService();
        }
    }

    private void unbindService() {
        if (status == false) return;
        Intent intent = new Intent(getContext(), LocationService.class);
        mService.unbindService(sc);
        status = false;
    }
}
