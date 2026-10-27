package android.view;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import desktop.DesktopEnv;

/** WindowManager interface, matching the Android API shape. */
public interface WindowManager {
    Display getDefaultDisplay();
    void addView(View view, ViewGroup.LayoutParams params);
    void removeView(View view);
    void removeViewImmediate(View view);
    void updateViewLayout(View view, ViewGroup.LayoutParams params);
    WindowMetrics getCurrentWindowMetrics();
    WindowMetrics getMaximumWindowMetrics();
    boolean isApplicationVisible();
    void setApplicationVisible(boolean visible);

    class WindowMetrics {
        public Rect getBounds() { return new Rect(0, 0, DesktopEnv.width, DesktopEnv.height); }
        public android.graphics.Insets getInsets() { return android.graphics.Insets.NONE; }
        public int getDensity() { return (int) (DesktopEnv.density * 160); }
    }

    class LayoutParams extends ViewGroup.LayoutParams implements Parcelable {
        public static final int FLAG_ALLOW_LOCK_WHILE_SCREEN_ON = 0x00000001;
        public static final int FLAG_DIM_BEHIND = 0x00000002;
        public static final int FLAG_BLUR_BEHIND = 0x00000004;
        public static final int FLAG_NOT_FOCUSABLE = 0x00000008;
        public static final int FLAG_NOT_TOUCHABLE = 0x00000010;
        public static final int FLAG_NOT_TOUCH_MODAL = 0x00000020;
        public static final int FLAG_TOUCHABLE_WHEN_WAKING = 0x00000040;
        public static final int FLAG_KEEP_SCREEN_ON = 0x00000080;
        public static final int FLAG_LAYOUT_IN_SCREEN = 0x00000100;
        public static final int FLAG_LAYOUT_NO_LIMITS = 0x00000200;
        public static final int FLAG_FULLSCREEN = 0x00000400;
        public static final int FLAG_FORCE_NOT_FULLSCREEN = 0x00000800;
        public static final int FLAG_SECURE = 0x00002000;
        public static final int FLAG_SCALED = 0x00004000;
        public static final int FLAG_IGNORE_CHEEK_PRESSES = 0x00008000;
        public static final int FLAG_LAYOUT_INSET_DECOR = 0x00010000;
        public static final int FLAG_ALT_FOCUSABLE_IM = 0x00020000;
        public static final int FLAG_WATCH_OUTSIDE_TOUCH = 0x00040000;
        public static final int FLAG_SHOW_WHEN_LOCKED = 0x00080000;
        public static final int FLAG_SHOW_WALLPAPER = 0x00100000;
        public static final int FLAG_TURN_SCREEN_ON = 0x00200000;
        public static final int FLAG_DISMISS_KEYGUARD = 0x00400000;
        public static final int FLAG_SPLIT_TOUCH = 0x00800000;
        public static final int FLAG_HARDWARE_ACCELERATED = 0x01000000;
        public static final int FLAG_LAYOUT_IN_OVERSCAN = 0x02000000;
        public static final int FLAG_TRANSLUCENT_STATUS = 0x04000000;
        public static final int FLAG_TRANSLUCENT_NAVIGATION = 0x08000000;
        public static final int FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS = 0x80000000;
        public static final int FLAG_LAYOUT_ATTACHED_IN_DECOR = 0x40000000;
        public static final int SOFT_INPUT_MASK_STATE = 0x0f;
        public static final int SOFT_INPUT_STATE_UNSPECIFIED = 0;
        public static final int SOFT_INPUT_STATE_UNCHANGED = 1;
        public static final int SOFT_INPUT_STATE_HIDDEN = 2;
        public static final int SOFT_INPUT_STATE_ALWAYS_HIDDEN = 3;
        public static final int SOFT_INPUT_STATE_VISIBLE = 4;
        public static final int SOFT_INPUT_STATE_ALWAYS_VISIBLE = 5;
        public static final int SOFT_INPUT_MASK_ADJUST = 0xf0;
        public static final int SOFT_INPUT_ADJUST_UNSPECIFIED = 0x00;
        public static final int SOFT_INPUT_ADJUST_RESIZE = 0x10;
        public static final int SOFT_INPUT_ADJUST_PAN = 0x20;
        public static final int SOFT_INPUT_ADJUST_NOTHING = 0x30;
        public static final int SOFT_INPUT_IS_FORWARD_NAVIGATION = 0x100;
        public static final int TYPE_APPLICATION = 2;
        public static final int TYPE_APPLICATION_STARTING = 3;
        public static final int TYPE_APPLICATION_PANEL = 1000;
        public static final int TYPE_APPLICATION_ATTACHED_DIALOG = 1001;
        public static final int TYPE_APPLICATION_OVERLAY = 2038;
        public static final int TYPE_SYSTEM_ALERT = 2003;
        public static final int TYPE_SYSTEM_OVERLAY = 2006;
        public static final int TYPE_SYSTEM_ERROR = 2010;
        public static final int TYPE_TOAST = 2005;
        public static final int FIRST_SYSTEM_WINDOW = 2000;
        public static final int LAST_SYSTEM_WINDOW = 2999;
        public static final int LAYOUT_IN_SCREEN = FLAG_LAYOUT_IN_SCREEN;
        public static final int LAYOUT_NO_LIMITS = FLAG_LAYOUT_NO_LIMITS;
        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT = 0;
        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES = 1;
        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER = 2;
        public static final int LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS = 3;

