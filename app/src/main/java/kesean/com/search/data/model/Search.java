
package kesean.com.search.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

import kesean.com.search.data.Config;

@Entity(tableName = Config.SEARCH_TABLE_NAME)
public class Search {

    @SerializedName("search_id")
    @PrimaryKey
    private long id;

    @SerializedName("data")
    @Ignore
    //@Embedded(prefix = "data")
    private List<Datum> mData;
    @SerializedName("paging")
    //@Embedded(prefix = "paging")
    @Ignore
    private Paging mPaging;
    @SerializedName("total_matches")
    //@Embedded(prefix = "total_matches")
    @Ignore
    private Long mTotalMatches;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public Paging getPaging() {
        return mPaging;
    }

    public void setPaging(Paging paging) {
        mPaging = paging;
    }

    public Long getTotalMatches() {
        return mTotalMatches;
    }

    public void setTotalMatches(Long totalMatches) {
        mTotalMatches = totalMatches;
    }

}
