package android.view.animation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/** Layout animation controller. */
public class LayoutAnimationController {
    public static final int ORDER_NONE = 0;
    public static final int ORDER_RANDOM = 1;
    public static final int ORDER_REVERSE = 2;

    public LayoutAnimationController(Animation animation) {}
    public LayoutAnimationController(Animation animation, float delay) {}
    public LayoutAnimationController(Context context, AttributeSet attrs) {}

    public void setOrder(int order) {}
    public int getOrder() { return ORDER_NONE; }
    public void setDelay(float delay) {}
    public float getDelay() { return 0; }
    public void setAnimation(Animation animation) {}
    public Animation getAnimation() { return null; }
    public void setInterpolator(Interpolator interpolator) {}
    public Interpolator getInterpolator() { return null; }
    public void start() {}
    public boolean isDone() { return true; }
    public Animation getAnimationForView(View v) { return null; }
    public void setAnimationListener(Animation.AnimationListener listener) {}
    public void willChangeBounds() {}
}
