package android.view.animation;

import android.graphics.Matrix;

/** Animation transformation. */
public class Transformation {
    public static final int TYPE_IDENTITY = 0;
    public static final int TYPE_ALPHA = 1;
    public static final int TYPE_MATRIX = 2;
    public static final int TYPE_BOTH = 3;

    private final Matrix matrix = new Matrix();
    private float alpha = 1.0f;
    private int transformationType = TYPE_IDENTITY;

    public void clear() {
        matrix.reset();
        alpha = 1.0f;
        transformationType = TYPE_IDENTITY;
    }
    public Matrix getMatrix() { return matrix; }
    public void setMatrix(Matrix matrix) { this.matrix.set(matrix); transformationType |= TYPE_MATRIX; }
    public float getAlpha() { return alpha; }
    public void setAlpha(float alpha) { this.alpha = alpha; transformationType |= TYPE_ALPHA; }
    public void setTransformation(Transformation t) {
        matrix.set(t.matrix);
        alpha = t.alpha;
        transformationType = t.transformationType;
    }
    public void compose(Transformation t) {
        matrix.preConcat(t.matrix);
        alpha *= t.alpha;
        transformationType |= t.transformationType;
    }
    public void postCompose(Transformation t) {
        matrix.postConcat(t.matrix);
        alpha *= t.alpha;
        transformationType |= t.transformationType;
    }
    public int getTransformationType() { return transformationType; }
    public String toString() { return "Transformation(alpha=" + alpha + ")"; }
}
