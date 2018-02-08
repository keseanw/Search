package kesean.com.search.ui.specialblend;

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
import kesean.com.search.ui.base.BaseActivity;


public class SpecialFragment extends Fragment implements SpecialContract.View {

    @BindView(R.id.recycler_special)
    RecyclerView specialRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.text_notification)
    TextView notificationText;

    private SpecialAdapter adapter;

    private SpecialContract.Presenter mPresenter;

    @Inject
    SpecialPresenter presenter;


    private OnFragmentInteractionListener mListener;

    public SpecialFragment() {
        // Required empty public constructor
    }

    public static SpecialFragment newInstance(String param1, String param2) {
        SpecialFragment fragment = new SpecialFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void isRefreshing(){
//        if (refreshLayout.isRefreshing()) {
//            refreshLayout.setRefreshing(false);
//        }
    }

    private void setUpRecyclerView() {
        // Setup recycler view
        adapter = new SpecialAdapter(new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        specialRecyclerView.setLayoutManager(layoutManager);
        specialRecyclerView.setAdapter(adapter);
        specialRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //click function
        adapter.setOnItemClickListener(
                (view, position) -> presenter.likeUser(adapter.getItem(position), position));

        // Refresh layout
        refreshLayout.setOnRefreshListener(() -> presenter.loadSpecial(true));
        // Set notification text visible first
        notificationText.setVisibility(View.GONE);
    }

    private void initializePresenter() {
        DaggerSpecialComponent.builder()
                .specialPresenterModule(new SpecialPresenterModule(this))
                .searchRepositoryComponent(((SpecialActivity)getActivity()).getSearchRepositoryComponent())
                .build()
                .inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_special, container, false);
        ButterKnife.bind(this, view);
        setUpRecyclerView();
        initializePresenter();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            //presenter.onAttach();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        //presenter.onDetach();
    }

    @Override
    public void showSpecial(List<Datum> search) {
        notificationText.setVisibility(View.GONE);
        adapter.replaceData(search);
    }

    @Override
    public void clearSpecial() {

    }

    @Override
    public void showNoDataMessage() {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showHighlight(Datum user, int position) {
        adapter.updateList(user, position);
    }

    @Override
    public void stopLoadingIndicator() {

    }

    @Override
    public void showEmptySearchResult() {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
