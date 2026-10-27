package android.view.animation;

/** Accelerate interpolator. */
public class AccelerateInterpolator implements Interpolator {
    private final float factor;
    public AccelerateInterpolator() { this(1.0f); }
    public AccelerateInterpolator(float factor) { this.factor = factor; }
    public AccelerateInterpolator(android.content.Context context, android.util.AttributeSet attrs) { this(1.0f); }
    @Override public float getInterpolation(float input) {
        return factor == 1.0f ? input * input : (float) Math.pow(input, factor * 2.0f);
    }
}
