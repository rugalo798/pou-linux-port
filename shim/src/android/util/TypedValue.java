package android.util;

/** TypedValue. */
public class TypedValue {
    public static final int TYPE_NULL = 0;
    public static final int TYPE_REFERENCE = 1;
    public static final int TYPE_ATTRIBUTE = 2;
    public static final int TYPE_STRING = 3;
    public static final int TYPE_FLOAT = 4;
    public static final int TYPE_DIMENSION = 5;
    public static final int TYPE_FRACTION = 6;
    public static final int TYPE_DYNAMIC_REFERENCE = 7;
    public static final int TYPE_DYNAMIC_ATTRIBUTE = 8;
    public static final int TYPE_FIRST_INT = 0x10;
    public static final int TYPE_INT_DEC = 0x10;
    public static final int TYPE_INT_HEX = 0x11;
    public static final int TYPE_INT_BOOLEAN = 0x12;
    public static final int TYPE_FIRST_COLOR_INT = 0x1c;
    public static final int TYPE_INT_COLOR_ARGB8 = 0x1c;
    public static final int TYPE_INT_COLOR_RGB8 = 0x1d;
    public static final int TYPE_INT_COLOR_ARGB4 = 0x1e;
    public static final int TYPE_INT_COLOR_RGB4 = 0x1f;
    public static final int TYPE_LAST_COLOR_INT = 0x1f;
    public static final int TYPE_LAST_INT = 0x1f;
    public static final int COMPLEX_UNIT_PX = 0;
    public static final int COMPLEX_UNIT_DIP = 1;
    public static final int COMPLEX_UNIT_SP = 2;
    public static final int COMPLEX_UNIT_PT = 3;
    public static final int COMPLEX_UNIT_IN = 4;
    public static final int COMPLEX_UNIT_MM = 5;
    public static final int COMPLEX_UNIT_SHIFT = 0;
    public static final int COMPLEX_UNIT_MASK = 0xf;
    public static final int COMPLEX_UNIT_FRACTION = 0;
    public static final int COMPLEX_UNIT_FRACTION_PARENT = 1;
    public static final int DENSITY_DEFAULT = 0;
    public static final int DENSITY_NONE = 0xffff;
    public static final int DATA_NULL_UNDEFINED = 0;
    public static final int DATA_NULL_EMPTY = 1;

    public int type;
    public int data;
    public int assetCookie;
    public int changingConfigurations;
    public int density;
    public int resourceId;
    public CharSequence string;
    public float floatValue;

    public static float applyDimension(int unit, float value, DisplayMetrics metrics) {
        switch (unit) {
            case COMPLEX_UNIT_PX: return value;
            case COMPLEX_UNIT_DIP: return value * metrics.density;
            case COMPLEX_UNIT_SP: return value * metrics.scaledDensity;
            case COMPLEX_UNIT_PT: return value * metrics.xdpi * (1.0f / 72);
            case COMPLEX_UNIT_IN: return value * metrics.xdpi;
            case COMPLEX_UNIT_MM: return value * metrics.xdpi * (1.0f / 25.4f);
            default: return 0;
        }
    }
    public static float complexToFloat(int complex) { return (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3]; }
    public static float complexToDimension(int data, DisplayMetrics metrics) { return applyDimension((data >> COMPLEX_UNIT_SHIFT) & COMPLEX_UNIT_MASK, complexToFloat(data), metrics); }
    public static int complexToDimensionPixelSize(int data, DisplayMetrics metrics) { return (int) (complexToDimension(data, metrics) + 0.5f); }
    public static float complexToDimensionNoisy(int data, DisplayMetrics metrics) { return complexToDimension(data, metrics); }
    public static float complexToFraction(int data, float base, float pbase) {
        switch ((data >> COMPLEX_UNIT_SHIFT) & COMPLEX_UNIT_MASK) {
            case COMPLEX_UNIT_FRACTION: return complexToFloat(data) * base;
            case COMPLEX_UNIT_FRACTION_PARENT: return complexToFloat(data) * pbase;
            default: return 0;
        }
    }
    private static final float[] RADIX_MULTS = {0.00390625f, 3.051758E-5f, 1.192093E-7f, 4.656613E-10f};
    public static String coerceToString(int type, int data) { return String.valueOf(data); }
    public final CharSequence coerceToString() { return string != null ? string : String.valueOf(data); }
    public void setTo(TypedValue other) {
        type = other.type; data = other.data; assetCookie = other.assetCookie; changingConfigurations = other.changingConfigurations;
        density = other.density; resourceId = other.resourceId; string = other.string; floatValue = other.floatValue;
    }
    public String toString() { return "TypedValue{t=" + type + ", d=" + data + "}"; }
}
