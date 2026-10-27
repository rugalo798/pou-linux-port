package android.graphics;

/** Point. */
public class Point implements android.os.Parcelable {
    public int x;
    public int y;
    public Point() {}
    public Point(int x, int y) { this.x = x; this.y = y; }
    public Point(Point src) { this.x = src.x; this.y = src.y; }
    public void set(int x, int y) { this.x = x; this.y = y; }
    public void set(Point src) { this.x = src.x; this.y = src.y; }
    public final void negate() { x = -x; y = -y; }
    public final void offset(int dx, int dy) { x += dx; y += dy; }
    public final boolean equals(int x, int y) { return this.x == x && this.y == y; }
    public boolean equals(Object o) { return o instanceof Point && ((Point) o).x == x && ((Point) o).y == y; }
    public int hashCode() { return 31 * x + y; }
    public String toString() { return "Point(" + x + ", " + y + ")"; }
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) { dest.writeInt(x); dest.writeInt(y); }
}
