package kesean.com.search.data;

import javax.inject.Singleton;
import kesean.com.search.AppModule;
import dagger.Component;
import kesean.com.search.data.repository.SearchRepository;

/**
 * Created by Kesean on 2/4/18.
 */

@Singleton
@Component(modules = { SearchRepositoryModule.class, AppModule.class, ApiServiceModule.class,
        DatabaseModule.class})
public interface SearchRepositoryComponent {
    SearchRepository provideSearchRepository();
}
