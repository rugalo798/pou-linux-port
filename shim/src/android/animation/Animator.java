package android.animation;

import java.util.ArrayList;

/** Animator base. */
public abstract class Animator implements Cloneable {
    public static final long DURATION_INFINITE = -1;

    public void start() {}
    public void cancel() {}
    public void end() {}
    public void pause() {}
    public void resume() {}
    public boolean isPaused() { return false; }
    public abstract long getDuration();
    public long getTotalDuration() { return getDuration(); }
    public abstract long getStartDelay();
    public abstract void setStartDelay(long startDelay);
    public abstract Animator setDuration(long duration);
    public abstract void setInterpolator(TimeInterpolator value);
    public abstract TimeInterpolator getInterpolator();
    public abstract boolean isRunning();
    public boolean isStarted() { return isRunning(); }
    public void addListener(AnimatorListener listener) {}
    public void removeListener(AnimatorListener listener) {}
    public void removeAllListeners() {}
    public void addPauseListener(AnimatorPauseListener listener) {}
    public void removePauseListener(AnimatorPauseListener listener) {}
    public void setTarget(Object target) {}
    public Object getTarget() { return null; }
    public void setupStartValues() {}
    public void setupEndValues() {}
    public ArrayList<AnimatorListener> getListeners() { return new ArrayList<>(); }
    public void setAllowRunningAsynchronously(boolean mayRunAsync) {}
    @Override public Animator clone() {
        try { return (Animator) super.clone(); }
        catch (CloneNotSupportedException e) { return null; }
    }

    public interface AnimatorListener {
        void onAnimationStart(Animator animation);
        void onAnimationEnd(Animator animation);
        void onAnimationCancel(Animator animation);
        void onAnimationRepeat(Animator animation);
    }

    public interface AnimatorPauseListener {
        void onAnimationPause(Animator animation);
        void onAnimationResume(Animator animation);
    }
}
