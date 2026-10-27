package android.graphics;

/** Base shader. */
public class Shader {
    public enum TileMode { CLAMP, REPEAT, MIRROR, DECAL }

    public java.awt.Paint toAwtPaint() { return java.awt.Color.BLACK; }
    public void setLocalMatrix(Matrix localM) {}
    public boolean getLocalMatrix(Matrix localM) { return false; }
}
