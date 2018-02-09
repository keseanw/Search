package kesean.com.search.ui.specialblend;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import kesean.com.search.R;
import kesean.com.search.ui.base.BaseActivity;
import kesean.com.search.ui.match.MatchFragment;


/**
 * Created by Kesean on 2/5/18.
 */

public class SpecialActivity extends BaseActivity implements SpecialFragment.OnFragmentInteractionListener, MatchFragment.OnFragmentInteractionListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setUpViewPager();
    }

    private void setUpViewPager(){

        getSupportActionBar().setElevation(0);
        tabLayout = findViewById(R.id.tab_layout1);
        viewPager = findViewById(R.id.pager1);

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
    public void onFragmentInteraction(Uri uri) {

    }
}
