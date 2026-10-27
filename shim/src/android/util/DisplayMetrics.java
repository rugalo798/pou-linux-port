package android.util;

/** Display metrics for the desktop window. */
public class DisplayMetrics {
    public static final int DENSITY_LOW = 120;
    public static final int DENSITY_MEDIUM = 160;
    public static final int DENSITY_HIGH = 240;
    public static final int DENSITY_XHIGH = 320;
    public static final int DENSITY_XXHIGH = 480;
    public static final int DENSITY_XXXHIGH = 640;
    public static final int DENSITY_DEFAULT = DENSITY_MEDIUM;

    public int widthPixels;
    public int heightPixels;
    public float density = 1.0f;
    public float scaledDensity = 1.0f;
    public int densityDpi = 160;
    public float xdpi = 160f;
    public float ydpi = 160f;

    public void setTo(DisplayMetrics other) {
        widthPixels = other.widthPixels;
        heightPixels = other.heightPixels;
        density = other.density;
        scaledDensity = other.scaledDensity;
        densityDpi = other.densityDpi;
        xdpi = other.xdpi;
        ydpi = other.ydpi;
    }
    public void setToDefaults() {
        widthPixels = 0;
        heightPixels = 0;
        density = 1.0f;
        scaledDensity = 1.0f;
        densityDpi = 160;
        xdpi = 160f;
        ydpi = 160f;
    }
    public boolean equalsMetrics(DisplayMetrics other) { return widthPixels == other.widthPixels && heightPixels == other.heightPixels && density == other.density; }
    public int hashCode() { return widthPixels * 31 + heightPixels; }
}
