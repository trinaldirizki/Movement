package movement.com.movement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import movement.com.movement.R;
import movement.com.movement.model.News;

/**
 * Created by isma-ilou on 11.06.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{

    private Context mContext;
    private List<News> mNewsList = new ArrayList<>();

    public NewsAdapter(Context context, List<News> newsList) {
        mContext = context;
        mNewsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_news, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextNews.setText(mNewsList.get(position).getTitle());
        holder.mTextDate.setText(mNewsList.get(position).getDate());
        Glide.with(mContext).load(mNewsList.get(position).getImageUrl()).into(holder.mImageNews);
        holder.mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageNews;
        TextView mTextNews;
        TextView mTextDate;
        Button mButtonShare;

        MyViewHolder(final View itemView) {
            super(itemView);

            mImageNews = itemView.findViewById(R.id.image_news);
            mTextNews = itemView.findViewById(R.id.text_news);
            mTextDate = itemView.findViewById(R.id.text_news_date);
            mButtonShare = itemView.findViewById(R.id.button_share_news);
        }
    }
}
