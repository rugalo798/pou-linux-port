package android.graphics.drawable;

/** Paint drawable (shape with a paint). */
public class PaintDrawable extends GradientDrawable {
    public PaintDrawable() {}
    public PaintDrawable(int color) { setColor(color); }
    public void setCornerRadius(float radius) { super.setCornerRadius(radius); }
}
