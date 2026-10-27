package android.graphics;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/** Matrix backed by java.awt.geom.AffineTransform. */
public class Matrix {
    public enum ScaleToFit { FILL, START, CENTER, END }

    public final AffineTransform t = new AffineTransform();

    public Matrix() {}
    public Matrix(Matrix src) { t.setTransform(src.t); }

    public void reset() { t.setToIdentity(); }
    public void set(Matrix src) { t.setTransform(src.t); }
    public void setValues(float[] values) { t.setTransform(values[0], values[1], values[2], values[3], values[4], values[5]); }
    public void getValues(float[] values) {
        double[] d = new double[6];
        t.getMatrix(d);
        for (int i = 0; i < 6; i++) values[i] = (float) d[i];
    }
    public void getMatrix(float[] values) { getValues(values); }
    public void setTranslate(float dx, float dy) { t.setToTranslation(dx, dy); }
    public void setScale(float sx, float sy) { t.setToScale(sx, sy); }
    public void setScale(float sx, float sy, float px, float py) { t.setTransform(sx, 0, 0, sy, px * (1 - sx), py * (1 - sy)); }
    public void setRotate(float degrees) { t.setToRotation(Math.toRadians(degrees)); }
    public void setRotate(float degrees, float px, float py) { t.setToRotation(Math.toRadians(degrees), px, py); }
    public void setSinCos(float sinValue, float cosValue) { t.setTransform(cosValue, sinValue, -sinValue, cosValue, 0, 0); }
    public void setSinCos(float sinValue, float cosValue, float px, float py) {
        t.setTransform(cosValue, sinValue, -sinValue, cosValue, px, py);
    }
    public void setSkew(float kx, float ky) { t.setToShear(kx, ky); }
    public void setSkew(float kx, float ky, float px, float py) { t.setToShear(kx, ky); t.preConcatenate(AffineTransform.getTranslateInstance(px, py)); t.concatenate(AffineTransform.getTranslateInstance(-px, -py)); }
    public void preTranslate(float dx, float dy) { t.preConcatenate(AffineTransform.getTranslateInstance(dx, dy)); }
    public void postTranslate(float dx, float dy) { t.concatenate(AffineTransform.getTranslateInstance(dx, dy)); }
    public void preScale(float sx, float sy) { t.preConcatenate(AffineTransform.getScaleInstance(sx, sy)); }
    public void postScale(float sx, float sy) { t.concatenate(AffineTransform.getScaleInstance(sx, sy)); }
    public void preRotate(float degrees) { t.preConcatenate(AffineTransform.getRotateInstance(Math.toRadians(degrees))); }
    public void postRotate(float degrees) { t.concatenate(AffineTransform.getRotateInstance(Math.toRadians(degrees))); }
    public void preSkew(float kx, float ky) { t.preConcatenate(AffineTransform.getShearInstance(kx, ky)); }
    public void postSkew(float kx, float ky) { t.concatenate(AffineTransform.getShearInstance(kx, ky)); }
    public void preConcat(Matrix other) { t.preConcatenate(other.t); }
    public void postConcat(Matrix other) { t.concatenate(other.t); }
    public void setConcat(Matrix a, Matrix b) { t.setTransform(a.t); t.concatenate(b.t); }
    public boolean invert(Matrix inverse) {
        try { inverse.t.setTransform(t.createInverse()); return true; }
        catch (Exception e) { return false; }
    }
    public boolean isIdentity() { return t.isIdentity(); }
    public boolean rectStaysRect() { return true; }
    public void mapPoints(float[] pts) { mapPoints(pts, 0, pts, 0, pts.length / 2); }
    public void mapPoints(float[] dst, float[] src) { mapPoints(dst, 0, src, 0, src.length / 2); }
    public void mapPoints(float[] dst, int dstIndex, float[] src, int srcIndex, int pointCount) {
        for (int i = 0; i < pointCount; i++) {
            Point2D.Float p = new Point2D.Float(src[srcIndex + i * 2], src[srcIndex + i * 2 + 1]);
            t.transform(p, p);
            dst[dstIndex + i * 2] = p.x;
            dst[dstIndex + i * 2 + 1] = p.y;
        }
    }
    public void mapVectors(float[] vecs) { mapVectors(vecs, 0, vecs, 0, vecs.length / 2); }
    public void mapVectors(float[] dst, float[] src) { mapVectors(dst, 0, src, 0, src.length / 2); }
    public void mapVectors(float[] dst, int dstIndex, float[] src, int srcIndex, int vectorCount) {
        for (int i = 0; i < vectorCount; i++) {
            Point2D.Float p = new Point2D.Float(src[srcIndex + i * 2], src[srcIndex + i * 2 + 1]);
            t.deltaTransform(p, p);
            dst[dstIndex + i * 2] = p.x;
            dst[dstIndex + i * 2 + 1] = p.y;
        }
    }
    public boolean mapRect(RectF rect) { return mapRect(rect, rect); }
    public boolean mapRect(RectF dst, RectF src) {
        float[] pts = {src.left, src.top, src.right, src.top, src.right, src.bottom, src.left, src.bottom};
        mapPoints(pts);
        dst.set(Math.min(Math.min(pts[0], pts[2]), Math.min(pts[4], pts[6])),
                Math.min(Math.min(pts[1], pts[3]), Math.min(pts[5], pts[7])),
                Math.max(Math.max(pts[0], pts[2]), Math.max(pts[4], pts[6])),
                Math.max(Math.max(pts[1], pts[3]), Math.max(pts[5], pts[7])));
        return true;
    }
    public float mapRadius(float radius) {
        float[] vec = {radius, 0};
        mapVectors(vec);
        return (float) Math.hypot(vec[0], vec[1]);
    }
    public void setRectToRect(RectF src, RectF dst, ScaleToFit stf) {
        float sx = dst.width() / src.width();
        float sy = dst.height() / src.height();
        float s = stf == ScaleToFit.FILL ? 1 : Math.min(sx, sy);
        if (stf == ScaleToFit.FILL) { sx = dst.width() / src.width(); sy = dst.height() / src.height(); }
        else { sx = s; sy = s; }
        float dx = dst.left - src.left * sx;
        float dy = dst.top - src.top * sy;
        if (stf == ScaleToFit.CENTER) { dx += (dst.width() - src.width() * sx) / 2; dy += (dst.height() - src.height() * sy) / 2; }
        if (stf == ScaleToFit.END) { dx += dst.width() - src.width() * sx; dy += dst.height() - src.height() * sy; }
        t.setTransform(sx, 0, 0, sy, dx, dy);
    }
    public boolean equals(Object o) { return o instanceof Matrix && t.equals(((Matrix) o).t); }
    public int hashCode() { return t.hashCode(); }
}
