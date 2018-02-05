
package kesean.com.search.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;


public class Location {

    @SerializedName("city_name")
    private String mCityName;
    @SerializedName("country_code")
    private String mCountryCode;
    @SerializedName("country_name")
    private String mCountryName;
    @SerializedName("state_code")
    private String mStateCode;
    @SerializedName("state_name")
    private String mStateName;

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public String getCountryName() {
        return mCountryName;
    }

    public void setCountryName(String countryName) {
        mCountryName = countryName;
    }

    public String getStateCode() {
        return mStateCode;
    }

    public void setStateCode(String stateCode) {
        mStateCode = stateCode;
    }

    public String getStateName() {
        return mStateName;
    }

    public void setStateName(String stateName) {
        mStateName = stateName;
    }

}
