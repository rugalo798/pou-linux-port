package android.graphics;

import java.awt.image.BufferedImage;

/** Color matrix filter applied per pixel. */
public class ColorMatrixColorFilter extends ColorFilter {
    private final float[] m;

    public ColorMatrixColorFilter(ColorMatrix matrix) { m = matrix.getArray().clone(); }
    public ColorMatrixColorFilter(float[] array) { m = array.clone(); }
    public void getColorMatrix(ColorMatrix matrix) { matrix.set(m); }

    @Override
    public BufferedImage apply(BufferedImage src) {
        int w = src.getWidth(), h = src.getHeight();
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int[] row = new int[w];
        for (int y = 0; y < h; y++) {
            src.getRGB(0, y, w, 1, row, 0, w);
            for (int x = 0; x < w; x++) {
                int p = row[x];
                int a = (p >>> 24) & 0xFF, r = (p >> 16) & 0xFF, g = (p >> 8) & 0xFF, b = p & 0xFF;
                int nr = clamp(m[0] * r + m[1] * g + m[2] * b + m[3] * a + m[4] * 255f);
                int ng = clamp(m[5] * r + m[6] * g + m[7] * b + m[8] * a + m[9] * 255f);
                int nb = clamp(m[10] * r + m[11] * g + m[12] * b + m[13] * a + m[14] * 255f);
                int na = clamp(m[15] * r + m[16] * g + m[17] * b + m[18] * a + m[19] * 255f);
                row[x] = (na << 24) | (nr << 16) | (ng << 8) | nb;
            }
            out.setRGB(0, y, w, 1, row, 0, w);
        }
        return out;
    }
    private static int clamp(float v) { return v < 0 ? 0 : v > 255 ? 255 : Math.round(v); }
}
