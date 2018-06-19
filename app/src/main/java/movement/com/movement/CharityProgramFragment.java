package movement.com.movement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import movement.com.movement.model.Movement;
import movement.com.movement.util.StringFormatter;

public class CharityProgramFragment extends Fragment {
    private static final String PARCEL_CHARITY = "parcelCharity";

    private Movement mMovement;

    private OnFragmentInteractionListener mListener;

    ImageView mImage;
    TextView mTextTargetDonation, mTextDistance, mTextCurrentDonation, mTextProgress, mTextTitle, mTextDetail;
    ProgressBar mProgressBar;
    Button mButtonStart;

    public CharityProgramFragment() {
        // Required empty public constructor
    }

    public static CharityProgramFragment newInstance(Parcelable parcelable) {
        CharityProgramFragment fragment = new CharityProgramFragment();
        Bundle args = new Bundle();
        args.putParcelable(PARCEL_CHARITY, parcelable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovement = getArguments().getParcelable(PARCEL_CHARITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charity_program, container, false);

        mImage = view.findViewById(R.id.image_program);
        mTextTargetDonation = view.findViewById(R.id.text_program_target_donation);
        mTextDistance = view.findViewById(R.id.text_program_target_distance);
        mTextCurrentDonation = view.findViewById(R.id.text_program_current_donation);
        mTextProgress = view.findViewById(R.id.text_program_percentage);
        mTextTitle = view.findViewById(R.id.text_program_title);
        mTextDetail = view.findViewById(R.id.text_program_detail);
        mProgressBar = view.findViewById(R.id.progress_program);
        mButtonStart = view.findViewById(R.id.button_program_start);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mMovement != null) {
            showMovementDetail();
        }
    }

    private void showMovementDetail() {
        if (getContext() == null) return;

        Glide.with(getContext()).load(mMovement.getImageUrl()).into(mImage);
        mTextTargetDonation.setText(StringFormatter.convertCurrency(mMovement.getTargetDonation()));
        mTextDistance.setText(StringFormatter.convertDistance(mMovement.getTargetDistance()));
        mTextCurrentDonation.setText(StringFormatter.convertCurrency(mMovement.getCurrentDonation()));
        mTextTitle.setText(mMovement.getName());
        mTextDetail.setText(mMovement.getDetail());

        int progress = (int) ((float) mMovement.getCurrentDistance() / mMovement.getTargetDistance() * 100);
        mProgressBar.setProgress(progress);
        String progressText = String.valueOf(progress) + "% achieved";
        mTextProgress.setText(progressText);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked!!!", Toast.LENGTH_SHORT).show();
            }
        });
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Parcelable parcelable);
    }
}
