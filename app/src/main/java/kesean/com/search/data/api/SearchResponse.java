package kesean.com.search.data.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kesean.com.search.data.model.Datum;
import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

public class SearchResponse {

    @SerializedName("data")
    private List<Datum> search;

    public List<Datum> getSearch() {
        return search;
    }

    public void setSearch(List<Datum> search) {
        this.search = search;
    }
}
