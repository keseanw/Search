package kesean.com.search.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kesean.com.search.data.repository.Local;
import kesean.com.search.data.repository.Remote;
import kesean.com.search.data.repository.SearchDataSource;
import kesean.com.search.data.repository.local.SearchLocalDataSource;
import kesean.com.search.data.repository.remote.SearchRemoteDataSource;

/**
 * Created by Kesean on 2/4/18.
 */

@Module
public class SearchRepositoryModule {

    /*
    * Local data source for db querying
    * */

    @Provides
    @Local
    @Singleton
    public SearchDataSource provideLocalDataSource(SearchLocalDataSource searchLocalDataSource) {
        return searchLocalDataSource;
    }

    /*
    * Remote data source for API calls
    * */

    @Provides
    @Remote
    @Singleton
    public SearchDataSource provideRemoteDataSource(SearchRemoteDataSource searchRemoteDataSource) {
        return searchRemoteDataSource;
    }
}
