package movement.com.movement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import movement.com.movement.model.Charity;
import movement.com.movement.viewholder.CharityViewHolder;

public class CharityListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    DatabaseReference mCharityRef;
    RecyclerView mRecyclerCharity;

    public CharityListFragment() {
        // Required empty public constructor
    }

    public static CharityListFragment newInstance(String param1, String param2) {
        CharityListFragment fragment = new CharityListFragment();
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

        mCharityRef = FirebaseDatabase.getInstance().getReference().child("charities");
        mCharityRef.keepSynced(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charity_list, container, false);
        mRecyclerCharity = view.findViewById(R.id.recycler_charity);
        mRecyclerCharity.setHasFixedSize(true);
        mRecyclerCharity.setLayoutManager(new GridLayoutManager(getContext(), 3));
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        initCharityRef();
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
        void onFragmentInteraction(Uri uri);
    }

    private void initCharityRef(){
        FirebaseRecyclerOptions<Charity> options = new FirebaseRecyclerOptions.Builder<Charity>()
                .setQuery(mCharityRef, Charity.class)
                .build();

        FirebaseRecyclerAdapter<Charity, CharityViewHolder> charityAdapter = new FirebaseRecyclerAdapter<Charity, CharityViewHolder>(options) {
            @Override
            protected void onBindViewHolder(CharityViewHolder holder, int position, final Charity model) {
                holder.setImageCharity(model.getImageUrl());
                holder.setOnSelectedCharity(model.getMovement());
            }

            @Override
            public CharityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.cardview_item_charity, parent, false);
                return new CharityViewHolder(view);
            }
        };

        charityAdapter.startListening();
        mRecyclerCharity.setAdapter(charityAdapter);
    }
}
