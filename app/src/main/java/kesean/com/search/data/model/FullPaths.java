
package kesean.com.search.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;


public class FullPaths {

    @SerializedName("large")
    private String mLarge;
    @SerializedName("medium")
    private String mMedium;
    @SerializedName("original")
    private String mOriginal;
    @SerializedName("small")
    private String mSmall;

    public String getLarge() {
        return mLarge;
    }

    public void setLarge(String large) {
        mLarge = large;
    }

    public String getMedium() {
        return mMedium;
    }

    public void setMedium(String medium) {
        mMedium = medium;
    }

    public String getOriginal() {
        return mOriginal;
    }

    public void setOriginal(String original) {
        mOriginal = original;
    }

    public String getSmall() {
        return mSmall;
    }

    public void setSmall(String small) {
        mSmall = small;
    }

}