        public int gravity = Gravity.TOP | Gravity.LEFT;
        public int x;
        public int y;
        public float horizontalMargin;
        public float verticalMargin;
        public int format = android.graphics.PixelFormat.OPAQUE;
        public int flags;
        public int type;
        public float alpha = 1f;
        public float dimAmount = 1f;
        public int softInputMode;
        public int screenOrientation = -1;
        public int screenBrightness = -1;
        public int buttonBrightness = -1;
        public int rotationAnimation = -1;
        public float preferredRefreshRate;
        public int preferredDisplayModeId;
        public int layoutInDisplayCutoutMode;
        public int windowAnimations;
        public int windowIsFloating;
        public int windowIsTranslucent;
        public int windowNoTitle;
        public int windowFullscreen;
        public int windowOpaque = 1;
        public int windowShowWallpaper;
        public int windowSwipeToDismiss;
        public int windowCloseOnTouchOutside;
        public int windowDisablePreview;
        public int windowContentTransitions;
        public int windowActivityTransitions;
        public int windowSharedElementsUseOverlay = 1;
        public int windowLightStatusBar;
        public int windowLightNavigationBar;
        public int windowDrawsSystemBarBackgrounds;
        public int windowLayoutInDisplayCutoutMode;
        public float windowElevation;
        public int windowClipToOutline;
        public int windowNoDisplay;
        public int windowIsRound;
        public int windowOutsetBottom;
        public int windowBlurBehindRadius;
        public int windowBlurBehindEnabled;
        public int windowSplashScreenBackground;
        public int windowSplashScreenAnimatedIcon;
        public int windowSplashScreenAnimationDuration;
        public int windowSplashScreenIconBackgroundColor;
        public int windowSplashScreenBehavior;
        public String title;

        public LayoutParams() { super(MATCH_PARENT, MATCH_PARENT); }
        public LayoutParams(int w, int h) { super(w, h); }
        public LayoutParams(int w, int h, int type, int flags, int format) {
            super(w, h); this.type = type; this.flags = flags; this.format = format;
        }
        public LayoutParams(LayoutParams source) {
            super(source.width, source.height);
            copyFrom(source);
        }
        public void copyFrom(LayoutParams o) {
            gravity = o.gravity; x = o.x; y = o.y; horizontalMargin = o.horizontalMargin; verticalMargin = o.verticalMargin;
            format = o.format; flags = o.flags; type = o.type; alpha = o.alpha; dimAmount = o.dimAmount;
            softInputMode = o.softInputMode; screenOrientation = o.screenOrientation; screenBrightness = o.screenBrightness;
            buttonBrightness = o.buttonBrightness; rotationAnimation = o.rotationAnimation; preferredRefreshRate = o.preferredRefreshRate;
            preferredDisplayModeId = o.preferredDisplayModeId; layoutInDisplayCutoutMode = o.layoutInDisplayCutoutMode;
            windowAnimations = o.windowAnimations; windowIsFloating = o.windowIsFloating; windowIsTranslucent = o.windowIsTranslucent;
            windowNoTitle = o.windowNoTitle; windowFullscreen = o.windowFullscreen; windowOpaque = o.windowOpaque;
            windowShowWallpaper = o.windowShowWallpaper; windowSwipeToDismiss = o.windowSwipeToDismiss;
            windowCloseOnTouchOutside = o.windowCloseOnTouchOutside; windowDisablePreview = o.windowDisablePreview;
            windowContentTransitions = o.windowContentTransitions; windowActivityTransitions = o.windowActivityTransitions;
            windowSharedElementsUseOverlay = o.windowSharedElementsUseOverlay; windowLightStatusBar = o.windowLightStatusBar;
            windowLightNavigationBar = o.windowLightNavigationBar; windowDrawsSystemBarBackgrounds = o.windowDrawsSystemBarBackgrounds;
            windowLayoutInDisplayCutoutMode = o.windowLayoutInDisplayCutoutMode; windowElevation = o.windowElevation;
            windowClipToOutline = o.windowClipToOutline; windowNoDisplay = o.windowNoDisplay; windowIsRound = o.windowIsRound;
            windowOutsetBottom = o.windowOutsetBottom; windowBlurBehindRadius = o.windowBlurBehindRadius;
            windowBlurBehindEnabled = o.windowBlurBehindEnabled; windowSplashScreenBackground = o.windowSplashScreenBackground;
            windowSplashScreenAnimatedIcon = o.windowSplashScreenAnimatedIcon;
            windowSplashScreenAnimationDuration = o.windowSplashScreenAnimationDuration;
            windowSplashScreenIconBackgroundColor = o.windowSplashScreenIconBackgroundColor;
            windowSplashScreenBehavior = o.windowSplashScreenBehavior; title = o.title;
        }
        public String getTitle() { return title; }
        public void setTitle(CharSequence title) { this.title = title == null ? null : title.toString(); }
        public void setFitInsetsTypes(int types) {}
        public void setFitInsetsSides(int sides) {}
        public void setFitInsetsIgnoringVisibility(boolean ignored) {}
        public int describeContents() { return 0; }
        public void writeToParcel(Parcel dest, int flags) {}

        public static final Parcelable.Creator<LayoutParams> CREATOR = new Parcelable.Creator<LayoutParams>() {
            public LayoutParams createFromParcel(Parcel source) { return new LayoutParams(); }
            public LayoutParams[] newArray(int size) { return new LayoutParams[size]; }
        };
    }
}
