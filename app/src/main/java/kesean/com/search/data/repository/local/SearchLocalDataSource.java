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

    /*
    * RX Flowable call to DAO to get all data from db
    * */
    @Override
    public Flowable<List<Datum>> loadSearch() {
        return searchDao.getAllSearch();
    }

    /*
    * Method call to insert data in db
    * */
    @Override
    public void addSearch(Datum data) {
        searchDao.insert(data);
    }

    /*
    * Method call to insert new user obj into db to update liked user boolean
    * */
    @Override
    public int likeUser(Datum user) {
         return searchDao.update(user);
    }


    /*
    * Not in use
    * */
    @Override
    public void clearData() {

    }

    /*
    * RX Flowable call to get all match results from db
    * */
    @Override
    public Flowable<List<Datum>> getMatches() {
        return searchDao.getMatches();
    }
}
