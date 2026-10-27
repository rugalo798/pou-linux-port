package android.graphics;

import java.awt.image.BufferedImage;

/** Lighting color filter. */
public class LightingColorFilter extends ColorFilter {
    private final int mul, add;
    public LightingColorFilter(int mul, int add) { this.mul = mul; this.add = add; }
    @Override
    public BufferedImage apply(BufferedImage src) {
        int w = src.getWidth(), h = src.getHeight();
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int mr = (mul >> 16) & 0xFF, mg = (mul >> 8) & 0xFF, mb = mul & 0xFF;
        int ar = (add >> 16) & 0xFF, ag = (add >> 8) & 0xFF, ab = add & 0xFF;
        int[] row = new int[w];
        for (int y = 0; y < h; y++) {
            src.getRGB(0, y, w, 1, row, 0, w);
            for (int x = 0; x < w; x++) {
                int p = row[x];
                int a = (p >>> 24) & 0xFF, r = (p >> 16) & 0xFF, g = (p >> 8) & 0xFF, b = p & 0xFF;
                r = clamp(r * mr / 255 + ar); g = clamp(g * mg / 255 + ag); b = clamp(b * mb / 255 + ab);
                row[x] = (a << 24) | (r << 16) | (g << 8) | b;
            }
            out.setRGB(0, y, w, 1, row, 0, w);
        }
        return out;
    }
    private static int clamp(int v) { return v < 0 ? 0 : v > 255 ? 255 : v; }
}
