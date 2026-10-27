package android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/** Minimal View hosted in a Swing JComponent. */
public class View {
    public static final int VISIBLE = 0;
    public static final int INVISIBLE = 4;
    public static final int GONE = 8;
    public static final int NO_ID = -1;
    public static final int SYSTEM_UI_FLAG_VISIBLE = 0;
    public static final int SYSTEM_UI_FLAG_LOW_PROFILE = 1;
    public static final int SYSTEM_UI_FLAG_HIDE_NAVIGATION = 2;
    public static final int SYSTEM_UI_FLAG_FULLSCREEN = 4;
    public static final int SYSTEM_UI_FLAG_LAYOUT_STABLE = 256;
    public static final int SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION = 512;
    public static final int SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN = 1024;
    public static final int SYSTEM_UI_FLAG_IMMERSIVE = 2048;
    public static final int SYSTEM_UI_FLAG_IMMERSIVE_STICKY = 4096;

    protected Context context;
    private JComponent component;
    private ViewParent parent;
    private LayoutParams layoutParams;
    private int width, height, measuredWidth, measuredHeight;
    private int visibility = VISIBLE;
    private int backgroundColor;
    private Drawable background;
    private boolean clickable, enabled = true, attached, selected;
    private OnClickListener onClickListener;
    private OnTouchListener onTouchListener;
    private OnLongClickListener onLongClickListener;
    private Object tag;
    private float alpha = 1f, translationX, translationY, scaleX = 1f, scaleY = 1f, rotation, elevation;
    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private int id = NO_ID;
    private boolean keepScreenOn;
    private int systemUiVisibility;

    public View(Context context) { this.context = context; }

