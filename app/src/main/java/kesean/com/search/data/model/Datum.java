
package kesean.com.search.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;


@Entity(foreignKeys = @ForeignKey(
        entity = Search.class,
        parentColumns = "name",
        childColumns = "parentId"))
public class Datum {

    @SerializedName("age")
    private Long mAge;
    @SerializedName("city_name")
    private String mCityName;
    @SerializedName("country_code")
    private String mCountryCode;
    @SerializedName("country_name")
    private String mCountryName;
    @SerializedName("enemy")
    private Long mEnemy;
    @SerializedName("friend")
    private Long mFriend;
    @SerializedName("gender")
    private Long mGender;
    @SerializedName("gender_tags")
    private List<Object> mGenderTags;
    @SerializedName("is_online")
    private Long mIsOnline;
    @SerializedName("last_contact_time")
    private List<Long> mLastContactTime;
    @SerializedName("last_login")
    private Long mLastLogin;
    @SerializedName("liked")
    private Boolean mLiked;
    @SerializedName("location")
    private Location mLocation;
    @SerializedName("match")
    private Long mMatch;
    @SerializedName("orientation")
    private Long mOrientation;
    @SerializedName("orientation_tags")
    private List<Object> mOrientationTags;
    @SerializedName("photo")
    private Photo mPhoto;
    @SerializedName("relative")
    private Long mRelative;
    @SerializedName("state_code")
    private String mStateCode;
    @SerializedName("state_name")
    private String mStateName;
    @SerializedName("stoplight_color")
    private String mStoplightColor;
    @SerializedName("userid")
    private String mUserid;
    @SerializedName("username")
    private String mUsername;

    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getAge() {
        return mAge;
    }

    public void setAge(Long age) {
        mAge = age;
    }

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

    public Long getEnemy() {
        return mEnemy;
    }

    public void setEnemy(Long enemy) {
        mEnemy = enemy;
    }

    public Long getFriend() {
        return mFriend;
    }

    public void setFriend(Long friend) {
        mFriend = friend;
    }

    public Long getGender() {
        return mGender;
    }

    public void setGender(Long gender) {
        mGender = gender;
    }

    public List<Object> getGenderTags() {
        return mGenderTags;
    }

    public void setGenderTags(List<Object> genderTags) {
        mGenderTags = genderTags;
    }

    public Long getIsOnline() {
        return mIsOnline;
    }

    public void setIsOnline(Long isOnline) {
        mIsOnline = isOnline;
    }

    public List<Long> getLastContactTime() {
        return mLastContactTime;
    }

    public void setLastContactTime(List<Long> lastContactTime) {
        mLastContactTime = lastContactTime;
    }

    public Long getLastLogin() {
        return mLastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        mLastLogin = lastLogin;
    }

    public Boolean getLiked() {
        return mLiked;
    }

    public void setLiked(Boolean liked) {
        mLiked = liked;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }

    public Long getMatch() {
        return mMatch;
    }

    public void setMatch(Long match) {
        mMatch = match;
    }

    public Long getOrientation() {
        return mOrientation;
    }

    public void setOrientation(Long orientation) {
        mOrientation = orientation;
    }

    public List<Object> getOrientationTags() {
        return mOrientationTags;
    }

    public void setOrientationTags(List<Object> orientationTags) {
        mOrientationTags = orientationTags;
    }

    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo photo) {
        mPhoto = photo;
    }

    public Long getRelative() {
        return mRelative;
    }

    public void setRelative(Long relative) {
        mRelative = relative;
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

    public String getStoplightColor() {
        return mStoplightColor;
    }

    public void setStoplightColor(String stoplightColor) {
        mStoplightColor = stoplightColor;
    }

    public String getUserid() {
        return mUserid;
    }

    public void setUserid(String userid) {
        mUserid = userid;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
