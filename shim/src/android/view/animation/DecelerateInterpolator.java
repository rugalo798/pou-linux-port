package android.view.animation;

/** Decelerate interpolator. */
public class DecelerateInterpolator implements Interpolator {
    private final float factor;
    public DecelerateInterpolator() { this(1.0f); }
    public DecelerateInterpolator(float factor) { this.factor = factor; }
    public DecelerateInterpolator(android.content.Context context, android.util.AttributeSet attrs) { this(1.0f); }
    @Override public float getInterpolation(float input) {
        if (factor == 1.0f) return 1.0f - (1.0f - input) * (1.0f - input);
        return (float) (1.0f - Math.pow(1.0f - input, factor * 2.0f));
    }
}
