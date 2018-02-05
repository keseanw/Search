
package kesean.com.search.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

public class CropRect {

    @SerializedName("height")
    private Long mHeight;
    @SerializedName("width")
    private Long mWidth;
    @SerializedName("x")
    private Long mX;
    @SerializedName("y")
    private Long mY;

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

    public Long getX() {
        return mX;
    }

    public void setX(Long x) {
        mX = x;
    }

    public Long getY() {
        return mY;
    }

    public void setY(Long y) {
        mY = y;
    }

}
