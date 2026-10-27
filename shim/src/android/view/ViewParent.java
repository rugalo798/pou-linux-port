package android.view;

/** Minimal ViewParent. */
public interface ViewParent {
    void requestLayout();
    void invalidateChild(View child, android.graphics.Rect r);
    ViewParent getParent();
    void requestDisallowInterceptTouchEvent(boolean disallowIntercept);
    void bringChildToFront(View child);
    boolean showContextMenuForChild(View originalView);
    void createContextMenu(Object menu);
    boolean getChildVisibleRect(View child, android.graphics.Rect r, android.graphics.Point offset);
}
