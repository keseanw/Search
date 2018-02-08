package kesean.com.search.data.api;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kesean on 2/5/18.
 */

public interface SearchService {

    @GET("matchSample.json")
    Flowable<SearchResponse> loadSearch();

}
