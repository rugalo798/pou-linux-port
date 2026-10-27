package android.view.animation;

import android.content.Context;
import android.util.AttributeSet;

/** Animation base. */
public abstract class Animation implements Cloneable {
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    public static final int ABSOLUTE = 0;
    public static final int RELATIVE_TO_SELF = 1;
    public static final int RELATIVE_TO_PARENT = 2;
    public static final int ZORDER_NORMAL = 0;
    public static final int ZORDER_TOP = 1;
    public static final int ZORDER_BOTTOM = -1;
    public static final long START_ON_FIRST_FRAME = -1;

    private long duration = 0;
    private long startOffset = 0;
    private long startTime = START_ON_FIRST_FRAME;
    private int repeatCount = 0;
    private int repeatMode = RESTART;
    private boolean fillBefore = true;
    private boolean fillAfter = false;
    private boolean fillEnabled = false;
    private boolean started = false;
    private boolean ended = false;
    private Interpolator interpolator;
    private AnimationListener listener;
    private int zAdjustment = ZORDER_NORMAL;
    private int backgroundColor = 0;
    private boolean detachWallpaper = false;
    private float scaleFactor = 1.0f;

    public Animation() {}

    public void setDuration(long durationMillis) { duration = durationMillis; }
    public long getDuration() { return duration; }
    public void setStartOffset(long startOffset) { this.startOffset = startOffset; }
    public long getStartOffset() { return startOffset; }
    public void setFillBefore(boolean fillBefore) { this.fillBefore = fillBefore; }
    public boolean isFillBefore() { return fillBefore; }
    public void setFillAfter(boolean fillAfter) { this.fillAfter = fillAfter; }
    public boolean isFillAfter() { return fillAfter; }
    public void setFillEnabled(boolean fillEnabled) { this.fillEnabled = fillEnabled; }
    public boolean isFillEnabled() { return fillEnabled; }
    public void setRepeatCount(int repeatCount) { this.repeatCount = repeatCount; }
    public int getRepeatCount() { return repeatCount; }
    public void setRepeatMode(int repeatMode) { this.repeatMode = repeatMode; }
    public int getRepeatMode() { return repeatMode; }
    public void setInterpolator(Interpolator i) { interpolator = i; }
    public Interpolator getInterpolator() { return interpolator; }
    public void setAnimationListener(AnimationListener listener) { this.listener = listener; }
    public AnimationListener getAnimationListener() { return listener; }
    public void setZAdjustment(int zAdjustment) { this.zAdjustment = zAdjustment; }
    public int getZAdjustment() { return zAdjustment; }
    public void setBackgroundColor(int bg) { backgroundColor = bg; }
    public int getBackgroundColor() { return backgroundColor; }
    public void setDetachWallpaper(boolean detachWallpaper) { this.detachWallpaper = detachWallpaper; }
    public boolean getDetachWallpaper() { return detachWallpaper; }
    public void setStartTime(long startTimeMillis) { startTime = startTimeMillis; }
    public long getStartTime() { return startTime; }
    public void start() { started = true; ended = false; startTime = START_ON_FIRST_FRAME; }
    public void startNow() { start(); }
    public void cancel() { started = false; ended = true; }
    public void reset() { started = false; ended = false; startTime = START_ON_FIRST_FRAME; }
    public boolean hasStarted() { return started; }
    public boolean hasEnded() { return ended; }
    public void initialize(int width, int height, int parentWidth, int parentHeight) {}
    public void restrictDuration(long durationMillis) {}
    public void scaleCurrentDuration(float scale) { duration = (long) (duration * scale); }
    public boolean willChangeBounds() { return true; }
    public boolean willChangeTransformationMatrix() { return true; }
    public long computeDurationHint() { return duration; }
    public float getScaleFactor() { return scaleFactor; }
    public void setScaleFactor(float scaleFactor) { this.scaleFactor = scaleFactor; }
    protected void applyTransformation(float interpolatedTime, Transformation t) {}

    public void ensureInterpolator() {
        if (interpolator == null) interpolator = new LinearInterpolator();
    }

    public boolean getTransformation(long currentTime, Transformation outTransformation) {
        if (startTime == START_ON_FIRST_FRAME) startTime = currentTime;
        long elapsed = currentTime - startTime;
        if (elapsed < 0) return true;
        float normalized = duration > 0 ? (float) elapsed / (float) duration : 1.0f;
        if (normalized >= 1.0f) {
            if (repeatCount == 0) {
                if (!ended) {
                    ended = true;
                    started = false;
                    if (listener != null) listener.onAnimationEnd(this);
                }
            } else {
                normalized = normalized % 1.0f;
                if (listener != null) listener.onAnimationRepeat(this);
            }
        } else if (!started) {
            started = true;
            if (listener != null) listener.onAnimationStart(this);
        }
        float interpolated = interpolator != null ? interpolator.getInterpolation(normalized) : normalized;
        applyTransformation(interpolated, outTransformation);
        return normalized < 1.0f || repeatCount != 0;
    }

    public boolean getTransformation(long currentTime, Transformation outTransformation, float scale) {
        return getTransformation(currentTime, outTransformation);
    }

    @Override
    public Animation clone() {
        try { return (Animation) super.clone(); }
        catch (CloneNotSupportedException e) { return null; }
    }

    public interface AnimationListener {
        void onAnimationStart(Animation animation);
        void onAnimationEnd(Animation animation);
        void onAnimationRepeat(Animation animation);
    }
}
