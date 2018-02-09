package kesean.com.search.ui.match;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kesean.com.search.R;
import kesean.com.search.data.model.Datum;
import kesean.com.search.ui.specialblend.DaggerSpecialComponent;
import kesean.com.search.ui.specialblend.SpecialActivity;
import kesean.com.search.ui.specialblend.SpecialContract;
import kesean.com.search.ui.specialblend.SpecialPresenterModule;


public class MatchFragment extends Fragment implements SpecialContract.View{

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.recycler_special)
    RecyclerView matchRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.text_notification)
    TextView notificationText;

    private MatchAdapter adapter;

    @Inject
    MatchPresenter presenter;

    public MatchFragment() {
        // Required empty public constructor
    }

    public static MatchFragment newInstance() {
        MatchFragment fragment = new MatchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special, container, false);
        ButterKnife.bind(this, view);
        setUpRecyclerView();
        initializePresenter();
        return view;
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

    /*
    * Initialize presenter module and inject into the fragment
    * */
    private void initializePresenter() {
        DaggerSpecialComponent.builder()
                .specialPresenterModule(new SpecialPresenterModule(this))
                .searchRepositoryComponent(((SpecialActivity)getActivity()).getSearchRepositoryComponent())
                .build()
                .inject(this);
    }


    void setUpRecyclerView(){
        // Setup recycler view
        adapter = new MatchAdapter(new ArrayList<>(), getActivity());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        matchRecyclerView.setLayoutManager(layoutManager);
        matchRecyclerView.setAdapter(adapter);
        matchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //click function -- not in use
//        adapter.setOnItemClickListener(
//                (view, position) -> presenter.loadMatches());

        // Refresh layout
        refreshLayout.setOnRefreshListener(() -> presenter.loadMatches());
        notificationText.setVisibility(View.GONE);
    }

    /*
    * Pass search list to adapter in order to update recycler view list
    * */
    @Override
    public void showSpecial(List<Datum> search) {
        notificationText.setVisibility(View.GONE);
        adapter.replaceData(search);
    }

    /*
    * Clear entire list data in recycler view
    * */
    @Override
    public void clearSpecial() {
        adapter.clearData();
    }

    @Override
    public void showNoDataMessage() {
        showNotification(getString(R.string.msg_no_data));
    }

    @Override
    public void showErrorMessage(String error) {
        showNotification(error);
    }

    @Override
    public void showHighlight(Datum likedUser, int position) {
        //not in use
    }

    @Override
    public void stopLoadingIndicator() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showEmptySearchResult() {
        showNotification(getString(R.string.msg_empty_search_result));
    }

    /*
   * Method for displaying error message in view
   * */
    private void showNotification(String message) {
        notificationText.setVisibility(View.VISIBLE);
        notificationText.setText(message);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
