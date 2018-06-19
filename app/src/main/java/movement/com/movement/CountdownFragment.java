package movement.com.movement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CountdownFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    DatabaseReference mSponsorRef;
    ImageView mImageSponsor;
    TextView mTextCountdown;
    Sponsor mSponsor;

    public CountdownFragment() {
        // Required empty public constructor
    }

    public static CountdownFragment newInstance(String param1, String param2) {
        CountdownFragment fragment = new CountdownFragment();
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

        mSponsorRef = FirebaseDatabase.getInstance().getReference().child("sponsors");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);

        mImageSponsor = view.findViewById(R.id.image_sponsor);
        mTextCountdown = view.findViewById(R.id.text_countdown);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCountdownFragmentInteraction(uri);
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
        initCountdown();
    }

    public interface OnFragmentInteractionListener {
        void onCountdownFragmentInteraction(Uri uri);
    }

    private void initCountdown() {
        new CountDownTimer(3900, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                String text = String.valueOf(millisUntilFinished / 1000);
                mTextCountdown.setText(text);
            }

            @Override
            public void onFinish() {
                openProgressFragment();
            }
        }.start();
    }

    private void openProgressFragment() {
        Fragment fragment = new ProgressFragment();
        if (getActivity() != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_progress, fragment, fragment.getClass().getSimpleName());
            transaction.commit();
        }
    }

    private void initSponsor() {
        Query query = mSponsorRef.orderByKey().equalTo("001");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mSponsor = dataSnapshot.child("001").getValue(Sponsor.class);
                    showSponsor();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showSponsor() {
        Glide.with(this).load(mSponsor.getLogoUrl()).into(mImageSponsor);
    }
}
