package kesean.com.search.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import kesean.com.search.data.model.Datum;
import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

public interface SearchDataSource {

    Flowable<List<Datum>> loadSearch();

    void addSearch(Datum data);

    int likeUser(Datum user);

    void clearData();

    Flowable<List<Datum>> getMatches();
}
