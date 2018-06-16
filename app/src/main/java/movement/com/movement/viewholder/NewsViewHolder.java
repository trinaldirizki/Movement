package movement.com.movement.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import movement.com.movement.R;

/**
 * Created by isma-ilou on 14.06.2018.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    TextView mDate;
    ImageButton mShareNews;

    public NewsViewHolder(final View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.image_news);
        mTitle = itemView.findViewById(R.id.text_news);
        mDate = itemView.findViewById(R.id.text_news_date);
        mShareNews = itemView.findViewById(R.id.button_share_news);
        mShareNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), mTitle.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setImage(Context context, String uri) {
        Glide.with(context).load(uri).into(mImage);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setDate(String date) {
        mDate.setText(date);
    }

}
