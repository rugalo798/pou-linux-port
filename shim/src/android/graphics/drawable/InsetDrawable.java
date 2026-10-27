package android.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;

/** Inset drawable. */
public class InsetDrawable extends Drawable {
    private final Drawable drawable;
    private final int left, top, right, bottom;

    public InsetDrawable(Drawable drawable, int inset) { this(drawable, inset, inset, inset, inset); }
    public InsetDrawable(Drawable drawable, int left, int top, int right, int bottom) {
        this.drawable = drawable; this.left = left; this.top = top; this.right = right; this.bottom = bottom;
    }
    public Drawable getDrawable() { return drawable; }

    @Override
    public void draw(Canvas canvas) {
        if (drawable == null) return;
        Rect b = getBounds();
        drawable.setBounds(b.left + left, b.top + top, b.right - right, b.bottom - bottom);
        drawable.setAlpha(getAlpha());
        drawable.draw(canvas);
    }
    @Override public int getIntrinsicWidth() { return drawable != null ? drawable.getIntrinsicWidth() + left + right : -1; }
    @Override public int getIntrinsicHeight() { return drawable != null ? drawable.getIntrinsicHeight() + top + bottom : -1; }
}
