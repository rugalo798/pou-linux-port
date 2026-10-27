package android.view.animation;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;

/** Animation set. */
public class AnimationSet extends Animation {
    private final ArrayList<Animation> animations = new ArrayList<>();
    private boolean shareInterpolator;

    public AnimationSet(Context context, AttributeSet attrs) {}
    public AnimationSet(boolean shareInterpolator) { this.shareInterpolator = shareInterpolator; }

    public void addAnimation(Animation a) { if (a != null) animations.add(a); }
    public ArrayList<Animation> getAnimations() { return animations; }
    public boolean willChangeTransformationMatrix() {
        for (Animation a : animations) if (a.willChangeTransformationMatrix()) return true;
        return false;
    }
    public boolean willChangeBounds() {
        for (Animation a : animations) if (a.willChangeBounds()) return true;
        return false;
    }
    public long computeDurationHint() {
        long max = 0;
        for (Animation a : animations) max = Math.max(max, a.computeDurationHint());
        return max;
    }
    public void setStartTime(long startTimeMillis) {
        super.setStartTime(startTimeMillis);
        for (Animation a : animations) a.setStartTime(startTimeMillis);
    }
    public long getStartTime() { return super.getStartTime(); }
    public void restrictDuration(long durationMillis) { for (Animation a : animations) a.restrictDuration(durationMillis); }
    public void scaleCurrentDuration(float scale) { for (Animation a : animations) a.scaleCurrentDuration(scale); }
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        for (Animation a : animations) a.initialize(width, height, parentWidth, parentHeight);
    }
    public void reset() {
        super.reset();
        for (Animation a : animations) a.reset();
    }
    public void setDuration(long durationMillis) {
        super.setDuration(durationMillis);
        for (Animation a : animations) a.setDuration(durationMillis);
    }
    public void setFillAfter(boolean fillAfter) {
        super.setFillAfter(fillAfter);
        for (Animation a : animations) a.setFillAfter(fillAfter);
    }
    public void setFillBefore(boolean fillBefore) {
        super.setFillBefore(fillBefore);
        for (Animation a : animations) a.setFillBefore(fillBefore);
    }
}
