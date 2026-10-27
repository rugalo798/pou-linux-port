package android.graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

/** Paint state translated to java.awt.Graphics2D at draw time. */
public class Paint {
    public enum Style { FILL, STROKE, FILL_AND_STROKE }
    public enum Align { LEFT, CENTER, RIGHT }
    public enum Cap { BUTT, ROUND, SQUARE }
    public enum Join { MITER, ROUND, BEVEL }

    public static class FontMetrics {
        public float top, ascent, descent, bottom, leading;
    }
    public static class FontMetricsInt {
        public int top, ascent, descent, bottom, leading;
    }

    private int color = 0xFF000000;
    private int alpha = 255;
    private Style style = Style.FILL;
    private float strokeWidth = 1f;
    private float strokeMiter = 4f;
    private Cap cap = Cap.BUTT;
    private Join join = Join.MITER;
    private boolean antialias = true;
    private boolean dither;
    private boolean filterBitmap;
    private boolean subpixelText;
    private boolean linearText;
    private boolean fakeBold;
    private boolean underline;
    private boolean strikeThru;
    private float textSize = 12f;
    private float textScaleX = 1f;
    private float textSkewX;
    private float letterSpacing;
    private Typeface typeface = Typeface.DEFAULT;
    private Align align = Align.LEFT;
    private Shader shader;
    private ColorFilter colorFilter;
    private Xfermode xfermode;
    private PathEffect pathEffect;
    private MaskFilter maskFilter;
    private float shadowRadius, shadowDx, shadowDy;
    private int shadowColor;

    public Paint() {}
    public Paint(int flags) {}
    public Paint(Paint p) { set(p); }

    public void set(Paint p) {
        color = p.color; alpha = p.alpha; style = p.style; strokeWidth = p.strokeWidth;
        strokeMiter = p.strokeMiter; cap = p.cap; join = p.join; antialias = p.antialias;
        dither = p.dither; filterBitmap = p.filterBitmap; subpixelText = p.subpixelText;
        linearText = p.linearText; fakeBold = p.fakeBold; underline = p.underline;
        strikeThru = p.strikeThru; textSize = p.textSize; textScaleX = p.textScaleX;
        textSkewX = p.textSkewX; letterSpacing = p.letterSpacing; typeface = p.typeface;
        align = p.align; shader = p.shader; colorFilter = p.colorFilter; xfermode = p.xfermode;
        pathEffect = p.pathEffect; maskFilter = p.maskFilter; shadowRadius = p.shadowRadius;
        shadowDx = p.shadowDx; shadowDy = p.shadowDy; shadowColor = p.shadowColor;
    }
    public void reset() { set(new Paint()); }

    public int getColor() { return color; }
    public void setColor(int color) { this.color = color; this.alpha = (color >>> 24); }
    public int getAlpha() { return alpha; }
    public void setAlpha(int alpha) { this.alpha = alpha; }
    public void setARGB(int a, int r, int g, int b) { setColor((a << 24) | (r << 16) | (g << 8) | b); }
    public void setRGB(int r, int g, int b) { setColor(0xFF000000 | (r << 16) | (g << 8) | b); }

    public Style getStyle() { return style; }
    public void setStyle(Style style) { this.style = style; }
    public float getStrokeWidth() { return strokeWidth; }
    public void setStrokeWidth(float width) { this.strokeWidth = width; }
    public float getStrokeMiter() { return strokeMiter; }
    public void setStrokeMiter(float miter) { this.strokeMiter = miter; }
    public Cap getStrokeCap() { return cap; }
    public void setStrokeCap(Cap cap) { this.cap = cap; }
    public Join getStrokeJoin() { return join; }
    public void setStrokeJoin(Join join) { this.join = join; }

    public boolean isAntiAlias() { return antialias; }
    public void setAntiAlias(boolean aa) { antialias = aa; }
    public boolean isDither() { return dither; }
    public void setDither(boolean dither) { this.dither = dither; }
    public boolean isFilterBitmap() { return filterBitmap; }
    public void setFilterBitmap(boolean filter) { filterBitmap = filter; }
    public boolean isSubpixelText() { return subpixelText; }
    public void setSubpixelText(boolean subpixel) { subpixelText = subpixel; }
    public boolean isLinearText() { return linearText; }
    public void setLinearText(boolean linear) { linearText = linear; }
    public boolean isFakeBoldText() { return fakeBold; }
    public void setFakeBoldText(boolean fakeBold) { this.fakeBold = fakeBold; }
    public boolean isUnderlineText() { return underline; }
    public void setUnderlineText(boolean underline) { this.underline = underline; }
    public boolean isStrikeThruText() { return strikeThru; }
    public void setStrikeThruText(boolean strikeThru) { this.strikeThru = strikeThru; }

