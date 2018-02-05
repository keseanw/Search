
package kesean.com.search.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

public class Cursors {

    @SerializedName("after")
    private String mAfter;
    @SerializedName("before")
    private String mBefore;
    @SerializedName("current")
    private String mCurrent;

    public String getAfter() {
        return mAfter;
    }

    public void setAfter(String after) {
        mAfter = after;
    }

    public String getBefore() {
        return mBefore;
    }

    public void setBefore(String before) {
        mBefore = before;
    }

    public String getCurrent() {
        return mCurrent;
    }

    public void setCurrent(String current) {
        mCurrent = current;
    }

}
