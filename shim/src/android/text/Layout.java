package android.text;

import android.graphics.Canvas;

/** Minimal Layout base class. */
public abstract class Layout {
    public enum Alignment { ALIGN_NORMAL, ALIGN_OPPOSITE, ALIGN_CENTER }

    public static class Directions {}
    public interface TextInclusionStrategy {}

    protected final CharSequence text;
    protected final TextPaint paint;
    protected final int width;
    protected final Alignment alignment;
    protected final float spacingMultiplier;
    protected final float spacingAddition;

    protected Layout(CharSequence text, TextPaint paint, int width, Alignment alignment,
                     float spacingMultiplier, float spacingAddition) {
        this.text = text == null ? "" : text;
        this.paint = paint;
        this.width = width;
        this.alignment = alignment == null ? Alignment.ALIGN_NORMAL : alignment;
        this.spacingMultiplier = spacingMultiplier;
        this.spacingAddition = spacingAddition;
    }

    public static float getDesiredWidth(CharSequence source, TextPaint paint) {
        return source == null ? 0f : paint.measureText(source.toString());
    }

    public static float getDesiredWidth(CharSequence source, int start, int end, TextPaint paint) {
        return source == null ? 0f : paint.measureText(source.subSequence(start, end).toString());
    }

    public void draw(Canvas canvas) { drawText(canvas); }
    public void drawText(Canvas canvas) {}
    public void drawBackground(Canvas canvas) {}

    public final Alignment getAlignment() { return alignment; }
    public final TextPaint getPaint() { return paint; }
    public final CharSequence getText() { return text; }
    public int getWidth() { return width; }
    public int getHeight() { return getLineTop(getLineCount()); }

    public final int getLineAscent(int line) {
        return -(getLineBottom(line) - getLineTop(line) - getLineDescent(line));
    }
    public final int getLineBaseline(int line) { return getLineTop(line) - getLineAscent(line); }
    public final int getLineBottom(int line) { return getLineTop(line + 1); }
    public final int getLineEnd(int line) { return getLineStart(line + 1); }
    public final int getLineVisibleEnd(int line) { return getLineEnd(line); }
    public float getLineLeft(int line) { return 0f; }
    public float getLineRight(int line) { return width; }
    public float getLineMax(int line) { return width; }
    public float getLineWidth(int line) { return width; }
    public int getLineBounds(int line, android.graphics.Rect bounds) {
        if (bounds != null) bounds.set(0, getLineTop(line), width, getLineBottom(line));
        return getLineBaseline(line);
    }
    public int getLineForVertical(int vertical) {
        int count = getLineCount();
        for (int i = 0; i < count; i++) if (vertical < getLineBottom(i)) return i;
        return Math.max(0, count - 1);
    }
    public int getLineForOffset(int offset) {
        int count = getLineCount();
        for (int i = 0; i < count; i++) if (offset < getLineStart(i + 1)) return i;
        return Math.max(0, count - 1);
    }
    public float getPrimaryHorizontal(int offset) { return 0f; }
    public float getSecondaryHorizontal(int offset) { return 0f; }
    public float getLineSpacingMultiplier() { return spacingMultiplier; }
    public float getLineSpacingAmount() { return spacingAddition; }

    public abstract int getLineCount();
    public abstract int getLineStart(int line);
    public abstract int getLineTop(int line);
    public abstract int getLineDescent(int line);
    public abstract int getParagraphDirection(int line);
    public abstract boolean getLineContainsTab(int line);
    public abstract int getTopPadding();
    public abstract int getBottomPadding();
    public abstract int getEllipsisCount(int line);
    public abstract int getEllipsisStart(int line);
    public abstract Directions getLineDirections(int line);
}
