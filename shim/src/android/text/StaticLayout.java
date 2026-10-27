package android.text;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/** StaticLayout backed by simple greedy word wrapping and Java2D text metrics. */
public class StaticLayout extends Layout {
    private final String[] lines;
    private final int[] starts;
    private final int[] tops;
    private final int lineHeight;
    private final int descent;

    public StaticLayout(CharSequence source, TextPaint paint, int width, Alignment align,
                        float spacingmult, float spacingadd, boolean includepad) {
        super(source, paint, width, align, spacingmult, spacingadd);
        String src = source == null ? "" : source.toString();
        this.descent = Math.max(1, paint.getAwtFontMetrics().getDescent());
        int ascent = Math.max(1, paint.getAwtFontMetrics().getAscent());
        int leading = Math.max(0, paint.getAwtFontMetrics().getLeading());
        this.lineHeight = Math.max(1, ascent + descent + leading);
        List<String> ls = new ArrayList<>();
        List<Integer> ss = new ArrayList<>();
        int pos = 0;
        for (String paragraph : src.split("\\n", -1)) {
            int pstart = pos;
            if (width <= 0 || paint.measureText(paragraph) <= width) {
                ls.add(paragraph);
                ss.add(pstart);
            } else {
                int lineStart = 0;
                while (lineStart < paragraph.length()) {
                    int end = lineStart + 1;
                    int lastSpace = -1;
                    while (end <= paragraph.length()) {
                        String candidate = paragraph.substring(lineStart, end);
                        if (paint.measureText(candidate) > width) break;
                        if (end > lineStart && paragraph.charAt(end - 1) == ' ') lastSpace = end;
                        end++;
                    }
                    if (end > paragraph.length()) end = paragraph.length();
                    if (lastSpace > lineStart && end < paragraph.length()) end = lastSpace;
                    if (end <= lineStart) end = Math.min(paragraph.length(), lineStart + 1);
                    ls.add(paragraph.substring(lineStart, end));
                    ss.add(pstart + lineStart);
                    lineStart = end;
                    while (lineStart < paragraph.length() && paragraph.charAt(lineStart) == ' ') lineStart++;
                }
                if (paragraph.isEmpty()) { ls.add(""); ss.add(pstart); }
            }
            pos += paragraph.length() + 1;
        }
        if (ls.isEmpty()) { ls.add(""); ss.add(0); }
        this.lines = ls.toArray(new String[0]);
        this.starts = new int[ss.size()];
        for (int i = 0; i < starts.length; i++) starts[i] = ss.get(i);
        this.tops = new int[lines.length + 1];
        for (int i = 0; i <= lines.length; i++) tops[i] = i * lineHeight;
    }

    public StaticLayout(CharSequence source, int start, int end, TextPaint paint, int width,
                        Alignment align, float spacingmult, float spacingadd, boolean includepad) {
        this(source.subSequence(start, end), paint, width, align, spacingmult, spacingadd, includepad);
    }

    @Override public void drawText(Canvas canvas) {
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.isEmpty()) continue;
            float w = paint.measureText(line);
            float x = alignment == Alignment.ALIGN_CENTER ? (width - w) / 2f
                    : alignment == Alignment.ALIGN_OPPOSITE ? width - w : 0f;
            float y = getLineBaseline(i);
            canvas.drawText(line, x, y, paint);
        }
    }

    @Override public int getLineCount() { return lines.length; }
    @Override public int getLineStart(int line) {
        if (line <= 0) return starts.length > 0 ? starts[0] : 0;
        if (line >= starts.length) return text.length();
        return starts[line];
    }
    @Override public int getLineTop(int line) {
        if (line <= 0) return 0;
        if (line >= tops.length) return tops[tops.length - 1];
        return tops[line];
    }
    @Override public int getLineDescent(int line) { return descent; }
    @Override public int getParagraphDirection(int line) { return 1; }
    @Override public boolean getLineContainsTab(int line) { return false; }
    @Override public int getTopPadding() { return 0; }
    @Override public int getBottomPadding() { return 0; }
    @Override public int getEllipsisCount(int line) { return 0; }
    @Override public int getEllipsisStart(int line) { return 0; }
    @Override public Directions getLineDirections(int line) { return new Directions(); }
}
