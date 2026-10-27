package android.graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Deque;

/** Canvas backed by java.awt.Graphics2D. */
public class Canvas {
    private Graphics2D g2;
    private int width;
    private int height;
    private BufferedImage target;
    private final Deque<State> stack = new ArrayDeque<>();
    private int density = 160;

    private static final class State {
        AffineTransform transform;
        Shape clip;
        Composite composite;
        java.awt.Paint paint;
        java.awt.Color color;
        Stroke stroke;
        Font font;
    }

    public Canvas() {
        this(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));
    }
    public Canvas(Bitmap bitmap) {
        this(bitmap.image);
    }
    public Canvas(BufferedImage image) {
        this.target = image;
        this.g2 = image.createGraphics();
        this.width = image.getWidth();
        this.height = image.getHeight();
        init();
    }
    public Canvas(Graphics2D graphics, int width, int height) {
        this.g2 = graphics;
        this.width = width;
        this.height = height;
        init();
    }

    private void init() {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }

    public void setBitmap(Bitmap bitmap) {
        if (bitmap == null) { g2 = null; target = null; return; }
        target = bitmap.image;
        g2 = target.createGraphics();
        width = target.getWidth();
        height = target.getHeight();
        init();
    }

    public void dispose() {
        if (g2 != null) {
            g2.dispose();
            g2 = null;
        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getDensity() { return density; }
    public void setDensity(int density) { this.density = density; }
    public boolean isHardwareAccelerated() { return false; }
    public boolean isOpaque() { return false; }
    public void setHighContrastText(boolean highContrastText) {}
    public boolean isHighContrastTextEnabled() { return false; }

    public int save() {
        State s = new State();
        s.transform = g2.getTransform();
        s.clip = g2.getClip();
        s.composite = g2.getComposite();
        s.paint = g2.getPaint();
        s.color = g2.getColor();
        s.stroke = g2.getStroke();
        s.font = g2.getFont();
        stack.push(s);
        return stack.size();
    }
    public void restore() {
        if (stack.isEmpty()) return;
        restoreState(stack.pop());
    }
    public void restoreToCount(int saveCount) {
        while (!stack.isEmpty() && stack.size() >= saveCount) restoreState(stack.pop());
    }
    public int getSaveCount() { return stack.size() + 1; }
    private void restoreState(State s) {
        g2.setTransform(s.transform);
        g2.setClip(s.clip);
        g2.setComposite(s.composite);
        g2.setPaint(s.paint);
        g2.setColor(s.color);
        g2.setStroke(s.stroke);
        g2.setFont(s.font);
    }

    public void translate(float dx, float dy) { g2.translate(dx, dy); }
    public void scale(float sx, float sy) { g2.scale(sx, sy); }
    public void scale(float sx, float sy, float px, float py) {
        g2.translate(px, py);
        g2.scale(sx, sy);
        g2.translate(-px, -py);
    }
    public void rotate(float degrees) { g2.rotate(Math.toRadians(degrees)); }
    public void rotate(float degrees, float px, float py) { g2.rotate(Math.toRadians(degrees), px, py); }
    public void skew(float sx, float sy) { g2.shear(sx, sy); }
    public void concat(Matrix matrix) { g2.transform(matrix.t); }
    public void setMatrix(Matrix matrix) { g2.setTransform(matrix.t); }
    public void getMatrix(Matrix matrix) { matrix.t.setTransform(g2.getTransform()); }

    public boolean clipRect(float left, float top, float right, float bottom) { g2.clip(new Rectangle2D.Float(left, top, right - left, bottom - top)); return true; }
    public boolean clipRect(Rect rect) { return clipRect(rect.left, rect.top, rect.right, rect.bottom); }
    public boolean clipRect(RectF rect) { return clipRect(rect.left, rect.top, rect.right, rect.bottom); }
    public boolean clipRect(int left, int top, int right, int bottom) { return clipRect((float) left, (float) top, (float) right, (float) bottom); }
    public boolean clipPath(Path path) { g2.clip(path.path); return true; }
    public boolean clipOutRect(Rect rect) { return clipOutRect(rect.left, rect.top, rect.right, rect.bottom); }
    public boolean clipOutRect(float left, float top, float right, float bottom) {
        java.awt.geom.Area area = new java.awt.geom.Area(g2.getClip() == null ? new Rectangle2D.Float(0, 0, width, height) : g2.getClip());
        area.subtract(new java.awt.geom.Area(new Rectangle2D.Float(left, top, right - left, bottom - top)));
        g2.clip(area);
        return true;
    }
    public boolean clipOutPath(Path path) {
        java.awt.geom.Area area = new java.awt.geom.Area(g2.getClip() == null ? new Rectangle2D.Float(0, 0, width, height) : g2.getClip());
        area.subtract(new java.awt.geom.Area(path.path));
        g2.clip(area);
        return true;
    }
    public Rect getClipBounds() {
        Rectangle r = g2.getClipBounds();
        if (r == null) return new Rect(0, 0, width, height);
        return new Rect(r.x, r.y, r.x + r.width, r.y + r.height);
    }
    public boolean getClipBounds(Rect bounds) {
        Rectangle r = g2.getClipBounds();
        if (r == null) bounds.set(0, 0, width, height);
        else bounds.set(r.x, r.y, r.x + r.width, r.y + r.height);
        return true;
    }

    public void drawColor(int color) { drawColor(color, PorterDuff.Mode.SRC_OVER); }
    public void drawColor(int color, PorterDuff.Mode mode) {
        g2.setComposite(Paint.compositeFor(new PorterDuffXfermode(mode), android.graphics.Color.alpha(color)));
        g2.setColor(new java.awt.Color(android.graphics.Color.red(color), android.graphics.Color.green(color), android.graphics.Color.blue(color), android.graphics.Color.alpha(color)));
        g2.fillRect(0, 0, width, height);
    }
    public void drawARGB(int a, int r, int g, int b) { drawColor(Color.argb(a, r, g, b)); }
    public void drawRGB(int r, int g, int b) { drawColor(Color.rgb(r, g, b)); }

    public void drawRect(float left, float top, float right, float bottom, Paint paint) {
        prepare(paint);
        Shape s = new Rectangle2D.Float(left, top, right - left, bottom - top);
        fillOrStroke(s, paint);
    }
    public void drawRect(RectF rect, Paint paint) { drawRect(rect.left, rect.top, rect.right, rect.bottom, paint); }
    public void drawRect(Rect rect, Paint paint) { drawRect(rect.left, rect.top, rect.right, rect.bottom, paint); }
    public void drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint) {
        prepare(paint);
        fillOrStroke(new RoundRectangle2D.Float(left, top, right - left, bottom - top, rx * 2, ry * 2), paint);
    }
    public void drawRoundRect(RectF rect, float rx, float ry, Paint paint) { drawRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, paint); }
    public void drawCircle(float cx, float cy, float radius, Paint paint) {
        prepare(paint);
        fillOrStroke(new Ellipse2D.Float(cx - radius, cy - radius, radius * 2, radius * 2), paint);
    }
    public void drawOval(float left, float top, float right, float bottom, Paint paint) {
        prepare(paint);
        fillOrStroke(new Ellipse2D.Float(left, top, right - left, bottom - top), paint);
    }
    public void drawOval(RectF oval, Paint paint) { drawOval(oval.left, oval.top, oval.right, oval.bottom, paint); }
    public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        prepare(paint);
        Arc2D.Float arc = new Arc2D.Float(left, top, right - left, bottom - top, -startAngle, -sweepAngle, useCenter ? Arc2D.PIE : Arc2D.OPEN);
        fillOrStroke(arc, paint);
    }
    public void drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint) { drawArc(oval.left, oval.top, oval.right, oval.bottom, startAngle, sweepAngle, useCenter, paint); }
    public void drawLine(float startX, float startY, float stopX, float stopY, Paint paint) {
        prepare(paint);
        g2.draw(new Line2D.Float(startX, startY, stopX, stopY));
    }
    public void drawLines(float[] pts, Paint paint) { drawLines(pts, 0, pts.length, paint); }
    public void drawLines(float[] pts, int offset, int count, Paint paint) {
        prepare(paint);
        for (int i = offset; i + 3 < offset + count; i += 4) g2.draw(new Line2D.Float(pts[i], pts[i + 1], pts[i + 2], pts[i + 3]));
    }
    public void drawPoint(float x, float y, Paint paint) {
        prepare(paint);
        g2.fill(new Ellipse2D.Float(x, y, 1, 1));
    }
    public void drawPoints(float[] pts, Paint paint) { drawPoints(pts, 0, pts.length, paint); }
    public void drawPoints(float[] pts, int offset, int count, Paint paint) {
        prepare(paint);
        for (int i = offset; i + 1 < offset + count; i += 2) g2.fill(new Ellipse2D.Float(pts[i], pts[i + 1], 1, 1));
    }
    public void drawPaint(Paint paint) { prepare(paint); g2.fillRect(0, 0, width, height); }

    public void drawPath(Path path, Paint paint) {
        prepare(paint);
        fillOrStroke(path.path, paint);
    }

    public void drawBitmap(Bitmap bitmap, float left, float top, Paint paint) {
        if (bitmap == null) return;
        prepare(paint);
        BufferedImage img = applyFilter(bitmap, paint);
        g2.drawImage(img, (int) left, (int) top, null);
    }
    public void drawBitmap(Bitmap bitmap, Rect src, Rect dst, Paint paint) {
        if (bitmap == null) return;
        prepare(paint);
        BufferedImage img = applyFilter(bitmap, paint);
        if (src == null) g2.drawImage(img, dst.left, dst.top, dst.width(), dst.height(), null);
        else g2.drawImage(img, dst.left, dst.top, dst.right, dst.bottom, src.left, src.top, src.right, src.bottom, null);
    }
    public void drawBitmap(Bitmap bitmap, Rect src, RectF dst, Paint paint) {
        if (bitmap == null) return;
        prepare(paint);
        BufferedImage img = applyFilter(bitmap, paint);
        if (src == null) g2.drawImage(img, (int) dst.left, (int) dst.top, (int) dst.width(), (int) dst.height(), null);
        else g2.drawImage(img, (int) dst.left, (int) dst.top, (int) dst.right, (int) dst.bottom, src.left, src.top, src.right, src.bottom, null);
    }
    public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        if (bitmap == null) return;
        prepare(paint);
        BufferedImage img = applyFilter(bitmap, paint);
        g2.drawImage(img, matrix.t, null);
    }
    public void drawBitmap(Bitmap bitmap, float[] matrix, Paint paint) {
        Matrix m = new Matrix();
        m.setValues(matrix);
        drawBitmap(bitmap, m, paint);
    }
    public void drawBitmap(int[] colors, int offset, int stride, float x, float y, int width, int height, boolean hasAlpha, Paint paint) {
        Bitmap b = Bitmap.createBitmap(colors, width, height, Bitmap.Config.ARGB_8888);
        drawBitmap(b, x, y, paint);
    }
    public void drawBitmap(int[] colors, int offset, int stride, int x, int y, int width, int height, boolean hasAlpha, Paint paint) {
        drawBitmap(colors, offset, stride, (float) x, (float) y, width, height, hasAlpha, paint);
    }

    public void drawText(String text, float x, float y, Paint paint) {
        prepare(paint);
        java.awt.Font font = paint.getAwtFont();
        g2.setFont(font);
        float dx = paint.getTextAlign() == Paint.Align.CENTER ? -paint.measureText(text) / 2f : paint.getTextAlign() == Paint.Align.RIGHT ? -paint.measureText(text) : 0;
        java.awt.font.GlyphVector gv = font.createGlyphVector(g2.getFontRenderContext(), text);
        java.awt.Shape outline = gv.getOutline(x + dx, y);
        switch (paint.getStyle()) {
            case FILL: g2.fill(outline); break;
            case STROKE: g2.draw(outline); break;
            case FILL_AND_STROKE: g2.fill(outline); g2.draw(outline); break;
        }
    }
    public void drawText(String text, int start, int end, float x, float y, Paint paint) { drawText(text.substring(start, end), x, y, paint); }
    public void drawText(CharSequence text, int start, int end, float x, float y, Paint paint) { drawText(text.subSequence(start, end).toString(), x, y, paint); }
    public void drawText(char[] text, int index, int count, float x, float y, Paint paint) { drawText(new String(text, index, count), x, y, paint); }
    public void drawTextRun(char[] text, int index, int count, int contextIndex, int contextCount, float x, float y, boolean isRtl, Paint paint) { drawText(new String(text, index, count), x, y, paint); }
    public void drawTextRun(CharSequence text, int start, int end, int contextStart, int contextEnd, float x, float y, boolean isRtl, Paint paint) { drawText(text.subSequence(start, end).toString(), x, y, paint); }
    public void drawTextOnPath(String text, Path path, float hOffset, float vOffset, Paint paint) { drawText(text, 0, 0, paint); }
    public void drawTextOnPath(char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint) { drawText(new String(text, index, count), 0, 0, paint); }
    public void drawPosText(String text, float[] pos, Paint paint) {
        for (int i = 0; i < text.length() && i * 2 + 1 < pos.length; i++) drawText(String.valueOf(text.charAt(i)), pos[i * 2], pos[i * 2 + 1], paint);
    }
    public void drawPosText(char[] text, int index, int count, float[] pos, Paint paint) { drawPosText(new String(text, index, count), pos, paint); }

    public int saveLayer(RectF bounds, Paint paint, int saveFlags) { return save(); }
    public int saveLayer(float left, float top, float right, float bottom, Paint paint, int saveFlags) { return save(); }
    public int saveLayerAlpha(RectF bounds, int alpha, int saveFlags) { return save(); }
    public int saveLayerAlpha(float left, float top, float right, float bottom, int alpha, int saveFlags) { return save(); }
    public int saveLayerAlpha(RectF bounds, int alpha) { return save(); }
    public int saveLayer(RectF bounds, Paint paint) { return save(); }

    private void prepare(Paint paint) {
        if (paint == null) paint = new Paint();
        paint.apply(g2);
    }
    private void fillOrStroke(Shape shape, Paint paint) {
        switch (paint.getStyle()) {
            case FILL: g2.fill(shape); break;
            case STROKE: g2.draw(shape); break;
            case FILL_AND_STROKE: g2.fill(shape); g2.draw(shape); break;
        }
    }
    private BufferedImage applyFilter(Bitmap bitmap, Paint paint) {
        if (paint == null) return bitmap.image;
        ColorFilter filter = paint.getColorFilter();
        if (filter == null) return bitmap.image;
        return filter.apply(bitmap.image);
    }
}
