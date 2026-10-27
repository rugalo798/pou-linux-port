package android.content.res;

import android.os.Parcel;
import android.os.Parcelable;

/** Simple ColorStateList. */
public class ColorStateList implements Parcelable {
    private final int color;

    public ColorStateList(int[][] states, int[] colors) {
        this.color = colors != null && colors.length > 0 ? colors[0] : 0;
    }
    private ColorStateList(int color) { this.color = color; }

    public static ColorStateList valueOf(int color) { return new ColorStateList(color); }
    public int getDefaultColor() { return color; }
    public int getColorForState(int[] stateSet, int defaultColor) { return color; }
    public boolean isStateful() { return false; }
    public boolean isOpaque() { return (color >>> 24) == 0xFF; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public ColorStateList withAlpha(int alpha) { return new ColorStateList((color & 0x00FFFFFF) | (alpha << 24)); }

    public static final Parcelable.Creator<ColorStateList> CREATOR = new Parcelable.Creator<ColorStateList>() {
        public ColorStateList createFromParcel(Parcel source) { return new ColorStateList(0); }
        public ColorStateList[] newArray(int size) { return new ColorStateList[size]; }
    };
}
