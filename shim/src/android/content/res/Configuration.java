package android.content.res;

import java.util.Locale;

/** Configuration. */
public class Configuration {
    public static final int ORIENTATION_UNDEFINED = 0;
    public static final int ORIENTATION_PORTRAIT = 1;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int UI_MODE_TYPE_UNDEFINED = 0;
    public static final int UI_MODE_TYPE_NORMAL = 1;
    public static final int UI_MODE_TYPE_DESK = 2;
    public static final int UI_MODE_TYPE_CAR = 3;
    public static final int UI_MODE_TYPE_TELEVISION = 4;
    public static final int UI_MODE_TYPE_APPLIANCE = 5;
    public static final int UI_MODE_TYPE_WATCH = 6;
    public static final int UI_MODE_TYPE_VR_HEADSET = 7;
    public static final int UI_MODE_NIGHT_UNDEFINED = 0;
    public static final int UI_MODE_NIGHT_NO = 1;
    public static final int UI_MODE_NIGHT_YES = 2;
    public static final int SCREENLAYOUT_SIZE_UNDEFINED = 0;
    public static final int SCREENLAYOUT_SIZE_SMALL = 1;
    public static final int SCREENLAYOUT_SIZE_NORMAL = 2;
    public static final int SCREENLAYOUT_SIZE_LARGE = 3;
    public static final int SCREENLAYOUT_SIZE_XLARGE = 4;
    public static final int SCREENLAYOUT_LONG_UNDEFINED = 0;
    public static final int SCREENLAYOUT_LONG_NO = 0x10;
    public static final int SCREENLAYOUT_LONG_YES = 0x20;
    public static final int SCREENLAYOUT_LAYOUTDIR_UNDEFINED = 0;
    public static final int SCREENLAYOUT_LAYOUTDIR_LTR = 0x40;
    public static final int SCREENLAYOUT_LAYOUTDIR_RTL = 0x80;
    public static final int KEYBOARD_UNDEFINED = 0;
    public static final int KEYBOARD_NOKEYS = 1;
    public static final int KEYBOARD_QWERTY = 2;
    public static final int KEYBOARD_12KEY = 3;
    public static final int KEYBOARDHIDDEN_UNDEFINED = 0;
    public static final int KEYBOARDHIDDEN_NO = 1;
    public static final int KEYBOARDHIDDEN_YES = 2;
    public static final int HARDKEYBOARDHIDDEN_UNDEFINED = 0;
    public static final int HARDKEYBOARDHIDDEN_NO = 1;
    public static final int HARDKEYBOARDHIDDEN_YES = 2;
    public static final int NAVIGATION_UNDEFINED = 0;
    public static final int NAVIGATION_NONAV = 1;
    public static final int NAVIGATION_DPAD = 2;
    public static final int NAVIGATION_TRACKBALL = 3;
    public static final int NAVIGATION_WHEEL = 4;
    public static final int NAVIGATIONHIDDEN_UNDEFINED = 0;
    public static final int NAVIGATIONHIDDEN_NO = 1;
    public static final int NAVIGATIONHIDDEN_YES = 2;
    public static final int TOUCHSCREEN_UNDEFINED = 0;
    public static final int TOUCHSCREEN_NOTOUCH = 1;
    public static final int TOUCHSCREEN_STYLUS = 2;
    public static final int TOUCHSCREEN_FINGER = 3;
    public static final int DENSITY_DPI_UNDEFINED = 0;
    public static final int DENSITY_DPI_VDPI = 140;
    public static final int DENSITY_DPI_ANY = 0xfffe;
    public static final int DENSITY_DPI_NONE = 0xffff;

    public float fontScale = 1f;
    public int mcc;
    public int mnc;
    public Locale locale = Locale.getDefault();
    public int screenLayout;
    public int touchscreen = TOUCHSCREEN_FINGER;
    public int keyboard = KEYBOARD_NOKEYS;
    public int keyboardHidden = KEYBOARDHIDDEN_YES;
    public int hardKeyboardHidden = HARDKEYBOARDHIDDEN_YES;
    public int navigation = NAVIGATION_NONAV;
    public int navigationHidden = NAVIGATIONHIDDEN_YES;
    public int orientation = ORIENTATION_PORTRAIT;
    public int uiMode = UI_MODE_TYPE_NORMAL;
    public int screenWidthDp;
    public int screenHeightDp;
    public int smallestScreenWidthDp;
    public int densityDpi = 160;
    public int colorMode;
    public int grammaticalGender;
    public boolean isScreenRound;
    public int seq;

    public Configuration() {}
    public Configuration(Configuration o) { setTo(o); }
    public void setTo(Configuration o) {
        fontScale = o.fontScale; mcc = o.mcc; mnc = o.mnc; locale = o.locale;
        screenLayout = o.screenLayout; touchscreen = o.touchscreen; keyboard = o.keyboard;
        keyboardHidden = o.keyboardHidden; hardKeyboardHidden = o.hardKeyboardHidden;
        navigation = o.navigation; navigationHidden = o.navigationHidden; orientation = o.orientation;
        uiMode = o.uiMode; screenWidthDp = o.screenWidthDp; screenHeightDp = o.screenHeightDp;
        smallestScreenWidthDp = o.smallestScreenWidthDp; densityDpi = o.densityDpi; colorMode = o.colorMode;
        grammaticalGender = o.grammaticalGender; isScreenRound = o.isScreenRound; seq = o.seq;
    }
    public void setToDefaults() {
        fontScale = 1f; mcc = 0; mnc = 0; locale = Locale.getDefault(); screenLayout = 0;
        touchscreen = TOUCHSCREEN_FINGER; keyboard = KEYBOARD_NOKEYS; keyboardHidden = KEYBOARDHIDDEN_YES;
        hardKeyboardHidden = HARDKEYBOARDHIDDEN_YES; navigation = NAVIGATION_NONAV; navigationHidden = NAVIGATIONHIDDEN_YES;
        orientation = ORIENTATION_PORTRAIT; uiMode = UI_MODE_TYPE_NORMAL; screenWidthDp = 0; screenHeightDp = 0;
        smallestScreenWidthDp = 0; densityDpi = 160; colorMode = 0; grammaticalGender = 0; isScreenRound = false;
    }
    public int diff(Configuration delta) { return 0; }
    public int diff(Configuration delta, boolean compareUndefined) { return 0; }
    public boolean equals(Configuration that) { return true; }
    public boolean equals(Object that) { return that instanceof Configuration; }
    public int hashCode() { return 0; }
    public int compareTo(Configuration that) { return 0; }
    public String toString() { return "Configuration{orientation=" + orientation + ", densityDpi=" + densityDpi + "}"; }
    public String getLocales() { return locale != null ? locale.toString() : ""; }
    public static class Config { }
}
