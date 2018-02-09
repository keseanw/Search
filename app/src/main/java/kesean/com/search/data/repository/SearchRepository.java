package kesean.com.search.data.repository;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import kesean.com.search.data.model.Datum;
import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

public class SearchRepository implements SearchDataSource{

    private SearchDataSource remoteDataSource;
    private SearchDataSource localDataSource;
    List<Datum> caches;

    @Inject
    public SearchRepository(@Local SearchDataSource localDataSource,
                              @Remote SearchDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;

        caches = new ArrayList<>();
    }

    /*
    * RX Flowable to emit data from data source for special blend tab
    * */
    @Override
    public Flowable<List<Datum>> loadSearch() {

                return localDataSource.loadSearch()
                        .take(1)
                        .flatMap(Flowable::fromIterable)
                        .doOnNext(search -> caches.add(search))
                        .toList()
                        .toFlowable()
                        .filter(list -> !list.isEmpty())
                        .switchIfEmpty(
                                refreshData()); // If local data is empty, fetch from remote source instead.
    }

    /*
    * Not in use
    * */
    @Override
    public void addSearch(Datum data) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    /*
    * Method to update user that has been liked
    * */
    @Override
    public int likeUser(Datum user) {
        return localDataSource.likeUser(user);
    }

    /*
    * RX Flowable to emit data from data source to refresh data
    * */
    private Flowable<List<Datum>> refreshData() {
        return remoteDataSource.loadSearch()
                .flatMap(Flowable::fromIterable)
                .doOnNext(search -> {
                    caches.add(search);
                    //revisit
                    localDataSource.addSearch(search);
        }).toList().toFlowable();
    }

    /*
    * Clear data from data source
    * */
    @Override
    public void clearData() {
        caches.clear();
        localDataSource.clearData();
    }

    /*
    * RX Flowable to emit data from data source for matches tab
    * */
    @Override
    public Flowable<List<Datum>> getMatches() {
        return localDataSource.getMatches();
    }
}
