package movement.com.movement;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import movement.com.movement.util.ScreenNavigator;


public class HomeFragment extends Fragment {

    private static final String PARCEL_USER = "parcelUser";
    private Parcelable mParcelableUser;
    private OnFragmentInteractionListener mListener;

    Button mButtonStart;
    ImageView mImageWalking, mImageRunning, mImageCycling, mImageChecked;
    ConstraintLayout mLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(Parcelable parcelable) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putParcelable(PARCEL_USER, parcelable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParcelableUser = getArguments().getParcelable(PARCEL_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mLayout = view.findViewById(R.id.layout_home);
        mImageWalking = view.findViewById(R.id.imageWalking);
        mImageRunning = view.findViewById(R.id.imageRunning);
        mImageCycling = view.findViewById(R.id.imageCycling);
        mImageChecked = view.findViewById(R.id.imageChecked);
        mButtonStart = view.findViewById(R.id.buttonStartActivity);

        mImageWalking.setOnClickListener(activityOnClickListener);
        mImageRunning.setOnClickListener(activityOnClickListener);
        mImageCycling.setOnClickListener(activityOnClickListener);
        mButtonStart.setOnClickListener(activityStartListener);
        return view;
    }

    public void onButtonPressed(Parcelable parcelable) {
        if (mListener != null) {
            mListener.onFragmentInteraction(parcelable);
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Parcelable parcelable);
    }

    private View.OnClickListener activityOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            selectActivity(v);
        }
    };

    private View.OnClickListener activityStartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ScreenNavigator.navigateTo(getContext(), SelectCharityActivity.class);
        }
    };

    public void selectActivity(View view) {
        String selected = "";
        if (view == mImageWalking) {
            selected = "walking";
        } else if (view == mImageRunning) {
            selected = "running";
        } else {
            selected = "cycling";
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mLayout);

        constraintSet.connect(mImageChecked.getId(), ConstraintSet.BOTTOM, view.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(mImageChecked.getId(), ConstraintSet.END, view.getId(), ConstraintSet.END);
        constraintSet.applyTo(mLayout);

        Toast.makeText(getContext(), selected, Toast.LENGTH_SHORT).show();
    }

}
