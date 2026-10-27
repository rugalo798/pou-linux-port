package android.animation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Animator set stub. */
public final class AnimatorSet extends Animator {
    private final ArrayList<Animator> animators = new ArrayList<>();

    public void playTogether(Animator... items) {}
    public void playTogether(Collection<Animator> items) {}
    public void playSequentially(Animator... items) {}
    public void playSequentially(List<Animator> items) {}
    public Builder play(Animator anim) { return new Builder(); }
    @Override public void cancel() {}
    @Override public void end() {}
    @Override public boolean isRunning() { return false; }
    @Override public boolean isStarted() { return false; }
    public ArrayList<Animator> getChildAnimations() { return animators; }
    @Override public void setTarget(Object target) {}
    @Override public long getDuration() { return 0; }
    @Override public Animator setDuration(long duration) { return this; }
    @Override public long getStartDelay() { return 0; }
    @Override public void setStartDelay(long startDelay) {}
    @Override public void setInterpolator(TimeInterpolator value) {}
    @Override public TimeInterpolator getInterpolator() { return null; }
    @Override public void start() {}
    @Override public void setupStartValues() {}
    @Override public void setupEndValues() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public long getTotalDuration() { return 0; }

    public static final class Builder {
        public Builder with(Animator anim) { return this; }
        public Builder before(Animator anim) { return this; }
        public Builder after(Animator anim) { return this; }
        public Builder after(long delay) { return this; }
        public Builder play(Animator anim) { return this; }
        public Builder playTogether(Animator... items) { return this; }
        public Builder playSequentially(Animator... items) { return this; }
        public AnimatorSet build() { return new AnimatorSet(); }
    }
}
