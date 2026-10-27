package android.graphics;

import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;

/** Bitmap shader backed by TexturePaint. */
public class BitmapShader extends Shader {
    private final Bitmap bitmap;
    private final TileMode tileX, tileY;

    public BitmapShader(Bitmap bitmap, TileMode tileX, TileMode tileY) {
        this.bitmap = bitmap; this.tileX = tileX; this.tileY = tileY;
    }
    @Override
    public java.awt.Paint toAwtPaint() {
        if (bitmap == null) return java.awt.Color.BLACK;
        return new TexturePaint(bitmap.image, new Rectangle2D.Float(0, 0, bitmap.getWidth(), bitmap.getHeight()));
    }
}
