package android.view.animation;

import android.content.Context;

/** Animation utilities. */
public class AnimationUtils {
    public static long currentAnimationTimeMillis() { return System.currentTimeMillis(); }
    public static Animation loadAnimation(Context context, int id) { return null; }
    public static Animation makeInAnimation(Context context, boolean fromLeft) { return null; }
    public static Animation makeOutAnimation(Context context, boolean toRight) { return null; }
    public static Animation makeInChildBottomAnimation(Context context) { return null; }
    public static LayoutAnimationController loadLayoutAnimation(Context context, int id) { return null; }
    public static float getAnimationScale(Context context) { return 1.0f; }
    public static void setAnimationScale(Context context, float scale) {}
    public static float[] getAnimationScales(Context context) { return new float[]{1.0f, 1.0f, 1.0f}; }
    public static void setAnimationScales(Context context, float[] scales) {}
    public static void lockAnimationClock(long vsyncMillis) {}
    public static void unlockAnimationClock() {}
}
