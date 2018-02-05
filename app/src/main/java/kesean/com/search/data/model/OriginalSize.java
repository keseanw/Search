
package kesean.com.search.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;


public class OriginalSize {

    @SerializedName("height")
    private Long mHeight;
    @SerializedName("width")
    private Long mWidth;

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }

}
