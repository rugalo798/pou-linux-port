package android.view;

/** Gravity constants. */
public class Gravity {
    public static final int NO_GRAVITY = 0x0000;
    public static final int TOP = 0x30;
    public static final int BOTTOM = 0x50;
    public static final int LEFT = 0x03;
    public static final int RIGHT = 0x05;
    public static final int START = 0x00800003;
    public static final int END = 0x00800005;
    public static final int CENTER_VERTICAL = 0x10;
    public static final int CENTER_HORIZONTAL = 0x01;
    public static final int CENTER = 0x11;
    public static final int FILL_VERTICAL = TOP | BOTTOM;
    public static final int FILL_HORIZONTAL = LEFT | RIGHT;
    public static final int FILL = FILL_VERTICAL | FILL_HORIZONTAL;
    public static final int RELATIVE_LAYOUT_DIRECTION = 0x00800000;
    public static final int DISPLAY_CLIP_VERTICAL = 0x10000000;
    public static final int DISPLAY_CLIP_HORIZONTAL = 0x01000000;
    public static int getAbsoluteGravity(int gravity, int layoutDirection) { return gravity; }
}
