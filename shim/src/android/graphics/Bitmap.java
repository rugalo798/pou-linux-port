package android.graphics;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import javax.imageio.ImageIO;

/** Bitmap backed by a java.awt.image.BufferedImage. */
public class Bitmap {
    public enum Config { ALPHA_8, RGB_565, ARGB_4444, ARGB_8888, RGBA_F16, HARDWARE }
    public enum CompressFormat { JPEG, PNG, WEBP, WEBP_LOSSLESS, WEBP_LOSSY }

    public final BufferedImage image;
    private int density = 160;
    private boolean recycled;

    public Bitmap(BufferedImage image) { this.image = image; }

    public static Bitmap createBitmap(int width, int height, Config config) {
        return new Bitmap(new BufferedImage(Math.max(1, width), Math.max(1, height), BufferedImage.TYPE_INT_ARGB));
    }
    public static Bitmap createBitmap(int width, int height, Config config, boolean hasAlpha) {
        return createBitmap(width, height, config);
    }
    public static Bitmap createBitmap(Bitmap src) { return createBitmap(src, 0, 0, src.getWidth(), src.getHeight()); }
    public static Bitmap createBitmap(Bitmap src, int x, int y, int w, int h) {
        BufferedImage out = new BufferedImage(Math.max(1, w), Math.max(1, h), BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = out.createGraphics();
        g.drawImage(src.image, -x, -y, null);
        g.dispose();
        return new Bitmap(out);
    }
    public static Bitmap createBitmap(Bitmap src, int x, int y, int w, int h, Matrix m, boolean filter) {
        Bitmap b = createBitmap(src, x, y, w, h);
        if (m != null) {
            BufferedImage out = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_ARGB);
            java.awt.Graphics2D g = out.createGraphics();
            g.drawImage(b.image, m.t, null);
            g.dispose();
            return new Bitmap(out);
        }
        return b;
    }
    public static Bitmap createScaledBitmap(Bitmap src, int dstWidth, int dstHeight, boolean filter) {
        BufferedImage out = new BufferedImage(Math.max(1, dstWidth), Math.max(1, dstHeight), BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = out.createGraphics();
        g.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION,
                filter ? java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR : java.awt.RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g.drawImage(src.image, 0, 0, dstWidth, dstHeight, null);
        g.dispose();
        return new Bitmap(out);
    }
    public static Bitmap createBitmap(int[] colors, int width, int height, Config config) {
        Bitmap b = createBitmap(width, height, config);
        b.image.setRGB(0, 0, width, height, colors, 0, width);
        return b;
    }
    public static Bitmap createBitmap(Bitmap src, int x, int y, int w, int h, Matrix m, boolean filter, Config config) {
        return createBitmap(src, x, y, w, h, m, filter);
    }

    public int getWidth() { return image.getWidth(); }
    public int getHeight() { return image.getHeight(); }
    public int getPixel(int x, int y) { return image.getRGB(x, y); }
    public void setPixel(int x, int y, int color) { image.setRGB(x, y, color); }
    public void getPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height) {
        image.getRGB(x, y, width, height, pixels, offset, stride);
    }
    public void setPixels(int[] pixels, int offset, int stride, int x, int y, int width, int height) {
        image.setRGB(x, y, width, height, pixels, offset, stride);
    }
    public void eraseColor(int c) {
        int[] row = new int[getWidth()];
        java.util.Arrays.fill(row, c);
        for (int y = 0; y < getHeight(); y++) image.setRGB(0, y, getWidth(), 1, row, 0, getWidth());
    }
    public void recycle() { recycled = true; }
    public boolean isRecycled() { return recycled; }
    public Config getConfig() { return Config.ARGB_8888; }
    public int getDensity() { return density; }
    public void setDensity(int density) { this.density = density; }
    public boolean compress(CompressFormat format, int quality, OutputStream stream) {
        try {
            ImageIO.write(image, format == CompressFormat.PNG ? "png" : "jpg", stream);
            return true;
        } catch (Exception e) { return false; }
    }
    public Bitmap copy(Config config, boolean isMutable) {
        return new Bitmap(copyImage(image));
    }
    public static BufferedImage copyImage(BufferedImage src) {
        BufferedImage out = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = out.createGraphics();
        g.drawImage(src, 0, 0, null);
        g.dispose();
        return out;
    }
    public Bitmap extractAlpha() {
        BufferedImage out = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < getHeight(); y++)
            for (int x = 0; x < getWidth(); x++)
                out.setRGB(x, y, (image.getRGB(x, y) >>> 24) << 24 | 0xFFFFFF);
        return new Bitmap(out);
    }
    public int getRowBytes() { return getWidth() * 4; }
    public int getByteCount() { return getWidth() * getHeight() * 4; }
    public int getAllocationByteCount() { return getByteCount(); }
    public boolean isMutable() { return true; }
    public boolean hasAlpha() { return true; }
    public void setHasAlpha(boolean hasAlpha) {}
    public void prepareToDraw() {}
    public void reconfigure(int width, int height, Config config) {}
    public int getGenerationId() { return 0; }
}
