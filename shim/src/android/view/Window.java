package android.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

/** Window backed by the Activity's Swing frame. */
public class Window {
    private final Activity activity;
    private WindowManager.LayoutParams attributes = new WindowManager.LayoutParams();
    private Callback callback;

    public Window(Activity activity) { this.activity = activity; }

    public void setFlags(int flags, int mask) { attributes.flags = (attributes.flags & ~mask) | (flags & mask); }
    public void addFlags(int flags) { attributes.flags |= flags; }
    public void clearFlags(int flags) { attributes.flags &= ~flags; }
    public void setSoftInputMode(int mode) { attributes.softInputMode = mode; }
    public void setBackgroundDrawable(Drawable drawable) {}
    public void setBackgroundDrawableResource(int resId) {}
    public void setLayout(int width, int height) { attributes.width = width; attributes.height = height; }
    public void setGravity(int gravity) { attributes.gravity = gravity; }
    public void setFormat(int format) { attributes.format = format; }
    public void setTitle(CharSequence title) { activity.setTitle(title); }
    public void setStatusBarColor(int color) {}
    public void setNavigationBarColor(int color) {}
    public void setNavigationBarDividerColor(int color) {}
    public void setStatusBarContrastEnforced(boolean ensured) {}
    public void setNavigationBarContrastEnforced(boolean enforced) {}
    public void setDimAmount(float amount) { attributes.dimAmount = amount; }
    public void setAttributes(WindowManager.LayoutParams attrs) { if (attrs != null) attributes = attrs; }
    public WindowManager.LayoutParams getAttributes() { return attributes; }
    public View getDecorView() { return activity.contentView; }
    public View peekDecorView() { return activity.contentView; }
    public void setContentView(View view) { activity.setContentView(view); }
    public void setContentView(int layoutResID) { activity.setContentView(layoutResID); }
    public void addContentView(View view, ViewGroup.LayoutParams params) { activity.addContentView(view, params); }
    public void setVolumeControlStream(int streamType) { activity.setVolumeControlStream(streamType); }
    public int getVolumeControlStream() { return activity.getVolumeControlStream(); }
    public void setCallback(Callback callback) { this.callback = callback; }
    public Callback getCallback() { return callback; }
    public void takeSurface(Object callback) {}
    public void takeInputQueue(Object callback) {}
    public void makeActive() {}
    public void setWindowAnimations(int resId) {}
    public void setWindowManager(WindowManager wm, Object token, String name) {}
    public WindowManager getWindowManager() { return activity.getWindowManager(); }
    public void closeAllPanels() {}
    public boolean isFloating() { return false; }
    public void setElevation(float elevation) {}
    public void setAllowEnterTransitionOverlap(boolean allow) {}
    public void setAllowReturnTransitionOverlap(boolean allow) {}
    public void setEnterTransition(Object transition) {}
    public void setExitTransition(Object transition) {}
    public void setReturnTransition(Object transition) {}
    public void setReenterTransition(Object transition) {}
    public void setSharedElementEnterTransition(Object transition) {}
    public void setSharedElementExitTransition(Object transition) {}
    public void setSharedElementReturnTransition(Object transition) {}
    public void setSharedElementReenterTransition(Object transition) {}
    public void setTransitionBackgroundFadeDuration(long duration) {}
    public void setDecorFitsSystemWindows(boolean decorFitsSystemWindows) {}
    public void setStatusBarBehavior(int behavior) {}
    public void setNavigationBarBehavior(int behavior) {}
    public boolean requestFeature(int featureId) { return false; }
    public boolean hasFeature(int featureId) { return false; }
    public void addFeature(int featureId) {}
    public int getFeatures() { return 0; }

    public interface Callback {
        boolean dispatchKeyEvent(KeyEvent event);
        boolean dispatchTouchEvent(MotionEvent event);
        boolean dispatchTrackballEvent(MotionEvent event);
        boolean dispatchGenericMotionEvent(MotionEvent event);
        boolean dispatchPopulateAccessibilityEvent(Object event);
        View onCreatePanelView(int featureId);
        boolean onCreatePanelMenu(int featureId, Object menu);
        boolean onPreparePanel(int featureId, View view, Object menu);
        boolean onMenuOpened(int featureId, Object menu);
        boolean onMenuItemSelected(int featureId, Object item);
        void onWindowAttributesChanged(WindowManager.LayoutParams attrs);
        void onContentChanged();
        void onWindowFocusChanged(boolean hasFocus);
        void onAttachedToWindow();
        void onDetachedFromWindow();
        void onPanelClosed(int featureId, Object menu);
        boolean onSearchRequested();
        boolean onSearchRequested(Object searchEvent);
        void onActionModeStarted(Object mode);
        void onActionModeFinished(Object mode);
    }
}
