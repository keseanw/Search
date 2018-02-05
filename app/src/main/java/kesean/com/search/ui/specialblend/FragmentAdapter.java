package kesean.com.search.ui.specialblend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import kesean.com.search.ui.match.MatchFragment;

/**
 * Created by Kesean on 2/5/18.
 */


public class FragmentAdapter extends FragmentStatePagerAdapter {

        int TabCount;

        public FragmentAdapter(FragmentManager fragmentManager, int CountTabs) {

            super(fragmentManager);

            this.TabCount = CountTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    SpecialFragment tab1 = new SpecialFragment();
                    return tab1;

                case 1:
                    MatchFragment tab2 = new MatchFragment();
                    return tab2;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return TabCount;
        }
}
