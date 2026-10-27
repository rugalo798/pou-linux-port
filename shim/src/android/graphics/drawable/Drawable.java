package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.TypedValue;

import java.io.InputStream;

/** Base Drawable. */
public abstract class Drawable {
    public static final int OPACITY_TRANSPARENT = -2;
    public static final int OPACITY_TRANSLUCENT = -3;
    public static final int OPACITY_OPAQUE = -1;

    private final Rect bounds = new Rect();
    private int alpha = 255;
    private ColorFilter colorFilter;
    private boolean visible = true;
    private int level;
    private boolean autoMirrored;
    private int layoutDirection;

    public void draw(Canvas canvas) {}
    public void setBounds(int left, int top, int right, int bottom) { bounds.set(left, top, right, bottom); }
    public void setBounds(Rect bounds) { this.bounds.set(bounds); }
    public final void copyBounds(Rect bounds) { bounds.set(this.bounds); }
    public final Rect copyBounds() { return new Rect(bounds); }
    public final Rect getBounds() { return bounds; }
    public void setAlpha(int alpha) { this.alpha = alpha; }
    public int getAlpha() { return alpha; }
    public void setColorFilter(ColorFilter colorFilter) { this.colorFilter = colorFilter; }
    public ColorFilter getColorFilter() { return colorFilter; }
    public void clearColorFilter() { colorFilter = null; }
    public void setDither(boolean dither) {}
    public void setFilterBitmap(boolean filter) {}
    public void setTint(int tintColor) {}
    public void setTintList(ColorStateList tint) {}
    public void setTintMode(PorterDuff.Mode tintMode) {}
    public void setAutoMirrored(boolean mirrored) { autoMirrored = mirrored; }
    public boolean isAutoMirrored() { return autoMirrored; }
    public boolean isVisible() { return visible; }
    public boolean setVisible(boolean visible, boolean restart) { boolean old = this.visible; this.visible = visible; return old; }
    public int getIntrinsicWidth() { return -1; }
    public int getIntrinsicHeight() { return -1; }
    public int getMinimumWidth() { return 0; }
    public int getMinimumHeight() { return 0; }
    public int getOpacity() { return OPACITY_TRANSLUCENT; }
    public void invalidateSelf() {}
    public void scheduleSelf(Runnable what, long when) {}
    public void unscheduleSelf(Runnable what) {}
    public Drawable mutate() { return this; }
    public void jumpToCurrentState() {}
    public boolean getPadding(Rect padding) { padding.set(0, 0, 0, 0); return false; }
    public int getLayoutDirection() { return layoutDirection; }
    public void setLayoutDirection(int layoutDirection) { this.layoutDirection = layoutDirection; }
    public int getLevel() { return level; }
    public boolean setLevel(int level) { this.level = level; return false; }
    public ConstantState getConstantState() { return null; }
    public boolean isStateful() { return false; }
    public boolean setState(int[] stateSet) { return false; }
    public int[] getState() { return new int[0]; }

    public static Drawable createFromPath(String pathName) { return null; }
    public static Drawable createFromStream(InputStream is, String srcName) { return null; }
    public static Drawable createFromResourceStream(Resources res, TypedValue value, InputStream is, String srcName, BitmapFactory.Options opts) { return null; }
    public static Drawable createFromXml(Resources r, Object parser) { return null; }
    public static Drawable createFromXml(Resources r, Object parser, Resources.Theme theme) { return null; }
    public static Drawable createFromXmlInner(Resources r, Object parser, Object attrs, Resources.Theme theme) { return null; }
    public static int resolveOpacity(int op1, int op2) {
        if (op1 == op2) return op1;
        if (op1 == OPACITY_TRANSPARENT || op2 == OPACITY_TRANSPARENT) return OPACITY_TRANSPARENT;
        if (op1 == OPACITY_TRANSLUCENT || op2 == OPACITY_TRANSLUCENT) return OPACITY_TRANSLUCENT;
        return OPACITY_OPAQUE;
    }

    public interface ConstantState {
        Drawable newDrawable();
        Drawable newDrawable(Resources res);
        Drawable newDrawable(Resources res, Resources.Theme theme);
        int getChangingConfigurations();
    }
}
