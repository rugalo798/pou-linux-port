package android.graphics;

import java.awt.MultipleGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

/** Radial gradient shader. */
public class RadialGradient extends Shader {
    private final float cx, cy, radius;
    private final int[] colors;
    private final float[] positions;
    private final TileMode tile;

    public RadialGradient(float centerX, float centerY, float radius, int[] colors, float[] positions, TileMode tile) {
        this.cx = centerX; this.cy = centerY; this.radius = radius;
        this.colors = colors; this.positions = positions; this.tile = tile;
    }
    public RadialGradient(float centerX, float centerY, float radius, int color0, int color1, TileMode tile) {
        this(centerX, centerY, radius, new int[]{color0, color1}, new float[]{0f, 1f}, tile);
    }

    @Override
    public java.awt.Paint toAwtPaint() {
        try {
            java.awt.Color[] cs = new java.awt.Color[colors.length];
            for (int i = 0; i < colors.length; i++) cs[i] = new java.awt.Color(colors[i], true);
            float[] fractions = positions != null ? positions : LinearGradient.evenFractions(colors.length);
            return new RadialGradientPaint(new Point2D.Float(cx, cy), Math.max(0.01f, radius),
                    new Point2D.Float(cx, cy), fractions, cs, MultipleGradientPaint.CycleMethod.NO_CYCLE);
        } catch (Exception e) {
            return new java.awt.Color(colors.length > 0 ? colors[0] : 0xFF000000, true);
        }
    }
}
