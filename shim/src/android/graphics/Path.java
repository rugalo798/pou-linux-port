package android.graphics;

import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/** Path backed by java.awt.geom.Path2D.Float. */
public class Path {
    public enum FillType { WINDING, EVEN_ODD, INVERSE_WINDING, INVERSE_EVEN_ODD }
    public enum Direction { CW, CCW }
    public enum Op { DIFFERENCE, INTERSECT, UNION, XOR, REVERSE_DIFFERENCE }

    public final Path2D.Float path = new Path2D.Float(Path2D.WIND_NON_ZERO);
    private FillType fillType = FillType.WINDING;

    public Path() {}
    public Path(Path src) { if (src != null) { path.append(src.path, false); fillType = src.fillType; } }

    public void reset() { path.reset(); }
    public void rewind() { path.reset(); }
    public void set(Path src) { reset(); if (src != null) path.append(src.path, false); }
    public boolean isEmpty() { return path.getCurrentPoint() == null; }
    public boolean isConvex() { return false; }
    public boolean isInverseFillType() { return fillType == FillType.INVERSE_WINDING || fillType == FillType.INVERSE_EVEN_ODD; }
    public void toggleInverseFillType() {}
    public FillType getFillType() { return fillType; }
    public void setFillType(FillType ft) {
        fillType = ft;
        path.setWindingRule(ft == FillType.EVEN_ODD || ft == FillType.INVERSE_EVEN_ODD ? Path2D.WIND_EVEN_ODD : Path2D.WIND_NON_ZERO);
    }

    public void moveTo(float x, float y) { path.moveTo(x, y); }
    public void lineTo(float x, float y) { path.lineTo(x, y); }
    public void quadTo(float x1, float y1, float x2, float y2) { path.quadTo(x1, y1, x2, y2); }
    public void cubicTo(float x1, float y1, float x2, float y2, float x3, float y3) { path.curveTo(x1, y1, x2, y2, x3, y3); }
    public void close() { path.closePath(); }
    public void rMoveTo(float dx, float dy) { path.moveTo(lastX() + dx, lastY() + dy); }
    public void rLineTo(float dx, float dy) { path.lineTo(lastX() + dx, lastY() + dy); }
    public void rQuadTo(float dx1, float dy1, float dx2, float dy2) { path.quadTo(lastX() + dx1, lastY() + dy1, lastX() + dx2, lastY() + dy2); }
    public void rCubicTo(float dx1, float dy1, float dx2, float dy2, float dx3, float dy3) { path.curveTo(lastX() + dx1, lastY() + dy1, lastX() + dx2, lastY() + dy2, lastX() + dx3, lastY() + dy3); }
    private float lastX() { return (float) path.getCurrentPoint().getX(); }
    private float lastY() { return (float) path.getCurrentPoint().getY(); }

    public void addRect(float left, float top, float right, float bottom, Direction dir) { path.append(new Rectangle2D.Float(left, top, right - left, bottom - top), false); }
    public void addRect(RectF rect, Direction dir) { addRect(rect.left, rect.top, rect.right, rect.bottom, dir); }
    public void addRoundRect(float left, float top, float right, float bottom, float rx, float ry, Direction dir) {
        path.append(new java.awt.geom.RoundRectangle2D.Float(left, top, right - left, bottom - top, rx * 2, ry * 2), false);
    }
    public void addRoundRect(RectF rect, float rx, float ry, Direction dir) { addRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, dir); }
    public void addRoundRect(float left, float top, float right, float bottom, float[] radii, Direction dir) { addRoundRect(left, top, right, bottom, radii[0], radii[1], dir); }
    public void addRoundRect(RectF rect, float[] radii, Direction dir) { addRoundRect(rect, radii[0], radii[1], dir); }
    public void addCircle(float x, float y, float radius, Direction dir) { path.append(new java.awt.geom.Ellipse2D.Float(x - radius, y - radius, radius * 2, radius * 2), false); }
    public void addOval(RectF oval, Direction dir) { path.append(new java.awt.geom.Ellipse2D.Float(oval.left, oval.top, oval.width(), oval.height()), false); }
    public void addOval(float left, float top, float right, float bottom, Direction dir) { path.append(new java.awt.geom.Ellipse2D.Float(left, top, right - left, bottom - top), false); }
    public void addArc(RectF oval, float startAngle, float sweepAngle) { addArc(oval.left, oval.top, oval.right, oval.bottom, startAngle, sweepAngle); }
    public void addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle) {
        path.append(new java.awt.geom.Arc2D.Float(left, top, right - left, bottom - top, -startAngle, -sweepAngle, java.awt.geom.Arc2D.OPEN), false);
    }
    public void arcTo(RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo) { addArc(oval, startAngle, sweepAngle); }
    public void arcTo(RectF oval, float startAngle, float sweepAngle) { addArc(oval, startAngle, sweepAngle); }
    public void addPath(Path src) { path.append(src.path, false); }
    public void addPath(Path src, float dx, float dy) { path.append(src.path, true); }
    public void addPath(Path src, Matrix matrix) { path.append(matrix.t.createTransformedShape(src.path), false); }
    public void offset(float dx, float dy) { path.transform(java.awt.geom.AffineTransform.getTranslateInstance(dx, dy)); }
    public void offset(float dx, float dy, Path dst) { dst.set(this); dst.offset(dx, dy); }
    public void transform(Matrix matrix) { path.transform(matrix.t); }
    public void transform(Matrix matrix, Path dst) { dst.set(this); dst.transform(matrix); }
    public void computeBounds(RectF bounds, boolean exact) {
        Rectangle2D r = path.getBounds2D();
        bounds.set((float) r.getX(), (float) r.getY(), (float) r.getMaxX(), (float) r.getMaxY());
    }
    public boolean op(Path path2, Op op) { return op(this, path2, op); }
    public boolean op(Path path1, Path path2, Op op) {
        Area a = new Area(path1.path);
        Area b = new Area(path2.path);
        switch (op) {
            case DIFFERENCE: a.subtract(b); break;
            case INTERSECT: a.intersect(b); break;
            case UNION: a.add(b); break;
            case XOR: a.exclusiveOr(b); break;
            case REVERSE_DIFFERENCE: { Area c = new Area(b); c.subtract(a); a = c; break; }
        }
        path.reset();
        path.append(a, false);
        return true;
    }
    public void setLastPoint(float dx, float dy) {}
    public boolean equals(Object o) { return o instanceof Path && path.equals(((Path) o).path); }
    public int hashCode() { return path.hashCode(); }
}
