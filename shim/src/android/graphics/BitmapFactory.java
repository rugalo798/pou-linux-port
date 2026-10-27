package android.graphics;

import android.content.res.Resources;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

/** BitmapFactory over ImageIO. */
public class BitmapFactory {
    public static class Options {
        public boolean inJustDecodeBounds;
        public int outWidth;
        public int outHeight;
        public String outMimeType;
        public int inSampleSize = 1;
        public Bitmap.Config inPreferredConfig = Bitmap.Config.ARGB_8888;
        public boolean inScaled = true;
        public int inDensity;
        public int inTargetDensity;
        public int inScreenDensity;
        public boolean inMutable;
        public boolean inPremultiplied = true;
        public boolean inInputShareable;
        public boolean inPurgeable;
        public byte[] inTempStorage;
    }

    public static Bitmap decodeStream(InputStream is) { return decodeStream(is, null, null); }
    public static Bitmap decodeStream(InputStream is, Rect outPadding, Options opts) {
        try {
            BufferedImage img = ImageIO.read(is);
            if (img == null) return null;
            img = toArgb(img);
            if (opts != null) {
                opts.outWidth = img.getWidth();
                opts.outHeight = img.getHeight();
                if (opts.inJustDecodeBounds) return null;
                if (opts.inSampleSize > 1) {
                    int w = Math.max(1, img.getWidth() / opts.inSampleSize);
                    int h = Math.max(1, img.getHeight() / opts.inSampleSize);
                    img = Bitmap.createScaledBitmap(new Bitmap(img), w, h, true).image;
                }
            }
            return new Bitmap(img);
        } catch (Exception e) {
            return null;
        }
    }
    public static Bitmap decodeFile(String pathName) { return decodeFile(pathName, null); }
    public static Bitmap decodeFile(String pathName, Options opts) {
        try (InputStream in = new FileInputStream(new File(pathName))) {
            return decodeStream(in, null, opts);
        } catch (Exception e) { return null; }
    }
    public static Bitmap decodeByteArray(byte[] data, int offset, int length) { return decodeByteArray(data, offset, length, null); }
    public static Bitmap decodeByteArray(byte[] data, int offset, int length, Options opts) {
        return decodeStream(new ByteArrayInputStream(data, offset, length), null, opts);
    }
    public static Bitmap decodeResource(Resources res, int id) { return decodeResource(res, id, null); }
    public static Bitmap decodeResource(Resources res, int id, Options opts) {
        try (InputStream in = res.openRawResource(id)) { return decodeStream(in, null, opts); }
        catch (Exception e) { return null; }
    }
    public static Bitmap decodeResourceStream(Resources res, android.util.TypedValue value, InputStream is, Rect pad, Options opts) {
        return decodeStream(is, pad, opts);
    }
    public static Bitmap decodeFileDescriptor(java.io.FileDescriptor fd) { return null; }
    public static Bitmap decodeFileDescriptor(java.io.FileDescriptor fd, Rect outPadding, Options opts) { return null; }

    public static BufferedImage toArgb(BufferedImage img) {
        if (img.getType() == BufferedImage.TYPE_INT_ARGB) return img;
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = out.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return out;
    }
}
