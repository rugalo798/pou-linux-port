package android.text;

import android.graphics.Paint;

/** TextPaint with the Android text-specific fields. */
public class TextPaint extends Paint {
    public int baselineShift;
    public int bgColor;
    public float density = 1.0f;
    public int[] drawableState;
    public int linkColor;
    public int underlineColor;
    public float underlineThickness;

    public TextPaint() { super(); }
    public TextPaint(Paint p) { super(p); }
    public TextPaint(int flags) { super(flags); }

    public float getUnderlineThickness() { return underlineThickness; }
    public void set(TextPaint tp) {
        super.set(tp);
        baselineShift = tp.baselineShift;
        bgColor = tp.bgColor;
        density = tp.density;
        drawableState = tp.drawableState;
        linkColor = tp.linkColor;
        underlineColor = tp.underlineColor;
        underlineThickness = tp.underlineThickness;
    }
}
