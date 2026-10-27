package android.animation;

import android.view.View;
import android.view.ViewGroup;

/** Layout transition stub. */
public class LayoutTransition {
    public static final int CHANGE_APPEARING = 0;
    public static final int CHANGE_DISAPPEARING = 1;
    public static final int CHANGING = 2;
    public static final int APPEARING = 3;
    public static final int DISAPPEARING = 4;

    public void setAnimator(int transitionType, Animator animator) {}
    public Animator getAnimator(int transitionType) { return null; }
    public void setDuration(long duration) {}
    public long getDuration() { return 0; }
    public void setStartDelay(int transitionType, long delay) {}
    public long getStartDelay(int transitionType) { return 0; }
    public void setInterpolator(int transitionType, TimeInterpolator interpolator) {}
    public TimeInterpolator getInterpolator(int transitionType) { return null; }
    public void enableTransitionType(int transitionType) {}
    public void disableTransitionType(int transitionType) {}
    public boolean isTransitionTypeEnabled(int transitionType) { return false; }
    public void setStagger(int transitionType, long duration) {}
    public long getStagger(int transitionType) { return 0; }
    public void setAnimateParentHierarchy(boolean animateParentHierarchy) {}
    public boolean isChangingLayout() { return false; }
    public boolean isRunning() { return false; }
    public void addChild(View child) {}
    public void removeChild(View child) {}
    public void hideChild(View parent, View child, int transitionType) {}
    public void showChild(View parent, View child, int transitionType) {}
    public void setTransitionListener(TransitionListener listener) {}
    public TransitionListener getTransitionListener() { return null; }

    public interface TransitionListener {
        void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType);
        void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType);
    }
}
