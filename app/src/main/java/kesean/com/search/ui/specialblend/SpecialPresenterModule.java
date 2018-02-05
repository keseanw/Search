package kesean.com.search.ui.specialblend;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kesean on 2/5/18.
 */

@Module
public class SpecialPresenterModule {

    private SpecialContract.View view;

    public SpecialPresenterModule(SpecialContract.View view) {
        this.view = view;
    }

    @Provides
    public SpecialContract.View provideView() {
        return view;
    }
}
