package android.graphics;

/** Blur mask filter (ignored). */
public class BlurMaskFilter extends MaskFilter {
    public enum Blur { NORMAL, SOLID, OUTER, INNER }
    public BlurMaskFilter(float radius, Blur style) {}
}
