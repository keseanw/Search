
package kesean.com.search.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.google.gson.annotations.SerializedName;

//@Entity(foreignKeys = @ForeignKey(
//        entity = Search.class,
//        parentColumns = "name",
//        childColumns = "parentId"))
public class Paging {

    @SerializedName("cursors")
    private Cursors mCursors;

//    private String parentId;
//
//    public String getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(String parentId) {
//        this.parentId = parentId;
//    }

    public Cursors getCursors() {
        return mCursors;
    }

    public void setCursors(Cursors cursors) {
        mCursors = cursors;
    }

}
