package android.graphics;

/** Integer rectangle. */
public class Rect {
    public int left, top, right, bottom;
    public Rect() {}
    public Rect(int left, int top, int right, int bottom) { this.left = left; this.top = top; this.right = right; this.bottom = bottom; }
    public Rect(Rect r) { set(r); }
    public void set(int left, int top, int right, int bottom) { this.left = left; this.top = top; this.right = right; this.bottom = bottom; }
    public void set(Rect r) { set(r.left, r.top, r.right, r.bottom); }
    public void setEmpty() { set(0, 0, 0, 0); }
    public boolean isEmpty() { return left >= right || top >= bottom; }
    public int width() { return right - left; }
    public int height() { return bottom - top; }
    public int centerX() { return (left + right) >> 1; }
    public int centerY() { return (top + bottom) >> 1; }
    public float exactCenterX() { return (left + right) * 0.5f; }
    public float exactCenterY() { return (top + bottom) * 0.5f; }
    public void offset(int dx, int dy) { left += dx; top += dy; right += dx; bottom += dy; }
    public void offsetTo(int newLeft, int newTop) { right += newLeft - left; bottom += newTop - top; left = newLeft; top = newTop; }
    public void inset(int dx, int dy) { left += dx; top += dy; right -= dx; bottom -= dy; }
    public boolean contains(int x, int y) { return left < right && top < bottom && x >= left && x < right && y >= top && y < bottom; }
    public boolean contains(Rect r) { return left <= r.left && top <= r.top && right >= r.right && bottom >= r.bottom; }
    public boolean intersect(Rect r) {
        if (left < r.right && r.left < right && top < r.bottom && r.top < bottom) {
            left = Math.max(left, r.left); top = Math.max(top, r.top);
            right = Math.min(right, r.right); bottom = Math.min(bottom, r.bottom);
            return true;
        }
        return false;
    }
    public boolean intersect(int l, int t, int r, int b) { return intersect(new Rect(l, t, r, b)); }
    public void union(Rect r) {
        if (isEmpty()) { set(r); return; }
        if (r.isEmpty()) return;
        left = Math.min(left, r.left); top = Math.min(top, r.top);
        right = Math.max(right, r.right); bottom = Math.max(bottom, r.bottom);
    }
    public void union(int l, int t, int r, int b) { union(new Rect(l, t, r, b)); }
    public void sort() {
        if (left > right) { int t = left; left = right; right = t; }
        if (top > bottom) { int t = top; top = bottom; bottom = t; }
    }
    public boolean equals(Object o) {
        if (!(o instanceof Rect)) return false;
        Rect r = (Rect) o;
        return left == r.left && top == r.top && right == r.right && bottom == r.bottom;
    }
    public int hashCode() { return ((left * 31 + top) * 31 + right) * 31 + bottom; }
    public String toString() { return "Rect(" + left + ", " + top + " - " + right + ", " + bottom + ")"; }
    public String toShortString() { return "[" + left + "," + top + "][" + right + "," + bottom + "]"; }
}
