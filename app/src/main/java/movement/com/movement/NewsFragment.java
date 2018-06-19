package movement.com.movement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import movement.com.movement.model.News;
import movement.com.movement.viewholder.NewsViewHolder;

public class NewsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private RecyclerView mRecyclerNews;
    private DatabaseReference mNewsRef;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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

        mNewsRef = FirebaseDatabase.getInstance().getReference().child("news");
        mNewsRef.keepSynced(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mRecyclerNews = view.findViewById(R.id.recycler_news);
        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onNewsFragmentInteraction(uri);
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

        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
        if (compatActivity != null){
            ActionBar actionBar = compatActivity.getSupportActionBar();
            if (actionBar != null){
                actionBar.setTitle("News");
            }
        }

        initNewsRef();
    }

    private void initNewsRef() {
        FirebaseRecyclerOptions<News> options = new FirebaseRecyclerOptions.Builder<News>()
                .setQuery(mNewsRef, News.class)
                .build();

        FirebaseRecyclerAdapter<News, NewsViewHolder> newsAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>(options){
            @NonNull
            @Override
            public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.card_news, parent, false);
                return new NewsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull NewsViewHolder holder, int position, @NonNull News model) {
                holder.setTitle(model.getTitle());
                holder.setDate(model.getDate());
                holder.setImage(getContext(), model.getImageUrl());
            }
        };

        newsAdapter.startListening();
        mRecyclerNews.setAdapter(newsAdapter);
    }

    public interface OnFragmentInteractionListener {
        void onNewsFragmentInteraction(Uri uri);
    }
}