    public Context getContext() { return context; }
    public Resources getResources() { return context != null ? context.getResources() : null; }
    public ViewParent getParent() { return parent; }
    public void setParent(ViewParent parent) { this.parent = parent; }
    public LayoutParams getLayoutParams() { return layoutParams; }
    public void setLayoutParams(LayoutParams params) { layoutParams = params; if (parent != null) parent.requestLayout(); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Object getTag() { return tag; }
    public void setTag(Object tag) { this.tag = tag; }
    public Object getTag(int key) { return tag; }
    public void setTag(int key, Object tag) { this.tag = tag; }

    public int getWidth() { return component != null ? component.getWidth() : width; }
    public int getHeight() { return component != null ? component.getHeight() : height; }
    public int getMeasuredWidth() { return measuredWidth; }
    public int getMeasuredHeight() { return measuredHeight; }
    public void measure(int widthMeasureSpec, int heightMeasureSpec) {}
    public void layout(int l, int t, int r, int b) { width = r - l; height = b - t; if (component != null) component.setBounds(l, t, width, height); }
    public void getDrawingRect(android.graphics.Rect outRect) { outRect.set(0, 0, getWidth(), getHeight()); }
    public void getLocationOnScreen(int[] outLocation) {
        outLocation[0] = 0; outLocation[1] = 0;
        if (component != null) {
            try { java.awt.Point p = component.getLocationOnScreen(); outLocation[0] = p.x; outLocation[1] = p.y; } catch (Exception ignored) {}
        }
    }
    public void getLocationInWindow(int[] outLocation) { getLocationOnScreen(outLocation); }

    public void setBackgroundColor(int color) {
        backgroundColor = color;
        background = new ColorDrawable(color);
        if (component != null) { component.setOpaque((color >>> 24) == 0xFF); component.repaint(); }
    }
    public void setBackground(Drawable drawable) { background = drawable; if (component != null) component.repaint(); }
    public void setBackgroundResource(int resid) {}
    public Drawable getBackground() { return background; }
    public void setBackgroundDrawable(Drawable drawable) { setBackground(drawable); }

    public int getVisibility() { return visibility; }
    public void setVisibility(int visibility) {
        this.visibility = visibility;
        if (component != null) component.setVisible(visibility == VISIBLE);
    }
    public boolean isShown() { return visibility == VISIBLE; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }
    public boolean isClickable() { return clickable; }
    public void setClickable(boolean clickable) { this.clickable = clickable; }
    public void setFocusable(boolean focusable) {}
    public void setFocusableInTouchMode(boolean focusable) {}
    public void setFocusable(int focusable) {}
    public void requestFocus() {}
    public boolean requestFocus(int direction, android.graphics.Rect previouslyFocusedRect) { return false; }
    public void setOnClickListener(OnClickListener l) { onClickListener = l; clickable = true; }
    public OnClickListener getOnClickListener() { return onClickListener; }
    public void setOnTouchListener(OnTouchListener l) { onTouchListener = l; }
    public void setOnLongClickListener(OnLongClickListener l) { onLongClickListener = l; }
    public boolean performClick() { if (onClickListener != null) { onClickListener.onClick(this); return true; } return false; }
    public boolean callOnClick() { return performClick(); }
    public void setSoundEffectsEnabled(boolean enabled) {}
    public void setHapticFeedbackEnabled(boolean enabled) {}
    public boolean performHapticFeedback(int feedbackConstant) { return false; }
    public void setKeepScreenOn(boolean keepScreenOn) { this.keepScreenOn = keepScreenOn; }
    public void setSystemUiVisibility(int visibility) { systemUiVisibility = visibility; }
    public int getSystemUiVisibility() { return systemUiVisibility; }
    public void setContentDescription(CharSequence contentDescription) {}
    public CharSequence getContentDescription() { return null; }
    public void setAccessibilityDelegate(Object delegate) {}
    public void setImportantForAccessibility(int mode) {}
    public void setLayerType(int layerType, android.graphics.Paint paint) {}
    public void setWillNotDraw(boolean willNotDraw) {}
    public void setDrawingCacheEnabled(boolean enabled) {}
    public void setAnimation(Animation animation) {}
    public void startAnimation(Animation animation) {}
    public void clearAnimation() {}
    public Animation getAnimation() { return null; }
    public void setMinimumWidth(int minWidth) {}
    public void setMinimumHeight(int minHeight) {}
    public void setPadding(int left, int top, int right, int bottom) {
        paddingLeft = left; paddingTop = top; paddingRight = right; paddingBottom = bottom;
    }
    public void setPaddingRelative(int start, int top, int end, int bottom) { setPadding(start, top, end, bottom); }
    public int getPaddingLeft() { return paddingLeft; }
    public int getPaddingTop() { return paddingTop; }
    public int getPaddingRight() { return paddingRight; }
    public int getPaddingBottom() { return paddingBottom; }
    public int getPaddingStart() { return paddingLeft; }
    public int getPaddingEnd() { return paddingRight; }
    public void setAlpha(float alpha) { this.alpha = alpha; if (component != null) component.setOpaque(alpha >= 1f && backgroundColor != 0); }
    public float getAlpha() { return alpha; }
    public void setTranslationX(float translationX) { this.translationX = translationX; }
    public float getTranslationX() { return translationX; }
    public void setTranslationY(float translationY) { this.translationY = translationY; }
    public float getTranslationY() { return translationY; }
    public void setTranslationZ(float translationZ) {}
    public void setScaleX(float scaleX) { this.scaleX = scaleX; }
    public float getScaleX() { return scaleX; }
    public void setScaleY(float scaleY) { this.scaleY = scaleY; }
    public float getScaleY() { return scaleY; }
    public void setRotation(float rotation) { this.rotation = rotation; }
    public float getRotation() { return rotation; }
    public void setRotationX(float rotationX) {}
    public void setRotationY(float rotationY) {}
    public void setPivotX(float pivotX) {}
    public void setPivotY(float pivotY) {}
    public void setElevation(float elevation) { this.elevation = elevation; }
    public float getElevation() { return elevation; }
    public void setZ(float z) {}
    public void setOutlineProvider(Object provider) {}
    public void setClipToOutline(boolean clipToOutline) {}
    public void setTransitionName(String transitionName) {}
    public void setNestedScrollingEnabled(boolean enabled) {}
    public boolean isNestedScrollingEnabled() { return false; }
    public void setOverScrollMode(int overScrollMode) {}
    public void setScrollBarStyle(int style) {}
    public void setVerticalScrollBarEnabled(boolean enabled) {}
    public void setHorizontalScrollBarEnabled(boolean enabled) {}
    public void setFitsSystemWindows(boolean fitSystemWindows) {}
    public boolean getFitsSystemWindows() { return false; }
    public void setLayoutDirection(int layoutDirection) {}
    public int getLayoutDirection() { return 0; }
    public void setTextDirection(int textDirection) {}
    public void setTextAlignment(int textAlignment) {}
    public ViewPropertyAnimator animate() { return new ViewPropertyAnimator(this); }
    public boolean post(Runnable action) { SwingUtilities.invokeLater(action); return true; }
    public boolean postDelayed(Runnable action, long delayMillis) { new javax.swing.Timer((int) Math.max(1, delayMillis), e -> action.run()) {{ setRepeats(false); start(); }}; return true; }
    public void postOnAnimation(Runnable action) { SwingUtilities.invokeLater(action); }
    public void postOnAnimationDelayed(Runnable action, long delayMillis) { postDelayed(action, delayMillis); }
    public void removeCallbacks(Runnable action) {}
    public void invalidate() { if (component != null) component.repaint(); }
    public void postInvalidate() { if (component != null) SwingUtilities.invokeLater(component::repaint); }
    public void postInvalidateDelayed(long delayMilliseconds) { postDelayed(this::invalidate, delayMilliseconds); }
    public void invalidate(int l, int t, int r, int b) { invalidate(); }
    public void requestLayout() { if (parent != null) parent.requestLayout(); }
    public void forceLayout() {}
    public boolean isAttachedToWindow() { return attached; }
    public void onAttachedToWindow() { attached = true; }
    public void onDetachedFromWindow() { attached = false; }
    public android.os.IBinder getWindowToken() { return null; }
    public View getRootView() { return this; }
    public void setOnHierarchyChangeListener(Object listener) {}
    public boolean onTouchEvent(MotionEvent event) {
        if (clickable && event.getActionMasked() == MotionEvent.ACTION_UP) return performClick();
        return clickable;
    }
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (onTouchListener != null && onTouchListener.onTouch(this, event)) return true;
        return onTouchEvent(event);
    }
    public boolean onGenericMotionEvent(MotionEvent event) { return false; }
    public boolean dispatchGenericMotionEvent(MotionEvent event) { return onGenericMotionEvent(event); }
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) { return null; }
    public boolean onCheckIsTextEditor() { return false; }
    public void onDraw(Canvas canvas) {}
    public void draw(Canvas canvas) { onDraw(canvas); }
    public void drawableHotspotChanged(float x, float y) {}
    public void setSaveEnabled(boolean enabled) {}
    public void setFilterTouchesWhenObscured(boolean enabled) {}
    public void setOnApplyWindowInsetsListener(Object listener) {}
    public void setOnScrollChangeListener(Object listener) {}
    public void setOnFocusChangeListener(Object listener) {}
    public void setOnKeyListener(Object listener) {}
    public void setOnGenericMotionListener(Object listener) {}
    public void setOnDragListener(Object listener) {}
    public void setOnCreateContextMenuListener(Object listener) {}
    public void setOnContextClickListener(Object listener) {}
    public void setOnCapturedPointerListener(Object listener) {}
    public void setOnHoverListener(Object listener) {}

    /** The Swing component backing this view. */
    public JComponent getComponent() {
        if (component == null) {
            component = new JPanel() {
                @Override protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g.create();
                    Canvas canvas = new Canvas(g2, getWidth(), getHeight());
                    if (background != null) {
                        background.setBounds(0, 0, getWidth(), getHeight());
                        background.draw(canvas);
                    }
                    View.this.draw(canvas);
                    g2.dispose();
                }
            };
            component.setOpaque(backgroundColor != 0 && (backgroundColor >>> 24) == 0xFF);
            if (backgroundColor != 0) component.setBackground(new java.awt.Color(backgroundColor, true));
            component.addMouseListener(new MouseAdapter() {
                @Override public void mousePressed(MouseEvent e) {
                    dispatchTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, e.getX(), e.getY(), 0));
                }
                @Override public void mouseReleased(MouseEvent e) {
                    dispatchTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_UP, e.getX(), e.getY(), 0));
                }
                @Override public void mouseClicked(MouseEvent e) {}
            });
            component.addMouseMotionListener(new MouseMotionAdapter() {
                @Override public void mouseDragged(MouseEvent e) {
                    dispatchTouchEvent(MotionEvent.obtain(0, 0, MotionEvent.ACTION_MOVE, e.getX(), e.getY(), 0));
                }
            });
        }
        return component;
    }

    public interface OnClickListener { void onClick(View v); }
    public interface OnLongClickListener { boolean onLongClick(View v); }
    public interface OnTouchListener { boolean onTouch(View v, MotionEvent event); }
}
