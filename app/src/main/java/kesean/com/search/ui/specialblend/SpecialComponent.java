package kesean.com.search.ui.specialblend;

import dagger.Component;
import kesean.com.search.data.SearchRepositoryComponent;
import kesean.com.search.ui.base.ActivityScope;
import kesean.com.search.ui.match.MatchFragment;
import kesean.com.search.util.SchedulerModule;

/**
 * Created by Kesean on 2/5/18.
 */

@ActivityScope
@Component(modules = {SpecialPresenterModule.class, SchedulerModule.class}, dependencies = {
        SearchRepositoryComponent.class
})
public interface SpecialComponent {
    void inject(SpecialActivity specialActivity);

    void inject(SpecialFragment specialFragment);

    void inject(MatchFragment matchFragment);
}
