package android.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;

/** Minimal ViewGroup. */
public class ViewGroup extends View implements ViewParent {
    public static final int FOCUS_BEFORE_DESCENDANTS = 0x20000;
    public static final int FOCUS_AFTER_DESCENDANTS = 0x40000;
    public static final int FOCUS_BLOCK_DESCENDANTS = 0x60000;

    private final List<View> children = new ArrayList<>();
    private boolean clipChildren = true;

    public ViewGroup(android.content.Context context) { super(context); }

    public void addView(View child) { addView(child, -1, child.getLayoutParams() != null ? child.getLayoutParams() : new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); }
    public void addView(View child, int index) { addView(child, index, child.getLayoutParams() != null ? child.getLayoutParams() : new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); }
    public void addView(View child, LayoutParams params) { addView(child, -1, params); }
    public void addView(View child, int width, int height) { addView(child, -1, new LayoutParams(width, height)); }
    public void addView(View child, int index, LayoutParams params) {
        if (child == null) return;
        child.setParent(this);
        child.setLayoutParams(params);
        if (index < 0 || index > children.size()) children.add(child); else children.add(index, child);
        if (child.getComponent() != null) {
            if (getComponent().getLayout() instanceof BorderLayout) {
                getComponent().add(child.getComponent(), BorderLayout.CENTER);
            } else {
                getComponent().add(child.getComponent());
            }
        }
        child.onAttachedToWindow();
        getComponent().revalidate();
        getComponent().repaint();
    }
    public void removeView(View child) {
        children.remove(child);
        if (child != null) child.onDetachedFromWindow();
        if (child != null && child.getComponent() != null) getComponent().remove(child.getComponent());
        getComponent().revalidate();
        getComponent().repaint();
    }
    public void removeViewAt(int index) { if (index >= 0 && index < children.size()) removeView(children.get(index)); }
    public void removeAllViews() {
        for (View v : new ArrayList<>(children)) if (v.getComponent() != null) getComponent().remove(v.getComponent());
        children.clear();
        getComponent().revalidate();
        getComponent().repaint();
    }
    public void removeAllViewsInLayout() { removeAllViews(); }
    public int getChildCount() { return children.size(); }
    public View getChildAt(int index) { return index >= 0 && index < children.size() ? children.get(index) : null; }
    public int indexOfChild(View child) { return children.indexOf(child); }
    public void bringChildToFront(View child) { if (children.remove(child)) children.add(child); }
    public void setClipChildren(boolean clipChildren) { this.clipChildren = clipChildren; }
    public boolean getClipChildren() { return clipChildren; }
    public void setClipToPadding(boolean clipToPadding) {}
    public void setDescendantFocusability(int focusability) {}
    public int getDescendantFocusability() { return FOCUS_BEFORE_DESCENDANTS; }
    public void setOnHierarchyChangeListener(Object listener) {}
    public void setLayoutTransition(Object transition) {}
    public void setMotionEventSplittingEnabled(boolean split) {}
    public boolean onInterceptTouchEvent(MotionEvent ev) { return false; }
    public boolean dispatchTouchEvent(MotionEvent ev) { return super.dispatchTouchEvent(ev); }
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
    public void requestLayout() { super.requestLayout(); if (getComponent() != null) getComponent().revalidate(); }
    public void updateViewLayout(View view, LayoutParams params) { if (view != null) view.setLayoutParams(params); }
    public void measureChildren(int widthMeasureSpec, int heightMeasureSpec) {}
    public void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {}
    public static int getChildMeasureSpec(int spec, int padding, int childDimension) { return spec; }
    public void onViewAdded(View child) {}
    public void onViewRemoved(View child) {}
    public void setTouchscreenBlocksFocus(boolean touchscreenBlocksFocus) {}
    public void setAlwaysDrawnWithCacheEnabled(boolean always) {}
    public void setChildrenDrawingCacheEnabled(boolean enabled) {}
    public void setChildrenDrawnWithCacheEnabled(boolean enabled) {}
    public void setPersistentDrawingCache(int drawingCacheToKeep) {}
    public void setStaticTransformationsEnabled(boolean enabled) {}
    public void offsetDescendantRectToMyCoords(View descendant, android.graphics.Rect rect) {}
    public void offsetRectIntoDescendantCoords(View descendant, android.graphics.Rect rect) {}
    public boolean getChildVisibleRect(View child, android.graphics.Rect r, android.graphics.Point offset) { return true; }
    @Override public void invalidateChild(View child, android.graphics.Rect r) { if (getComponent() != null) getComponent().repaint(); }
    @Override public boolean showContextMenuForChild(View originalView) { return false; }
    @Override public void createContextMenu(Object menu) {}

    @Override
    public JComponent getComponent() {
        JComponent c = super.getComponent();
        if (c.getLayout() == null || c.getLayout() instanceof java.awt.FlowLayout) {
            c.setLayout(new BorderLayout());
        }
        return c;
    }

    public static class LayoutParams {
        public static final int MATCH_PARENT = -1;
        public static final int FILL_PARENT = -1;
        public static final int WRAP_CONTENT = -2;
        public int width;
        public int height;
        public LayoutParams(int width, int height) { this.width = width; this.height = height; }
        public LayoutParams(LayoutParams source) { this.width = source.width; this.height = source.height; }
        public void setBaseAttributes(Object a, int widthAttr, int heightAttr) {}
    }

    public static class MarginLayoutParams extends LayoutParams {
        public int leftMargin, topMargin, rightMargin, bottomMargin;
        public int marginStart, marginEnd;
        public MarginLayoutParams(int width, int height) { super(width, height); }
        public MarginLayoutParams(MarginLayoutParams source) { super(source); leftMargin = source.leftMargin; topMargin = source.topMargin; rightMargin = source.rightMargin; bottomMargin = source.bottomMargin; }
        public void setMargins(int left, int top, int right, int bottom) { leftMargin = left; topMargin = top; rightMargin = right; bottomMargin = bottom; }
        public void setMarginStart(int start) { marginStart = start; }
        public void setMarginEnd(int end) { marginEnd = end; }
        public int getMarginStart() { return marginStart; }
        public int getMarginEnd() { return marginEnd; }
    }
}
