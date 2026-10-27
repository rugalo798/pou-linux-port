package android.graphics;

/** Insets. */
public final class Insets {
    public static final Insets NONE = new Insets(0, 0, 0, 0);
    public final int left, top, right, bottom;

    private Insets(int left, int top, int right, int bottom) {
        this.left = left; this.top = top; this.right = right; this.bottom = bottom;
    }
    public static Insets of(int left, int top, int right, int bottom) { return new Insets(left, top, right, bottom); }
    public static Insets of(android.graphics.Rect r) { return new Insets(r.left, r.top, r.right, r.bottom); }
    public boolean equals(Object o) {
        if (!(o instanceof Insets)) return false;
        Insets i = (Insets) o;
        return left == i.left && top == i.top && right == i.right && bottom == i.bottom;
    }
    public int hashCode() { return ((left * 31 + top) * 31 + right) * 31 + bottom; }
    public String toString() { return "Insets(" + left + ", " + top + ", " + right + ", " + bottom + ")"; }
}
