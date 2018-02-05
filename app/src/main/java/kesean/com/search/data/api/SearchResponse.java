package kesean.com.search.data.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kesean.com.search.data.model.Search;

/**
 * Created by Kesean on 2/5/18.
 */

public class SearchResponse {

    private List<Search> search;

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }
}
