package android.graphics.drawable;

import android.graphics.Canvas;

/** Container for a list of drawables. */
public class DrawableContainer extends Drawable {
    public void setEnterFadeDuration(int ms) {}
    public void setExitFadeDuration(int ms) {}
    public Drawable getCurrent() { return null; }
    public void selectDrawable(int index) {}
    public int getOpacity() { return OPACITY_TRANSLUCENT; }
    public void draw(Canvas canvas) {}
}
