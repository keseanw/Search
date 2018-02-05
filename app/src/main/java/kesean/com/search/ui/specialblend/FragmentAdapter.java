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
                    return new SpecialFragment();

                case 1:
                    return new MatchFragment();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return TabCount;
        }
}
