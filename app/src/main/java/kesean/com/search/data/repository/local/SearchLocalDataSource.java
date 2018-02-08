package kesean.com.search.data.repository.local;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import kesean.com.search.data.database.SearchDao;
import kesean.com.search.data.model.Datum;
import kesean.com.search.data.model.Search;
import kesean.com.search.data.repository.SearchDataSource;

/**
 * Created by Kesean on 2/5/18.
 */

//Accesses the DAO layer
public class SearchLocalDataSource implements SearchDataSource{

    private SearchDao searchDao;

    @Inject
    public SearchLocalDataSource(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    @Override
    public Flowable<List<Datum>> loadSearch(boolean forceRemote) {
        return searchDao.getAllSearch();
    }

    @Override
    public void addSearch(Datum data) {
        searchDao.insert(data);
    }

//    @Override
//    public Flowable<Datum> likeUser(String id, boolean likeVal) {
//        return searchDao.update(id, likeVal);
//    }

    @Override
    public int likeUser(Datum user) {
         return searchDao.update(user);
    }


    @Override
    public void clearData() {

    }
}
