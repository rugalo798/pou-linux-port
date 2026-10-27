package android.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import java.io.InputStream;

/** Bitmap drawable. */
public class BitmapDrawable extends Drawable {
    public static final int FIXED = 0;
    public static final int CLAMP = 1;
    public static final int REPEAT = 2;
    public static final int MIRROR = 3;

    private Bitmap bitmap;
    private int gravity = android.view.Gravity.CENTER;
    private int tileModeX = CLAMP;
    private int tileModeY = CLAMP;
    private final Paint paint = new Paint();

    public BitmapDrawable() {}
    public BitmapDrawable(Bitmap bitmap) { this.bitmap = bitmap; }
    public BitmapDrawable(Resources res, Bitmap bitmap) { this.bitmap = bitmap; }
    public BitmapDrawable(Resources res, InputStream is) { this.bitmap = BitmapFactory.decodeStream(is); }
    public BitmapDrawable(String filepath) { this.bitmap = BitmapFactory.decodeFile(filepath); }

    public Bitmap getBitmap() { return bitmap; }
    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }
    public void setGravity(int gravity) { this.gravity = gravity; }
    public int getGravity() { return gravity; }
    public void setTileModeX(int mode) { tileModeX = mode; }
    public void setTileModeY(int mode) { tileModeY = mode; }
    public void setAntiAlias(boolean aa) { paint.setAntiAlias(aa); }
    public void setFilterBitmap(boolean filter) { paint.setFilterBitmap(filter); }
    public void setDither(boolean dither) { paint.setDither(dither); }
    public void setTargetDensity(int density) {}
    public void setTargetDensity(DisplayMetrics metrics) {}
    public void setMipMap(boolean mipMap) {}
    public void setColorFilter(android.graphics.ColorFilter cf) { super.setColorFilter(cf); paint.setColorFilter(cf); }

    @Override
    public void draw(Canvas canvas) {
        if (bitmap == null) return;
        Rect b = getBounds();
        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAlpha(getAlpha());
        canvas.drawBitmap(bitmap, src, b, paint);
    }

    @Override public int getIntrinsicWidth() { return bitmap != null ? bitmap.getWidth() : -1; }
    @Override public int getIntrinsicHeight() { return bitmap != null ? bitmap.getHeight() : -1; }
    @Override public int getOpacity() { return OPACITY_TRANSLUCENT; }
}
