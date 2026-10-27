package android.widget;

import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

/** FrameLayout with BorderLayout host. */
public class FrameLayout extends ViewGroup {
    public FrameLayout(Context context) { super(context); }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int UNSPECIFIED_GRAVITY = -1;
        public int gravity = UNSPECIFIED_GRAVITY;
        public LayoutParams(int width, int height) { super(width, height); }
        public LayoutParams(int width, int height, int gravity) { super(width, height); this.gravity = gravity; }
        public LayoutParams(ViewGroup.LayoutParams source) { super(source.width, source.height); }
        public LayoutParams(ViewGroup.MarginLayoutParams source) { super(source); }
    }

    @Override
    public void addView(View child) { addView(child, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)); }
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

    public void setForeground(android.graphics.drawable.Drawable drawable) {}
    public void setForegroundGravity(int gravity) {}
    public void setMeasureAllChildren(boolean measureAll) {}
    public boolean getMeasureAllChildren() { return false; }
}
