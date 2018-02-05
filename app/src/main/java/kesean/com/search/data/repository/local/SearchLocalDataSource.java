package kesean.com.search.data.repository.local;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import kesean.com.search.data.database.SearchDao;
import kesean.com.search.data.model.Search;
import kesean.com.search.data.repository.SearchDataSource;

/**
 * Created by Kesean on 2/5/18.
 */

public class SearchLocalDataSource implements SearchDataSource{

    private SearchDao searchDao;

    @Inject
    public SearchLocalDataSource(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @Override
    public Flowable<List<Search>> loadSearch(boolean forceRemote) {
        return searchDao.getAllSearch();
    }

    @Override
    public void likeUser(Search search) {

    }

    @Override
    public void clearData() {

    }
}
