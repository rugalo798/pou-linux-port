package android.graphics;

/** Color constants and helpers. */
public class Color {
    public static final int BLACK = 0xFF000000;
    public static final int WHITE = 0xFFFFFFFF;
    public static final int TRANSPARENT = 0x00000000;
    public static final int RED = 0xFFFF0000;
    public static final int GREEN = 0xFF00FF00;
    public static final int BLUE = 0xFF0000FF;
    public static final int YELLOW = 0xFFFFFF00;
    public static final int CYAN = 0xFF00FFFF;
    public static final int MAGENTA = 0xFFFF00FF;
    public static final int GRAY = 0xFF888888;
    public static final int LTGRAY = 0xFFCCCCCC;
    public static final int DKGRAY = 0xFF444444;
    public static final int DARKGRAY = 0xFF444444;
    public static final int LIGHTGRAY = 0xFFCCCCCC;

    public static int alpha(int color) { return color >>> 24; }
    public static int red(int color) { return (color >> 16) & 0xFF; }
    public static int green(int color) { return (color >> 8) & 0xFF; }
    public static int blue(int color) { return color & 0xFF; }
    public static int rgb(int red, int green, int blue) { return 0xFF000000 | (red << 16) | (green << 8) | blue; }
    public static int argb(int alpha, int red, int green, int blue) { return (alpha << 24) | (red << 16) | (green << 8) | blue; }
    public static int parseColor(String colorString) {
        if (colorString.charAt(0) == '#') {
            long color = Long.parseLong(colorString.substring(1), 16);
            if (colorString.length() == 7) color |= 0xFF000000L;
            return (int) color;
        }
        switch (colorString) {
            case "red": return RED;
            case "green": return GREEN;
            case "blue": return BLUE;
            case "black": return BLACK;
            case "white": return WHITE;
            case "gray": return GRAY;
            case "yellow": return YELLOW;
            case "cyan": return CYAN;
            case "magenta": return MAGENTA;
            default: throw new IllegalArgumentException("Unknown color " + colorString);
        }
    }
    public static void RGBToHSV(int red, int green, int blue, float[] hsv) {
        java.awt.Color.RGBtoHSB(red, green, blue, hsv);
    }
    public static void colorToHSV(int color, float[] hsv) {
        RGBToHSV(red(color), green(color), blue(color), hsv);
    }
    public static int HSVToColor(float[] hsv) { return HSVToColor(0xFF, hsv); }
    public static int HSVToColor(int alpha, float[] hsv) {
        int rgb = java.awt.Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]);
        return (alpha << 24) | (rgb & 0xFFFFFF);
    }
}
