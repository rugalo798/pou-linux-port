package android.view;

/** Simple ViewPropertyAnimator. */
public class ViewPropertyAnimator {
    private final View view;
    private long duration;
    public ViewPropertyAnimator(View view) { this.view = view; }
    public ViewPropertyAnimator alpha(float value) { view.setAlpha(value); return this; }
    public ViewPropertyAnimator translationX(float value) { view.setTranslationX(value); return this; }
    public ViewPropertyAnimator translationY(float value) { view.setTranslationY(value); return this; }
    public ViewPropertyAnimator translationZ(float value) { return this; }
    public ViewPropertyAnimator scaleX(float value) { view.setScaleX(value); return this; }
    public ViewPropertyAnimator scaleY(float value) { view.setScaleY(value); return this; }
    public ViewPropertyAnimator rotation(float value) { view.setRotation(value); return this; }
    public ViewPropertyAnimator rotationX(float value) { return this; }
    public ViewPropertyAnimator rotationY(float value) { return this; }
    public ViewPropertyAnimator x(float value) { return this; }
    public ViewPropertyAnimator y(float value) { return this; }
    public ViewPropertyAnimator z(float value) { return this; }
    public ViewPropertyAnimator setDuration(long duration) { this.duration = duration; return this; }
    public long getDuration() { return duration; }
    public ViewPropertyAnimator setStartDelay(long startDelay) { return this; }
    public ViewPropertyAnimator setInterpolator(android.animation.TimeInterpolator interpolator) { return this; }
    public ViewPropertyAnimator setUpdateListener(Object listener) { return this; }
    public ViewPropertyAnimator withStartAction(Runnable runnable) { return this; }
    public ViewPropertyAnimator withEndAction(Runnable runnable) { if (runnable != null) runnable.run(); return this; }
    public void start() {}
    public void cancel() {}
}