    public float getTextSize() { return textSize; }
    public void setTextSize(float size) { textSize = size; }
    public float getTextScaleX() { return textScaleX; }
    public void setTextScaleX(float scaleX) { textScaleX = scaleX; }
    public float getTextSkewX() { return textSkewX; }
    public void setTextSkewX(float skewX) { textSkewX = skewX; }
    public float getLetterSpacing() { return letterSpacing; }
    public void setLetterSpacing(float spacing) { letterSpacing = spacing; }
    public Typeface getTypeface() { return typeface; }
    public Typeface setTypeface(Typeface tf) { Typeface old = typeface; typeface = tf == null ? Typeface.DEFAULT : tf; return old; }
    public Align getTextAlign() { return align; }
    public void setTextAlign(Align align) { this.align = align; }

    public Shader getShader() { return shader; }
    public Shader setShader(Shader shader) { Shader old = this.shader; this.shader = shader; return old; }
    public ColorFilter getColorFilter() { return colorFilter; }
    public ColorFilter setColorFilter(ColorFilter filter) { ColorFilter old = colorFilter; colorFilter = filter; return old; }
    public Xfermode getXfermode() { return xfermode; }
    public Xfermode setXfermode(Xfermode mode) { Xfermode old = xfermode; xfermode = mode; return old; }
    public PathEffect getPathEffect() { return pathEffect; }
    public PathEffect setPathEffect(PathEffect effect) { PathEffect old = pathEffect; pathEffect = effect; return old; }
    public MaskFilter getMaskFilter() { return maskFilter; }
    public MaskFilter setMaskFilter(MaskFilter filter) { MaskFilter old = maskFilter; maskFilter = filter; return old; }
    public void setShadowLayer(float radius, float dx, float dy, int color) { shadowRadius = radius; shadowDx = dx; shadowDy = dy; shadowColor = color; }
    public void clearShadowLayer() { shadowRadius = 0; }
    public int getShadowLayerColor() { return shadowColor; }

    public Font getAwtFont() {
        Font base = typeface != null ? typeface.font : Typeface.DEFAULT.font;
        Font f = base.deriveFont(textSize);
        if (fakeBold && !f.isBold()) f = f.deriveFont(f.getStyle() | Font.BOLD);
        return f;
    }

