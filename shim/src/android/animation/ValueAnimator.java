package android.animation;

import java.util.ArrayList;

/** Value animator stub. */
public class ValueAnimator extends Animator {
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;

    private long duration = 300;
    private long startDelay;
    private int repeatCount;
    private int repeatMode = RESTART;
    private TimeInterpolator interpolator;
    private TypeEvaluator evaluator;
    private final ArrayList<AnimatorUpdateListener> updateListeners = new ArrayList<>();

    public ValueAnimator() {}

    public static ValueAnimator ofInt(int... values) { return new ValueAnimator(); }
    public static ValueAnimator ofFloat(float... values) { return new ValueAnimator(); }
    public static ValueAnimator ofArgb(int... values) { return new ValueAnimator(); }
    public static ValueAnimator ofObject(TypeEvaluator evaluator, Object... values) { return new ValueAnimator(); }
    public static ValueAnimator ofPropertyValuesHolder(PropertyValuesHolder... values) { return new ValueAnimator(); }

    public void setIntValues(int... values) {}
    public void setFloatValues(float... values) {}
    public void setObjectValues(Object... values) {}
    public void setEvaluator(TypeEvaluator value) { this.evaluator = value; }
    public TypeEvaluator getEvaluator() { return evaluator; }
    public Object getAnimatedValue() { return null; }
    public Object getAnimatedValue(String propertyName) { return null; }
    public float getAnimatedFraction() { return 0f; }
    public long getCurrentPlayTime() { return 0; }
    public void setCurrentPlayTime(long playTime) {}
    public void setCurrentFraction(float fraction) {}
    public Object[] getValues() { return new Object[0]; }
    @Override public long getDuration() { return duration; }
    @Override public Animator setDuration(long duration) { this.duration = duration; return this; }
    @Override public long getStartDelay() { return startDelay; }
    @Override public void setStartDelay(long startDelay) { this.startDelay = startDelay; }
    public int getRepeatCount() { return repeatCount; }
    public void setRepeatCount(int repeatCount) { this.repeatCount = repeatCount; }
    public int getRepeatMode() { return repeatMode; }
    public void setRepeatMode(int repeatMode) { this.repeatMode = repeatMode; }
    public void addUpdateListener(AnimatorUpdateListener listener) { if (listener != null) updateListeners.add(listener); }
    public void removeUpdateListener(AnimatorUpdateListener listener) { updateListeners.remove(listener); }
    public void removeAllUpdateListeners() { updateListeners.clear(); }
    @Override public void setInterpolator(TimeInterpolator value) { interpolator = value; }
    @Override public TimeInterpolator getInterpolator() { return interpolator; }
    public void setFrameDelay(long frameDelay) {}
    public long getFrameDelay() { return 10; }
    public void setMinimumFraction(float minimumFraction) {}
    public void setMinimumVisibleFraction(float minimumVisibleFraction) {}
    public float getMinimumVisibleFraction() { return 0; }
    public static boolean areAnimatorsEnabled() { return true; }
    public static float getCurrentAnimatorScale() { return 1f; }
    public static void setDurationScale(float durationScale) {}
    public static float getDurationScale() { return 1f; }
    @Override public void start() {}
    @Override public void cancel() {}
    @Override public void end() {}
    public void reverse() {}
    @Override public boolean isRunning() { return false; }
    @Override public boolean isStarted() { return false; }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public boolean isPaused() { return false; }
    @Override public long getTotalDuration() { return duration; }
    @Override public ValueAnimator clone() { return new ValueAnimator(); }

    public interface AnimatorUpdateListener {
        void onAnimationUpdate(ValueAnimator animation);
    }
}
