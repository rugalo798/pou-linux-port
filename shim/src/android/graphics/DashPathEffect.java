package android.graphics;

/** Dash path effect. */
public class DashPathEffect extends PathEffect {
    public final float[] intervals;
    public final float phase;
    public DashPathEffect(float[] intervals, float phase) { this.intervals = intervals; this.phase = phase; }
}
