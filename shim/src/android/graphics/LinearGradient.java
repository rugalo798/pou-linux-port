package android.graphics;

import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.geom.Point2D;

/** Linear gradient shader. */
public class LinearGradient extends Shader {
    private final float x0, y0, x1, y1;
    private final int[] colors;
    private final float[] positions;
    private final TileMode tile;

    public LinearGradient(float x0, float y0, float x1, float y1, int[] colors, float[] positions, TileMode tile) {
        this.x0 = x0; this.y0 = y0; this.x1 = x1; this.y1 = y1;
        this.colors = colors; this.positions = positions; this.tile = tile;
    }
    public LinearGradient(float x0, float y0, float x1, float y1, int color0, int color1, TileMode tile) {
        this(x0, y0, x1, y1, new int[]{color0, color1}, new float[]{0f, 1f}, tile);
    }

    @Override
    public java.awt.Paint toAwtPaint() {
        try {
            java.awt.Color[] cs = new java.awt.Color[colors.length];
            for (int i = 0; i < colors.length; i++) cs[i] = new java.awt.Color(colors[i], true);
            if (colors.length == 2) {
                return new java.awt.GradientPaint(x0, y0, cs[0], x1, y1, cs[1], tile == TileMode.REPEAT);
            }
            float[] fractions = positions != null ? positions : evenFractions(colors.length);
            return new LinearGradientPaint(new Point2D.Float(x0, y0), new Point2D.Float(x1, y1), fractions, cs,
                    MultipleGradientPaint.CycleMethod.NO_CYCLE);
        } catch (Exception e) {
            return new java.awt.Color(colors.length > 0 ? colors[0] : 0xFF000000, true);
        }
    }

    static float[] evenFractions(int n) {
        float[] f = new float[n];
        for (int i = 0; i < n; i++) f[i] = n == 1 ? 0f : (float) i / (n - 1);
        return f;
    }
}
