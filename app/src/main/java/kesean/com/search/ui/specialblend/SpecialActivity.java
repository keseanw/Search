package kesean.com.search.ui.specialblend;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import kesean.com.search.R;
import kesean.com.search.data.model.Datum;
import kesean.com.search.ui.base.BaseActivity;
import kesean.com.search.ui.match.MatchFragment;


/**
 * Created by Kesean on 2/5/18.
 */

public class SpecialActivity extends BaseActivity implements SpecialContract.View, SpecialFragment.OnFragmentInteractionListener, MatchFragment.OnFragmentInteractionListener {

//    @BindView(R.id.recycler_special)
//    RecyclerView specialRecyclerView;
//    @BindView(R.id.refresh)
//    SwipeRefreshLayout refreshLayout;
//    @BindView(R.id.text_notification)
//    TextView notificationText;

    //Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdapter fragmentAdapter;

    private SpecialAdapter adapter;

    @Inject
    SpecialPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //ButterKnife.bind(this);
        initializePresenter();
        setUpViewPager();

//        setUpRecyclerView();

    }

    private void setUpViewPager(){

        //adapter setup
        adapter = new SpecialAdapter(new ArrayList<>());
        getSupportActionBar().setElevation(0);
        //toolbar = findViewById(R.id.toolbar1);
        tabLayout = findViewById(R.id.tab_layout1);
        viewPager = findViewById(R.id.pager1);

        //setSupportActionBar(toolbar);

        tabLayout.addTab(tabLayout.newTab().setText("SPECIAL BLEND"));
        tabLayout.addTab(tabLayout.newTab().setText("MATCH %"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(fragmentAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab LayoutTab) {

                viewPager.setCurrentItem(LayoutTab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {

            }
        });
    }

    private void initializePresenter() {
        DaggerSpecialComponent.builder()
                .specialPresenterModule(new SpecialPresenterModule(this))
                .searchRepositoryComponent(getSearchRepositoryComponent())
                .build()
                .inject(this);
    }

//    private void setUpRecyclerView() {
//        // Setup recycler view
//        adapter = new SpecialAdapter(new ArrayList<>());
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
//        specialRecyclerView.setLayoutManager(layoutManager);
//        specialRecyclerView.setAdapter(adapter);
//        specialRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        //click function
////        adapter.setOnItemClickListener(
////                (view, position) -> presenter.getQuestion(adapter.getItem(position).getId()));
//
//        // Refresh layout
//        refreshLayout.setOnRefreshListener(() -> presenter.loadSpecial(true));
//        // Set notification text visible first
//        notificationText.setVisibility(View.GONE);
//    }

    @Override
    public void showSpecial(List<Datum> search) {
//        notificationText.setVisibility(View.GONE);
        adapter.replaceData(search);
    }

    @Override
    public void clearSpecial() {
        adapter.clearData();
    }

    @Override
    public void showNoDataMessage() {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showHighlight(Datum user, int position) {

    }

    @Override
    public void stopLoadingIndicator() {
//       SpecialFragment specialFragment = new SpecialFragment();
//       specialFragment.isRefreshing();
    }

    @Override
    public void showEmptySearchResult() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
