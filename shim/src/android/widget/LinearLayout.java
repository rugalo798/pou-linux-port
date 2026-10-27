package android.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import javax.swing.BoxLayout;
import javax.swing.JComponent;

/** LinearLayout with BoxLayout host. */
public class LinearLayout extends ViewGroup {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_END = 4;

    private int orientation = HORIZONTAL;

    public LinearLayout(Context context) { super(context); }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        getComponent().setLayout(new BoxLayout(getComponent(), orientation == VERTICAL ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS));
    }
    public int getOrientation() { return orientation; }
    public void setGravity(int gravity) {}
    public void setHorizontalGravity(int gravity) {}
    public void setVerticalGravity(int gravity) {}
    public void setWeightSum(float weightSum) {}
    public void setBaselineAligned(boolean baselineAligned) {}
    public void setBaselineAlignedChildIndex(int i) {}
    public void setShowDividers(int showDividers) {}
    public void setDividerDrawable(android.graphics.drawable.Drawable divider) {}
    public void setDividerPadding(int padding) {}
    public void setMeasureWithLargestChildEnabled(boolean enabled) {}
    public boolean isBaselineAligned() { return false; }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public float weight;
        public int gravity = -1;
        public LayoutParams(int width, int height) { super(width, height); }
        public LayoutParams(int width, int height, float weight) { super(width, height); this.weight = weight; }
        public LayoutParams(ViewGroup.LayoutParams source) { super(source.width, source.height); }
        public LayoutParams(ViewGroup.MarginLayoutParams source) { super(source); }
    }

    @Override
    public void addView(View child) { addView(child, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)); }
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (!(params instanceof LayoutParams)) params = new LayoutParams(params.width, params.height);
        super.addView(child, params);
    }
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!(params instanceof LayoutParams)) params = new LayoutParams(params.width, params.height);
        super.addView(child, index, params);
    }
}
