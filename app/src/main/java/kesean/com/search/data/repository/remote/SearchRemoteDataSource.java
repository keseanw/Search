package kesean.com.search.data.repository.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import kesean.com.search.data.api.SearchResponse;
import kesean.com.search.data.api.SearchService;
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

    @Override
    public Flowable<List<Search>> loadSearch(boolean forceRemote) {
        return searchService.loadSearch().map(SearchResponse::getSearch);
        //return searchService.loadSearch(Config.ANDROID_QUESTION_TAG).map(QuestionResponse::getQuestions);
    }

    @Override
    public void likeUser(Search search) {

    }

    @Override
    public void clearData() {

    }

}
