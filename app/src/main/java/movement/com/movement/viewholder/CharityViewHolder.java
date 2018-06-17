package movement.com.movement.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import movement.com.movement.OverviewActivity;
import movement.com.movement.R;
import movement.com.movement.model.Movement;

/**
 * Created by isma-ilou on 16.06.2018.
 */

public class CharityViewHolder extends RecyclerView.ViewHolder {

    Context mContext;
    public ImageView mImageCharity;

    public CharityViewHolder(View itemView) {
        super(itemView);

        mContext = itemView.getContext();
        mImageCharity = itemView.findViewById(R.id.imageCharity);
    }

    public void setImageCharity(String imageUrl) {
        Glide.with(mContext).load(imageUrl).into(mImageCharity);
    }

    public void setOnSelectedCharity(final Movement movement) {
        mImageCharity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OverviewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("parcel_movement", movement);
                mContext.startActivity(intent);
            }
        });
    }
}
