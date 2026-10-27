package android.graphics;

import java.awt.image.BufferedImage;

/** Base color filter. */
public abstract class ColorFilter {
    public BufferedImage apply(BufferedImage src) { return src; }
}
