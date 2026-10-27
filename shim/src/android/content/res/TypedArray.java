package android.content.res;

import android.graphics.drawable.Drawable;
import android.util.TypedValue;

/** TypedArray stub. */
public class TypedArray {
    public int length() { return 0; }
    public int getIndexCount() { return 0; }
    public int getIndex(int at) { return 0; }
    public int getResourceId(int index, int defValue) { return defValue; }
    public CharSequence getText(int index) { return null; }
    public String getString(int index) { return null; }
    public boolean getBoolean(int index, boolean defValue) { return defValue; }
    public int getInt(int index, int defValue) { return defValue; }
    public float getFloat(int index, float defValue) { return defValue; }
    public int getColor(int index, int defValue) { return defValue; }
    public Drawable getDrawable(int index) { return null; }
    public float getDimension(int index, float defValue) { return defValue; }
    public int getDimensionPixelSize(int index, int defValue) { return defValue; }
    public int getDimensionPixelOffset(int index, int defValue) { return defValue; }
    public int getLayoutDimension(int index, int defValue) { return defValue; }
    public int getLayoutDimension(int index, String name) { return 0; }
    public float getFraction(int index, int base, int pbase, float defValue) { return defValue; }
    public int getInteger(int index, int defValue) { return defValue; }
    public CharSequence[] getTextArray(int index) { return null; }
    public boolean hasValue(int index) { return false; }
    public boolean hasValueOrEmpty(int index) { return false; }
    public TypedValue peekValue(int index) { return null; }
    public void recycle() {}
    public void getValue(int index, TypedValue outValue) {}
    public int getChangingConfigurations() { return 0; }
    public int getType(int index) { return 0; }
    public int getSourceResourceId(int index, int defValue) { return defValue; }
    public boolean getBoolean(int index, boolean defValue, boolean resolveRefs) { return defValue; }
}
