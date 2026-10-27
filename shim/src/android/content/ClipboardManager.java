package android.content;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/** ClipboardManager stub. */
public class ClipboardManager {
    private ClipData primaryClip;
    private final List<Object> listeners = new ArrayList<>();

    public ClipData getPrimaryClip() { return primaryClip; }
    public void setPrimaryClip(ClipData clip) { primaryClip = clip; }
    public CharSequence getText() {
        if (primaryClip == null || primaryClip.getItemCount() == 0) return null;
        return primaryClip.getItemAt(0).getText();
    }
    public void setText(CharSequence text) { primaryClip = ClipData.newPlainText("text", text); }
    public boolean hasPrimaryClip() { return primaryClip != null; }
    public boolean hasText() { return getText() != null; }
    public void clearPrimaryClip() { primaryClip = null; }
    public void addPrimaryClipChangedListener(Object listener) { if (listener != null) listeners.add(listener); }
    public void removePrimaryClipChangedListener(Object listener) { listeners.remove(listener); }
    public ClipDescription getPrimaryClipDescription() { return null; }

    /** ClipDescription stub. */
    public static class ClipDescription {
        private final CharSequence label;
        public ClipDescription(CharSequence label, String[] mimeTypes) { this.label = label; }
        public CharSequence getLabel() { return label; }
        public int getMimeTypeCount() { return 0; }
        public String getMimeType(int index) { return null; }
        public boolean hasMimeType(String mimeType) { return false; }
    }
}
