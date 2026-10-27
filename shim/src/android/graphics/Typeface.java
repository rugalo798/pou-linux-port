package android.graphics;

import android.content.res.AssetManager;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/** Typeface backed by java.awt.Font. */
public class Typeface {
    public static final int NORMAL = 0;
    public static final int BOLD = 1;
    public static final int ITALIC = 2;
    public static final int BOLD_ITALIC = 3;

    public static final Typeface DEFAULT = new Typeface(new Font("SansSerif", Font.PLAIN, 12));
    public static final Typeface DEFAULT_BOLD = new Typeface(new Font("SansSerif", Font.BOLD, 12));
    public static final Typeface SANS_SERIF = new Typeface(new Font("SansSerif", Font.PLAIN, 12));
    public static final Typeface SERIF = new Typeface(new Font("Serif", Font.PLAIN, 12));
    public static final Typeface MONOSPACE = new Typeface(new Font("Monospaced", Font.PLAIN, 12));

    public final Font font;

    public Typeface(Font font) { this.font = font; }

    public static Typeface create(String familyName, int style) {
        int s = style == BOLD ? Font.BOLD : style == ITALIC ? Font.ITALIC : style == BOLD_ITALIC ? (Font.BOLD | Font.ITALIC) : Font.PLAIN;
        return new Typeface(new Font(familyName == null ? "SansSerif" : familyName, s, 12));
    }
    public static Typeface create(Typeface family, int style) {
        return create(family == null ? "SansSerif" : family.font.getFamily(), style);
    }
    public static Typeface createFromAsset(AssetManager mgr, String path) {
        try (InputStream in = mgr.open(path)) {
            Font f = Font.createFont(Font.TRUETYPE_FONT, in);
            return new Typeface(f.deriveFont(12f));
        } catch (Exception e) {
            System.err.println("[pou-desktop] typeface load failed: " + path + " -> " + e);
            return DEFAULT;
        }
    }
    public static Typeface createFromFile(String path) { return createFromFile(new File(path)); }
    public static Typeface createFromFile(File path) {
        try (InputStream in = new FileInputStream(path)) {
            return new Typeface(Font.createFont(Font.TRUETYPE_FONT, in).deriveFont(12f));
        } catch (Exception e) { return DEFAULT; }
    }
    public int getStyle() {
        int s = font.getStyle();
        return s == Font.BOLD ? BOLD : s == Font.ITALIC ? ITALIC : s == (Font.BOLD | Font.ITALIC) ? BOLD_ITALIC : NORMAL;
    }
    public final boolean isBold() { return font.isBold(); }
    public final boolean isItalic() { return font.isItalic(); }
    public int getWeight() { return font.isBold() ? 700 : 400; }
    public static Typeface defaultFromStyle(int style) { return create((String) null, style); }
    public String toString() { return "Typeface(" + font + ")"; }
}
