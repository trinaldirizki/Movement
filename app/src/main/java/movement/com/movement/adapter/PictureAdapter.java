package movement.com.movement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import movement.com.movement.R;

/**
 * Created by isma-ilou on 11.06.2018.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHolder>{

    private static final String TAG = PictureAdapter.class.getSimpleName();

    private Context mContext;
    private List<String> mImageList = new ArrayList<>();

    public PictureAdapter(Context context, List<String> imageList) {
        mContext = context;
        mImageList = imageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_picture, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(mContext).load(mImageList.get(position)).into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mImageList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageCircle);
        }

    }
}
