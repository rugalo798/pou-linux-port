package android.graphics;

/** Float rectangle. */
public class RectF {
    public float left, top, right, bottom;
    public RectF() {}
    public RectF(float left, float top, float right, float bottom) { this.left = left; this.top = top; this.right = right; this.bottom = bottom; }
    public RectF(RectF r) { set(r); }
    public RectF(Rect r) { set(r); }
    public void set(float left, float top, float right, float bottom) { this.left = left; this.top = top; this.right = right; this.bottom = bottom; }
    public void set(RectF r) { set(r.left, r.top, r.right, r.bottom); }
    public void set(Rect r) { set(r.left, r.top, r.right, r.bottom); }
    public void setEmpty() { set(0, 0, 0, 0); }
    public boolean isEmpty() { return left >= right || top >= bottom; }
    public float width() { return right - left; }
    public float height() { return bottom - top; }
    public float centerX() { return (left + right) * 0.5f; }
    public float centerY() { return (top + bottom) * 0.5f; }
    public void offset(float dx, float dy) { left += dx; top += dy; right += dx; bottom += dy; }
    public void offsetTo(float newLeft, float newTop) { right += newLeft - left; bottom += newTop - top; left = newLeft; top = newTop; }
    public void inset(float dx, float dy) { left += dx; top += dy; right -= dx; bottom -= dy; }
    public boolean contains(float x, float y) { return left < right && top < bottom && x >= left && x < right && y >= top && y < bottom; }
    public boolean contains(float l, float t, float r, float b) { return left < right && top < bottom && left <= l && top <= t && right >= r && bottom >= b; }
    public boolean contains(RectF r) { return contains(r.left, r.top, r.right, r.bottom); }
    public boolean intersect(RectF r) {
        if (left < r.right && r.left < right && top < r.bottom && r.top < bottom) {
            left = Math.max(left, r.left); top = Math.max(top, r.top);
            right = Math.min(right, r.right); bottom = Math.min(bottom, r.bottom);
            return true;
        }
        return false;
    }
    public boolean intersect(float l, float t, float r, float b) { return intersect(new RectF(l, t, r, b)); }
    public void union(RectF r) {
        if (isEmpty()) { set(r); return; }
        if (r.isEmpty()) return;
        left = Math.min(left, r.left); top = Math.min(top, r.top);
        right = Math.max(right, r.right); bottom = Math.max(bottom, r.bottom);
    }
    public void union(float l, float t, float r, float b) { union(new RectF(l, t, r, b)); }
    public void sort() {
        if (left > right) { float t = left; left = right; right = t; }
        if (top > bottom) { float t = top; top = bottom; bottom = t; }
    }
    public void round(Rect dst) { dst.set(Math.round(left), Math.round(top), Math.round(right), Math.round(bottom)); }
    public void roundOut(Rect dst) { dst.set((int) Math.floor(left), (int) Math.floor(top), (int) Math.ceil(right), (int) Math.ceil(bottom)); }
    public boolean equals(Object o) {
        if (!(o instanceof RectF)) return false;
        RectF r = (RectF) o;
        return left == r.left && top == r.top && right == r.right && bottom == r.bottom;
    }
    public int hashCode() { return ((Float.floatToIntBits(left) * 31 + Float.floatToIntBits(top)) * 31 + Float.floatToIntBits(right)) * 31 + Float.floatToIntBits(bottom); }
    public String toString() { return "RectF(" + left + ", " + top + ", " + right + ", " + bottom + ")"; }
    public String toShortString() { return "[" + left + "," + top + "][" + right + "," + bottom + "]"; }
}
