package android.view.animation;

import android.content.Context;
import android.util.AttributeSet;

/** Rotate animation. */
public class RotateAnimation extends Animation {
    private float fromDegrees;
    private float toDegrees;

    public RotateAnimation(float fromDegrees, float toDegrees) {
        this.fromDegrees = fromDegrees; this.toDegrees = toDegrees;
    }
    public RotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY) {
        this(fromDegrees, toDegrees);
    }
    public RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        this(fromDegrees, toDegrees);
    }
    public RotateAnimation(Context context, AttributeSet attrs) {}

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float degrees = fromDegrees + (toDegrees - fromDegrees) * interpolatedTime;
        t.getMatrix().setRotate(degrees);
    }
}
