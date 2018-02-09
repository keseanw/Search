package kesean.com.search.data.repository.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import kesean.com.search.data.api.SearchResponse;
import kesean.com.search.data.api.SearchService;
import kesean.com.search.data.model.Datum;
import kesean.com.search.data.model.Search;
import kesean.com.search.data.repository.SearchDataSource;

/**
 * Created by Kesean on 2/5/18.
 */

public class SearchRemoteDataSource implements SearchDataSource {
    private SearchService searchService;

    @Inject
    public SearchRemoteDataSource(SearchService searchService) {
        this.searchService = searchService;
    }

    /*
    * API call for intial data
    * */
    @Override
    public Flowable<List<Datum>> loadSearch() {
        return searchService.loadSearch().map(SearchResponse::getSearch);
    }

    /*
    * Not in use for API calls
    * */
    @Override
    public void addSearch(Datum data) {
        //Currently, we do not need this for remote source.
        throw new UnsupportedOperationException("Unsupported operation");
    }

    /*
    * Not in use for API calls
    * */
    @Override
    public int likeUser(Datum user){
        return 0;
    }

    /*
    * Not in use for API calls
    * */
    @Override
    public void clearData() {

    }

    /*
    * Not in use for API calls
    * */
    @Override
    public Flowable<List<Datum>> getMatches() {
        return null;
    }

}