    public void apply(Graphics2D g2) {
        int a = Math.max(0, Math.min(255, alpha));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                antialias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                antialias ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                filterBitmap ? RenderingHints.VALUE_INTERPOLATION_BILINEAR : RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        int colorAlpha = (color >>> 24) & 0xFF;
        int effectiveAlpha = Math.max(0, Math.min(255, colorAlpha * a / 255));
        g2.setComposite(compositeFor(xfermode, effectiveAlpha));
        if (shader != null) {
            g2.setPaint(shader.toAwtPaint());
        } else {
            g2.setColor(new java.awt.Color((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF));
        }
        int capCode = cap == Cap.ROUND ? BasicStroke.CAP_ROUND : cap == Cap.SQUARE ? BasicStroke.CAP_SQUARE : BasicStroke.CAP_BUTT;
        int joinCode = join == Join.ROUND ? BasicStroke.JOIN_ROUND : join == Join.BEVEL ? BasicStroke.JOIN_BEVEL : BasicStroke.JOIN_MITER;
        g2.setStroke(new BasicStroke(strokeWidth, capCode, joinCode, strokeMiter));
        g2.setFont(getAwtFont());
    }

    public static AlphaComposite compositeFor(Xfermode mode, int alpha) {
        float a = Math.max(0f, Math.min(1f, alpha / 255f));
        if (mode instanceof PorterDuffXfermode) {
            switch (((PorterDuffXfermode) mode).mode) {
                case CLEAR: return AlphaComposite.getInstance(AlphaComposite.CLEAR, a);
                case SRC: return AlphaComposite.getInstance(AlphaComposite.SRC, a);
                case DST: return AlphaComposite.getInstance(AlphaComposite.DST, a);
                case SRC_OVER: return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a);
                case DST_OVER: return AlphaComposite.getInstance(AlphaComposite.DST_OVER, a);
                case SRC_IN: return AlphaComposite.getInstance(AlphaComposite.SRC_IN, a);
                case DST_IN: return AlphaComposite.getInstance(AlphaComposite.DST_IN, a);
                case SRC_OUT: return AlphaComposite.getInstance(AlphaComposite.SRC_OUT, a);
                case DST_OUT: return AlphaComposite.getInstance(AlphaComposite.DST_OUT, a);
                case SRC_ATOP: return AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, a);
                case DST_ATOP: return AlphaComposite.getInstance(AlphaComposite.DST_ATOP, a);
                case XOR: return AlphaComposite.getInstance(AlphaComposite.XOR, a);
                case DARKEN:
                case MULTIPLY: return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a);
                case LIGHTEN:
                case SCREEN: return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a);
                case ADD: return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a);
                default: return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a);
            }
        }
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, a);
    }

    public float measureText(String text) { return measureText(text, 0, text.length()); }
    public float measureText(String text, int start, int end) {
        return (float) (getAwtFont().getStringBounds(text.substring(start, end), new FontRenderContext(null, antialias, true)).getWidth() * textScaleX);
    }
    public float measureText(CharSequence text, int start, int end) { return measureText(text.toString(), start, end); }
    public float measureText(char[] text, int index, int count) { return measureText(new String(text, index, count)); }
    public int breakText(String text, boolean measureForwards, float maxWidth, float[] measuredWidth) {
        int i = 0;
        while (i < text.length() && measureText(text.substring(0, i + 1)) <= maxWidth) i++;
        if (measuredWidth != null && measuredWidth.length > 0) measuredWidth[0] = measureText(text.substring(0, i));
        return i;
    }
    public int breakText(char[] text, int index, int count, float maxWidth, float[] measuredWidth) {
        return breakText(new String(text, index, count), true, maxWidth, measuredWidth);
    }
    public int breakText(CharSequence text, int start, int end, boolean measureForwards, float maxWidth, float[] measuredWidth) {
        return breakText(text.subSequence(start, end).toString(), measureForwards, maxWidth, measuredWidth);
    }
    public void getTextBounds(String text, int start, int end, Rect bounds) {
        Rectangle2D r = getAwtFont().getStringBounds(text.substring(start, end), new FontRenderContext(null, antialias, true));
        bounds.set((int) Math.floor(r.getX()), (int) Math.floor(r.getY()), (int) Math.ceil(r.getMaxX()), (int) Math.ceil(r.getMaxY()));
    }
    public void getTextBounds(char[] text, int index, int count, Rect bounds) { getTextBounds(new String(text, index, count), 0, count, bounds); }
    public void getTextBounds(CharSequence text, int start, int end, Rect bounds) { getTextBounds(text.toString(), start, end, bounds); }
    public float[] getTextWidths(String text, float[] widths) {
        for (int i = 0; i < text.length(); i++) widths[i] = measureText(text.substring(i, i + 1));
        return widths;
    }
    public int getTextWidths(char[] text, int index, int count, float[] widths) {
        for (int i = 0; i < count; i++) widths[i] = measureText(String.valueOf(text[index + i]));
        return count;
    }
    public int getTextWidths(CharSequence text, int start, int end, float[] widths) {
        for (int i = start; i < end; i++) widths[i - start] = measureText(text.subSequence(i, i + 1).toString());
        return end - start;
    }
    public FontMetrics getFontMetrics() {
        java.awt.FontMetrics fm = getAwtFontMetrics();
        FontMetrics out = new FontMetrics();
        out.top = fm.getAscent() - fm.getAscent();
        out.ascent = fm.getAscent();
        out.descent = fm.getDescent();
        out.bottom = fm.getDescent() + fm.getLeading();
        out.leading = fm.getLeading();
        out.top = -(out.ascent);
        return out;
    }
    public FontMetricsInt getFontMetricsInt() {
        FontMetrics fm = getFontMetrics();
        FontMetricsInt out = new FontMetricsInt();
        out.top = (int) fm.top; out.ascent = (int) fm.ascent; out.descent = (int) fm.descent;
        out.bottom = (int) fm.bottom; out.leading = (int) fm.leading;
        return out;
    }
    public float getFontSpacing() { return getAwtFontMetrics().getHeight(); }
    public java.awt.FontMetrics getAwtFontMetrics() {
        return new java.awt.Canvas().getFontMetrics(getAwtFont());
    }
    public void setFontFeatureSettings(String settings) {}
    public String getFontFeatureSettings() { return null; }
    public void setElegantTextHeight(boolean elegant) {}
    public boolean isElegantTextHeight() { return false; }
    public void setHinting(int mode) {}
    public int getHinting() { return 0; }
    public int getTextRunFlags() { return 0; }
    public void setTextRunFlags(int flags) {}
    public void setWordSpacing(float spacing) {}
}
