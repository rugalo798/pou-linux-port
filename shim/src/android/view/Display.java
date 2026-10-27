package android.view;

import android.util.DisplayMetrics;
import desktop.DesktopEnv;

/** Display backed by DesktopEnv. */
public class Display {
    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_OFF = 1;
    public static final int STATE_ON = 2;
    public static final int STATE_DOZE = 3;
    public static final int STATE_DOZE_SUSPEND = 4;
    public static final int STATE_VR = 5;
    public static final int STATE_ON_SUSPEND = 6;
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_90 = 1;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;

    public void getMetrics(DisplayMetrics out) {
        out.widthPixels = DesktopEnv.width;
        out.heightPixels = DesktopEnv.height;
        out.density = DesktopEnv.density;
        out.scaledDensity = DesktopEnv.density;
        out.densityDpi = (int) (DesktopEnv.density * 160);
        out.xdpi = 160 * DesktopEnv.density;
        out.ydpi = 160 * DesktopEnv.density;
    }
    public void getRealMetrics(DisplayMetrics out) { getMetrics(out); }
    public void getSize(android.graphics.Point outSize) { outSize.set(DesktopEnv.width, DesktopEnv.height); }
    public void getRealSize(android.graphics.Point outSize) { getSize(outSize); }
    public int getOrientation() { return android.content.res.Configuration.ORIENTATION_PORTRAIT; }
    public int getRotation() { return ROTATION_0; }
    public int getWidth() { return DesktopEnv.width; }
    public int getHeight() { return DesktopEnv.height; }
    public int getDisplayId() { return 0; }
    public float getRefreshRate() { return 60f; }
    public String getName() { return "Pou Desktop"; }
    public int getState() { return STATE_ON; }
    public boolean isValid() { return true; }
    public boolean isDozeState() { return false; }
    public int getFlags() { return 0; }
    public Mode getMode() { return new Mode(DesktopEnv.width, DesktopEnv.height, 60f); }
    public Mode[] getSupportedModes() { return new Mode[]{getMode()}; }
    public int getHdrCapabilities() { return 0; }
    public boolean isHdr() { return false; }
    public boolean isWideColorGamut() { return false; }
    public boolean isMinimalPostProcessingSupported() { return false; }
    public int getPixelFormat() { return android.graphics.PixelFormat.RGBA_8888; }
    public String toString() { return "Display(" + DesktopEnv.width + "x" + DesktopEnv.height + ")"; }

    public static final class Mode {
        private final int width, height;
        private final float refreshRate;
        public Mode(int width, int height, float refreshRate) { this.width = width; this.height = height; this.refreshRate = refreshRate; }
        public int getModeId() { return 0; }
        public int getPhysicalWidth() { return width; }
        public int getPhysicalHeight() { return height; }
        public float getRefreshRate() { return refreshRate; }
        public boolean equals(Object o) { return o instanceof Mode && ((Mode) o).width == width && ((Mode) o).height == height; }
        public int hashCode() { return width * 31 + height; }
        public String toString() { return width + "x" + height + "@" + refreshRate; }
    }
}
