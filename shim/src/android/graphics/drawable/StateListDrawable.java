package android.graphics.drawable;

import android.graphics.Canvas;

/** State list drawable. */
public class StateListDrawable extends DrawableContainer {
    public void addState(int[] stateSet, Drawable drawable) {}
    public boolean isStateful() { return true; }
    public boolean setState(int[] stateSet) { return false; }
    public void setEnterFadeDuration(int ms) {}
    public void setExitFadeDuration(int ms) {}
    public Drawable getCurrent() { return null; }
    public void draw(Canvas canvas) {}
}
