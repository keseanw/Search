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

    @Override
    public Flowable<List<Datum>> loadSearch(boolean forceRemote) {
//        if (forceRemote) {
//            return refreshData();
//        } else {
//            if (caches.size() > 0) {
//                // if cache is available, return it immediately
//                return Flowable.just(caches);
//            } else {
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
//            }
//        }
    }

    @Override
    public void addSearch(Datum data) {
        //dont need this
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public int likeUser(Datum user) {
        return localDataSource.likeUser(user);
                //.filter(user -> user.getUserid().equals(id));
//                .doOnNext(result -> {
//
//                    caches.add(result);
//                }).
    }

    private Flowable<List<Datum>> refreshData() {
        return remoteDataSource.loadSearch(true)
                .flatMap(Flowable::fromIterable)
                .doOnNext(search -> {
                    caches.add(search);
                    //revisit
                    localDataSource.addSearch(search);
        }).toList().toFlowable();
    }

//    public Flowable<Datum> likeUser(String userId) {
//        return Flowable.fromIterable(caches).filter(search -> search.getUserid().equals(userId));
//    }

    @Override
    public void clearData() {
        caches.clear();
        localDataSource.clearData();
    }
}
