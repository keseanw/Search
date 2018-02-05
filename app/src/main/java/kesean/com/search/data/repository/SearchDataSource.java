package kesean.com.search.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

public interface SearchDataSource {

    Flowable<List<Search>> loadSearch(boolean forceRemote);

    //must change - shouldnt work
    void likeUser(Search search);

    void clearData();
}
