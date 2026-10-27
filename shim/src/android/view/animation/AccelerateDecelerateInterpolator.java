package android.view.animation;

/** Accelerate/decelerate interpolator. */
public class AccelerateDecelerateInterpolator implements Interpolator {
    public AccelerateDecelerateInterpolator() {}
    public AccelerateDecelerateInterpolator(android.content.Context context, android.util.AttributeSet attrs) {}
    @Override public float getInterpolation(float input) {
        return (float) ((Math.cos((input + 1.0f) * Math.PI) / 2.0f) + 0.5f);
    }
}
