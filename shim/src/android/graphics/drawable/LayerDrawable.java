package android.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;

/** Layer list drawable. */
public class LayerDrawable extends Drawable {
    private final Drawable[] layers;

    public LayerDrawable(Drawable[] layers) { this.layers = layers != null ? layers : new Drawable[0]; }

    public Drawable getDrawable(int index) { return index >= 0 && index < layers.length ? layers[index] : null; }
    public int getNumberOfLayers() { return layers.length; }
    public int getId(int index) { return 0; }
    public void setId(int index, int id) {}
    public boolean setDrawableByLayerId(int id, Drawable drawable) { return false; }
    public void setLayerInset(int index, int l, int t, int r, int b) {}
    public void setLayerInsetRelative(int index, int s, int t, int e, int b) {}
    public void setLayerGravity(int index, int gravity) {}
    public void setLayerWidth(int index, int w) {}
    public void setLayerHeight(int index, int h) {}
    public void setPadding(int left, int top, int right, int bottom) {}
    public void setPaddingRelative(int start, int top, int end, int bottom) {}
    public void setOpacity(int opacity) {}
    public void setDrawable(int index, Drawable drawable) {}

    @Override
    public void draw(Canvas canvas) {
        Rect b = getBounds();
        for (Drawable d : layers) {
            if (d != null) {
                d.setBounds(b);
                d.setAlpha(getAlpha());
                d.draw(canvas);
            }
        }
    }

    @Override
    public int getIntrinsicWidth() {
        int w = -1;
        for (Drawable d : layers) if (d != null) w = Math.max(w, d.getIntrinsicWidth());
        return w;
    }
    @Override
    public int getIntrinsicHeight() {
        int h = -1;
        for (Drawable d : layers) if (d != null) h = Math.max(h, d.getIntrinsicHeight());
        return h;
    }
}
