package kesean.com.search.ui.specialblend;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import kesean.com.search.R;
import kesean.com.search.data.model.Datum;
import kesean.com.search.ui.base.BaseRecyclerViewAdapter;

/**
 * Created by Kesean on 2/5/18.
 */

class SpecialAdapter extends BaseRecyclerViewAdapter<SpecialAdapter.SpecialViewHolder> {
    class SpecialViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.username)
        TextView username;
        @BindView(R.id.cardViewLayout)
        CardView cardView;
        @BindView(R.id.age)
        TextView age;
        @BindView(R.id.seperator)
        TextView seperator;
        @BindView(R.id.location_city)
        TextView locationCity;
        @BindView(R.id.location_state)
        TextView locationState;
        @BindView(R.id.match)
        TextView match;
        @BindView(R.id.image_profile)
        ImageView profileImage;

        public SpecialViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private List<Datum> searchList;
    private Context mContext;

    public SpecialAdapter(@NonNull ArrayList<Datum> searchList, Context context) {
        this.searchList =searchList;
        this.mContext = context;
    }

    @Override
    public SpecialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_special, parent, false);
        return new SpecialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        SpecialViewHolder vh = (SpecialViewHolder) viewHolder;

        Datum special_item = searchList.get(i);
        //based on liked boolean value, user card view is highlighted yellow or not
        if(special_item.getLiked()){
            vh.cardView.setCardBackgroundColor(Color.parseColor("#FFFF97"));
        }else{
            vh.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        vh.username.setText(special_item.getUsername());
        vh.age.setText(String.valueOf(special_item.getAge()));
        String formattedCityName =  special_item.getCityName() + ",";
        vh.locationCity.setText(formattedCityName);
        vh.locationState.setText(special_item.getStateCode());
        vh.match.setText(matchConversion(special_item.getMatch()));
        Glide.with(vh.profileImage).load(special_item.getPhoto().getFullPaths().getOriginal()).into(vh.profileImage);
    }

    /*
    * Rounding and converting the match value into a percentage string
    * */
    private String matchConversion(long matchOriginal){

        int x = 2; // 2 decimal points
        BigDecimal unscaled = new BigDecimal(matchOriginal);
        BigDecimal scaled = unscaled.scaleByPowerOfTen(-x);
        scaled = scaled.setScale(0, RoundingMode.HALF_EVEN);
        String matchPercentage = String.valueOf(scaled) + "% Match";
        return matchPercentage;
    }


    @Override
    public int getItemCount() {
        return searchList.size();
    }

    /*
    * Replaces entire recycler view list with new data
    * */
    public void replaceData(List<Datum> special) {
        this.searchList.clear();
        this.searchList.addAll(special);
        notifyDataSetChanged();
    }

    /*
    * Updates a single element in the recycler view list when a user likes another users account
    * */
    public void updateList(Datum user, int position) {
        this.searchList.set(position, user);
        notifyItemChanged(position);
    }

    /*
    * Based on position, gets specific element from list
    * */
    public Datum getItem(int position) {
        if (position < 0 || position >= searchList.size()) {
            throw new InvalidParameterException("Invalid item index");
        }
        return searchList.get(position);
    }

    public void clearData() {
        searchList.clear();
        notifyDataSetChanged();
    }

}
