package kesean.com.search.ui.specialblend;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;

import java.util.List;

import kesean.com.search.R;
import kesean.com.search.data.model.Search;
import kesean.com.search.ui.base.BaseActivity;
import kesean.com.search.ui.match.MatchFragment;

/**
 * Created by Kesean on 2/5/18.
 */

public class SpecialActivity extends BaseActivity implements SpecialContract.View, SpecialFragment.OnFragmentInteractionListener, MatchFragment.OnFragmentInteractionListener {

    //Toolbar toolbar;
    TabLayout tabLayout ;
    ViewPager viewPager ;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

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

    @Override
    public void showSpecial(List<Search> search) {

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
    public void stopLoadingIndicator() {

    }

    @Override
    public void showEmptySearchResult() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
