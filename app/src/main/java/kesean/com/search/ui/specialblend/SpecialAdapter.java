package kesean.com.search.ui.specialblend;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.security.InvalidParameterException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import kesean.com.search.R;
import kesean.com.search.data.model.Datum;
import kesean.com.search.data.model.Search;
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

    public SpecialAdapter(@NonNull List<Datum> special) {
        this.searchList = special;
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
        if(special_item.getLiked()){
            vh.cardView.setCardBackgroundColor(Color.parseColor("#FFFD38"));
        }else{
            vh.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        vh.username.setText(special_item.getUsername());
        vh.age.setText(String.valueOf(special_item.getAge()));
        vh.locationCity.setText(special_item.getCityName());
        vh.locationState.setText(special_item.getStateCode());
        vh.match.setText(String.valueOf(special_item.getMatch()));
        Glide.with(vh.profileImage).load(special_item.getPhoto().getFullPaths().getOriginal()).into(vh.profileImage);
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public void replaceData(List<Datum> special) {
        this.searchList.clear();
        this.searchList.addAll(special);
        notifyDataSetChanged();
    }

    public void updateList(Datum user, int position) {

        //this.searchList.remove(position);
        this.searchList.set(position, user);
        //this.searchList.add(position, user);
        notifyItemChanged(position);
    }

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
