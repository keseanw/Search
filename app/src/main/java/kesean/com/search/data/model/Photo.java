
package kesean.com.search.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Entity(foreignKeys = @ForeignKey(
//        entity = Datum.class,
//        parentColumns = "name",
//        childColumns = "parentId"))
public class Photo {

    @SerializedName("base_path")
    private String mBasePath;
    @SerializedName("caption")
    private String mCaption;
    @SerializedName("crop_rect")
    @Ignore
    private CropRect mCropRect;
    @SerializedName("full_paths")
    @Embedded(prefix = "full_paths_")
    private FullPaths mFullPaths;
    @SerializedName("id")
    private String mId;
    @SerializedName("ordinal")
    @Ignore
    private Long mOrdinal;
    @SerializedName("original_size")
    @Ignore
    private OriginalSize mOriginalSize;
    @SerializedName("thumb_paths")
    @Ignore
    private ThumbPaths mThumbPaths;

    //private String parentId;

//    public String getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(String parentId) {
//        this.parentId = parentId;
//    }

    public String getBasePath() {
        return mBasePath;
    }

    public void setBasePath(String basePath) {
        mBasePath = basePath;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public CropRect getCropRect() {
        return mCropRect;
    }

    public void setCropRect(CropRect cropRect) {
        mCropRect = cropRect;
    }

    public FullPaths getFullPaths() {
        return mFullPaths;
    }

    public void setFullPaths(FullPaths fullPaths) {
        mFullPaths = fullPaths;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Long getOrdinal() {
        return mOrdinal;
    }

    public void setOrdinal(Long ordinal) {
        mOrdinal = ordinal;
    }

    public OriginalSize getOriginalSize() {
        return mOriginalSize;
    }

    public void setOriginalSize(OriginalSize originalSize) {
        mOriginalSize = originalSize;
    }

    public ThumbPaths getThumbPaths() {
        return mThumbPaths;
    }

    public void setThumbPaths(ThumbPaths thumbPaths) {
        mThumbPaths = thumbPaths;
    }

}
