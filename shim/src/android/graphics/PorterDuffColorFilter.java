package android.graphics;

import java.awt.image.BufferedImage;

/** Porter-Duff color filter (basic tinting). */
public class PorterDuffColorFilter extends ColorFilter {
    private final int color;
    private final PorterDuff.Mode mode;

    public PorterDuffColorFilter(int color, PorterDuff.Mode mode) { this.color = color; this.mode = mode; }

    @Override
    public BufferedImage apply(BufferedImage src) {
        int w = src.getWidth(), h = src.getHeight();
        BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        int ca = (color >>> 24) & 0xFF, cr = (color >> 16) & 0xFF, cg = (color >> 8) & 0xFF, cb = color & 0xFF;
        int[] row = new int[w];
        for (int y = 0; y < h; y++) {
            src.getRGB(0, y, w, 1, row, 0, w);
            for (int x = 0; x < w; x++) {
                int p = row[x];
                int a = (p >>> 24) & 0xFF, r = (p >> 16) & 0xFF, g = (p >> 8) & 0xFF, b = p & 0xFF;
                switch (mode) {
                    case SRC_IN:
                        a = a * ca / 255; r = cr; g = cg; b = cb; break;
                    case MULTIPLY:
                        r = r * cr / 255; g = g * cg / 255; b = b * cb / 255; break;
                    case SRC_ATOP:
                        r = (r * (255 - ca) + cr * ca) / 255; g = (g * (255 - ca) + cg * ca) / 255; b = (b * (255 - ca) + cb * ca) / 255; break;
                    case SRC_OVER:
                        r = (r * (255 - ca) + cr * ca) / 255; g = (g * (255 - ca) + cg * ca) / 255; b = (b * (255 - ca) + cb * ca) / 255; break;
                    default:
                        r = cr; g = cg; b = cb; a = a * ca / 255; break;
                }
                row[x] = (a << 24) | (r << 16) | (g << 8) | b;
            }
            out.setRGB(0, y, w, 1, row, 0, w);
        }
        return out;
    }
}
