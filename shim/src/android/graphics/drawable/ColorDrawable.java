package android.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/** Solid color drawable. */
public class ColorDrawable extends Drawable {
    private int color;

    public ColorDrawable() {}
    public ColorDrawable(int color) { this.color = color; }

    public int getColor() { return color; }
    public void setColor(int color) { this.color = color; }
    public void setAlpha(int alpha) { super.setAlpha(alpha); }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAlpha(getAlpha());
        Rect b = getBounds();
        canvas.drawRect(b, paint);
    }
    @Override public int getOpacity() { return (color >>> 24) == 0xFF ? OPACITY_OPAQUE : OPACITY_TRANSLUCENT; }

    @Override
    public ConstantState getConstantState() {
        return new ConstantState() {
            public Drawable newDrawable() { return new ColorDrawable(color); }
            public Drawable newDrawable(android.content.res.Resources res) { return new ColorDrawable(color); }
            public Drawable newDrawable(android.content.res.Resources res, android.content.res.Resources.Theme theme) { return new ColorDrawable(color); }
            public int getChangingConfigurations() { return 0; }
        };
    }
}
