
package kesean.com.search.data.model;

import com.google.gson.annotations.SerializedName;


public class ThumbPaths {

    @SerializedName("desktop_match")
    private String mDesktopMatch;
    @SerializedName("large")
    private String mLarge;
    @SerializedName("medium")
    private String mMedium;
    @SerializedName("small")
    private String mSmall;

    public String getDesktopMatch() {
        return mDesktopMatch;
    }

    public void setDesktopMatch(String desktopMatch) {
        mDesktopMatch = desktopMatch;
    }

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

    public String getSmall() {
        return mSmall;
    }

    public void setSmall(String small) {
        mSmall = small;
    }

}
