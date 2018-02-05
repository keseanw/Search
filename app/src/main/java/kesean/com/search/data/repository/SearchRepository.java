package kesean.com.search.data.repository;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

public class SearchRepository implements SearchDataSource{

    private SearchDataSource remoteDataSource;
    private SearchDataSource localDataSource;
    List<Search> caches;

    @Inject
    public SearchRepository(@Local SearchDataSource localDataSource,
                              @Remote SearchDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;

        caches = new ArrayList<>();
    }

    @Override
    public Flowable<List<Search>> loadSearch(boolean forceRemote) {
        if (forceRemote) {
            return refreshData();
        } else {
            if (caches.size() > 0) {
                // if cache is available, return it immediately
                return Flowable.just(caches);
            } else {
                // else return data from local storage
                return localDataSource.loadSearch(false)
                        .take(1)
                        .flatMap(Flowable::fromIterable)
                        .doOnNext(search -> caches.add(search))
                        .toList()
                        .toFlowable()
                        .filter(list -> !list.isEmpty())
                        .switchIfEmpty(
                                refreshData()); // If local data is empty, fetch from remote source instead.
            }
        }
    }

    private Flowable<List<Search>> refreshData() {
        return remoteDataSource.loadSearch(true).doOnNext(list -> {
            // Clear cache
            caches.clear();
            // Clear data in local storage
            localDataSource.clearData();
        }).flatMap(Flowable::fromIterable).doOnNext(search -> {
            caches.add(search);
            //revisit
            //localDataSource.(question);
        }).toList().toFlowable();
    }

    @Override
    public void likeUser(Search search) {

    }

    @Override
    public void clearData() {
        caches.clear();
        localDataSource.clearData();
    }
}
