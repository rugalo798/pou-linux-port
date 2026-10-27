package android.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;

/** Gradient / shape drawable. */
public class GradientDrawable extends Drawable {
    public enum Orientation {
        TOP_BOTTOM, TR_BL, RIGHT_LEFT, BR_TL, BOTTOM_TOP, BL_TR, LEFT_RIGHT, TL_BR
    }
    public static final int RECTANGLE = 0;
    public static final int OVAL = 1;
    public static final int LINE = 2;
    public static final int RING = 3;
    public static final int LINEAR_GRADIENT = 0;
    public static final int RADIAL_GRADIENT = 1;
    public static final int SWEEP_GRADIENT = 2;

    private int shape = RECTANGLE;
    private int color;
    private int[] colors;
    private float cornerRadius;
    private float[] cornerRadii;
    private int strokeWidth;
    private int strokeColor;
    private float dashWidth, dashGap;
    private float gradientRadius;
    private Orientation orientation;
    private int gradientType;
    private int width = -1, height = -1;

    public GradientDrawable() {}
    public GradientDrawable(Orientation orientation, int[] colors) { this.orientation = orientation; this.colors = colors; }

    public void setShape(int shape) { this.shape = shape; }
    public int getShape() { return shape; }
    public void setColor(int color) { this.color = color; this.colors = null; }
    public void setColors(int[] colors) { this.colors = colors; }
    public void setCornerRadius(float radius) { cornerRadius = radius; }
    public void setCornerRadii(float[] radii) { cornerRadii = radii; }
    public void setStroke(int width, int color) { strokeWidth = width; strokeColor = color; dashWidth = 0; dashGap = 0; }
    public void setStroke(int width, int color, float dashWidth, float dashGap) { strokeWidth = width; strokeColor = color; this.dashWidth = dashWidth; this.dashGap = dashGap; }
    public void setGradientType(int gradientType) { this.gradientType = gradientType; }
    public void setGradientRadius(float gradientRadius) { this.gradientRadius = gradientRadius; }
    public void setGradientCenter(float x, float y) {}
    public void setUseLevel(boolean useLevel) {}
    public void setSize(int width, int height) { this.width = width; this.height = height; }
    public void setAlpha(int alpha) { super.setAlpha(alpha); }
    public void setColorFilter(ColorFilter cf) { super.setColorFilter(cf); }
    public void setDither(boolean dither) {}
    public float getCornerRadius() { return cornerRadius; }
    public int getColor() { return color; }
    public int[] getColors() { return colors; }
    public int getGradientType() { return gradientType; }
    public float getGradientRadius() { return gradientRadius; }
    public Orientation getOrientation() { return orientation; }

    @Override
    public void draw(Canvas canvas) {
        Rect b = getBounds();
        android.graphics.RectF rf = new android.graphics.RectF(b);
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setAlpha(getAlpha());
        p.setColorFilter(getColorFilter());
        if (colors != null && colors.length > 0) {
            if (colors.length == 1) p.setColor(colors[0]);
            else {
                float[] pos = new float[colors.length];
                for (int i = 0; i < pos.length; i++) pos[i] = i / (float) (pos.length - 1);
                p.setShader(new LinearGradient(b.left, b.top, b.right, b.bottom, colors, pos, Shader.TileMode.CLAMP));
            }
        } else {
            p.setColor(color);
        }
        switch (shape) {
            case OVAL: canvas.drawOval(rf, p); break;
            case LINE: canvas.drawLine(b.left, b.centerY(), b.right, b.centerY(), p); break;
            default:
                if (cornerRadius > 0) canvas.drawRoundRect(rf, cornerRadius, cornerRadius, p);
                else canvas.drawRect(b, p);
        }
        if (strokeWidth > 0) {
            Paint s = new Paint();
            s.setStyle(Paint.Style.STROKE);
            s.setStrokeWidth(strokeWidth);
            s.setColor(strokeColor);
            s.setAlpha(getAlpha());
            if (cornerRadius > 0) canvas.drawRoundRect(rf, cornerRadius, cornerRadius, s);
            else canvas.drawRect(b, s);
        }
    }

    @Override
    public int getIntrinsicWidth() { return width; }
    @Override
    public int getIntrinsicHeight() { return height; }
}
