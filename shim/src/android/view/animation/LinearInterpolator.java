package android.view.animation;

/** Linear interpolator. */
public class LinearInterpolator implements Interpolator {
    public LinearInterpolator() {}
    public LinearInterpolator(android.content.Context context, android.util.AttributeSet attrs) {}
    @Override public float getInterpolation(float input) { return input; }
}
