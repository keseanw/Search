package kesean.com.search.ui.match;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kesean.com.search.R;
import kesean.com.search.data.model.Datum;
import kesean.com.search.ui.base.BaseRecyclerViewAdapter;

/**
 * Created by Kesean on 2/7/18.
 */

class MatchAdapter extends BaseRecyclerViewAdapter<MatchAdapter.MatchViewHolder> {
    public class MatchViewHolder extends RecyclerView.ViewHolder {
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

        public MatchViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public MatchAdapter(@NonNull ArrayList<Datum> matchList) {
        this.matchList = matchList;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_special, parent, false);
        return new MatchViewHolder(view);
    }

    private List<Datum> matchList;

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        MatchAdapter.MatchViewHolder vh = (MatchAdapter.MatchViewHolder) viewHolder;

        Datum special_item = matchList.get(i);

        vh.username.setText(special_item.getUsername());
        vh.age.setText(String.valueOf(special_item.getAge()));
        vh.locationCity.setText(special_item.getCityName());
        vh.locationState.setText(special_item.getStateCode());
        vh.match.setText(String.valueOf(special_item.getMatch()));
        Glide.with(vh.profileImage).load(special_item.getPhoto().getFullPaths().getOriginal()).into(vh.profileImage);
    }

    public void replaceData(List<Datum> special) {
        this.matchList.clear();
        this.matchList.addAll(special);
        notifyDataSetChanged();
    }

    public void updateList(Datum user, int position) {
        this.matchList.set(position, user);
        notifyItemChanged(position);
    }

    public Datum getItem(int position) {
        if (position < 0 || position >= matchList.size()) {
            throw new InvalidParameterException("Invalid item index");
        }
        return matchList.get(position);
    }

    public void clearData() {
        matchList.clear();
        notifyDataSetChanged();
    }


}
