package android.view;

/** WindowManager implementation for the desktop shim. */
public class WindowManagerImpl implements WindowManager {
    private final Display display = new Display();

    @Override public Display getDefaultDisplay() { return display; }
    @Override public void addView(View view, ViewGroup.LayoutParams params) {}
    @Override public void removeView(View view) {}
    @Override public void removeViewImmediate(View view) {}
    @Override public void updateViewLayout(View view, ViewGroup.LayoutParams params) {}
    @Override public WindowMetrics getCurrentWindowMetrics() { return new WindowMetrics(); }
    @Override public WindowMetrics getMaximumWindowMetrics() { return new WindowMetrics(); }
    @Override public boolean isApplicationVisible() { return true; }
    @Override public void setApplicationVisible(boolean visible) {}
}
