package android.graphics;

/** Sweep gradient approximated with a linear gradient. */
public class SweepGradient extends Shader {
    private final float cx, cy;
    private final int[] colors;
    private final float[] positions;

    public SweepGradient(float cx, float cy, int[] colors, float[] positions) {
        this.cx = cx; this.cy = cy; this.colors = colors; this.positions = positions;
    }
    public SweepGradient(float cx, float cy, int color0, int color1) {
        this(cx, cy, new int[]{color0, color1}, new float[]{0f, 1f});
    }
    @Override
    public java.awt.Paint toAwtPaint() {
        return new LinearGradient(cx - 100, cy - 100, cx + 100, cy + 100, colors, positions, TileMode.CLAMP).toAwtPaint();
    }
}
