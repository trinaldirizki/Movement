package movement.com.movement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import movement.com.movement.R;
import movement.com.movement.model.Charity;

/**
 * Created by mobiltrakya on 21/04/2018.
 */

public class CharityAdapter extends RecyclerView.Adapter<CharityAdapter.CharityViewHolder> {

    private Context mContext;
    private List<Charity> charities;

    public CharityAdapter(Context mContext, List<Charity> charities) {
        this.mContext = mContext;
        this.charities = charities;
    }

    @Override
    public CharityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cardview_item_charity, parent, false);
        return new CharityViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CharityViewHolder holder, int position) {
        // holder.textCharity.setText(charities.get(position).getName());
        holder.imageCharity.setImageResource(charities.get(position).getThumbnailId());
    }

    @Override
    public int getItemCount() {
        return charities.size();
    }

    public static class CharityViewHolder extends RecyclerView.ViewHolder {

        // TextView textCharity;
        ImageView imageCharity;

        public CharityViewHolder(View itemView) {
            super(itemView);

            // textCharity = itemView.findViewById(R.id.textCharity);
            imageCharity = itemView.findViewById(R.id.imageCharity);

        }
    }

}
