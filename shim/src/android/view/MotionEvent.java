package android.view;

import android.graphics.Matrix;

/** MotionEvent with a single pointer (mouse). */
public final class MotionEvent {
    public static final int ACTION_DOWN = 0;
    public static final int ACTION_UP = 1;
    public static final int ACTION_MOVE = 2;
    public static final int ACTION_CANCEL = 3;
    public static final int ACTION_OUTSIDE = 4;
    public static final int ACTION_POINTER_DOWN = 5;
    public static final int ACTION_POINTER_UP = 6;
    public static final int ACTION_HOVER_MOVE = 7;
    public static final int ACTION_SCROLL = 8;
    public static final int ACTION_HOVER_ENTER = 9;
    public static final int ACTION_HOVER_EXIT = 10;
    public static final int ACTION_BUTTON_PRESS = 11;
    public static final int ACTION_BUTTON_RELEASE = 12;
    public static final int ACTION_MASK = 0xff;
    public static final int ACTION_POINTER_INDEX_MASK = 0xff00;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    public static final int TOOL_TYPE_UNKNOWN = 0;
    public static final int TOOL_TYPE_FINGER = 1;
    public static final int TOOL_TYPE_STYLUS = 2;
    public static final int TOOL_TYPE_MOUSE = 3;
    public static final int TOOL_TYPE_ERASER = 4;
    public static final int SOURCE_TOUCHSCREEN = 0x1002;
    public static final int SOURCE_MOUSE = 0x2002;
    public static final int BUTTON_PRIMARY = 1;

    private long downTime, eventTime;
    private int action;
    private int pointerCount = 1;
    private final float[] x = new float[8];
    private final float[] y = new float[8];
    private final int[] pointerIds = new int[8];
    private int metaState, source = SOURCE_TOUCHSCREEN, deviceId, flags, edgeFlags;

    private MotionEvent() {}

    public static MotionEvent obtain(long downTime, long eventTime, int action, float x, float y, int metaState) {
        MotionEvent e = new MotionEvent();
        e.downTime = downTime; e.eventTime = eventTime; e.action = action;
        e.x[0] = x; e.y[0] = y; e.pointerIds[0] = 0; e.metaState = metaState;
        return e;
    }
    public static MotionEvent obtain(long downTime, long eventTime, int action, int pointerCount, int[] pointerIds, float[] x, float[] y, int metaState, float xOffset, float yOffset, int source, int flags) {
        MotionEvent e = new MotionEvent();
        e.downTime = downTime; e.eventTime = eventTime; e.action = action;
        e.pointerCount = Math.min(pointerCount, 8);
        for (int i = 0; i < e.pointerCount; i++) {
            e.pointerIds[i] = pointerIds[i];
            e.x[i] = x[i] + xOffset;
            e.y[i] = y[i] + yOffset;
        }
        e.metaState = metaState; e.source = source; e.flags = flags;
        return e;
    }
    public static MotionEvent obtain(MotionEvent other) {
        MotionEvent e = new MotionEvent();
        e.downTime = other.downTime; e.eventTime = other.eventTime; e.action = other.action;
        e.pointerCount = other.pointerCount;
        System.arraycopy(other.x, 0, e.x, 0, 8);
        System.arraycopy(other.y, 0, e.y, 0, 8);
        System.arraycopy(other.pointerIds, 0, e.pointerIds, 0, 8);
        e.metaState = other.metaState; e.source = other.source; e.flags = other.flags;
        return e;
    }
    public static MotionEvent obtainNoHistory(MotionEvent other) { return obtain(other); }
    public void recycle() {}

    public int getAction() { return action; }
    public int getActionMasked() { return action & ACTION_MASK; }
    public int getActionIndex() { return (action & ACTION_POINTER_INDEX_MASK) >> ACTION_POINTER_INDEX_SHIFT; }
    public void setAction(int action) { this.action = action; }
    public float getX() { return x[0]; }
    public float getY() { return y[0]; }
    public float getX(int pointerIndex) { return pointerIndex >= 0 && pointerIndex < pointerCount ? x[pointerIndex] : 0; }
    public float getY(int pointerIndex) { return pointerIndex >= 0 && pointerIndex < pointerCount ? y[pointerIndex] : 0; }
    public float getRawX() { return x[0]; }
    public float getRawY() { return y[0]; }
    public int getPointerCount() { return pointerCount; }
    public int getPointerId(int pointerIndex) { return pointerIndex >= 0 && pointerIndex < pointerCount ? pointerIds[pointerIndex] : -1; }
    public int findPointerIndex(int pointerId) {
        for (int i = 0; i < pointerCount; i++) if (pointerIds[i] == pointerId) return i;
        return -1;
    }
    public long getEventTime() { return eventTime; }
    public long getDownTime() { return downTime; }
    public int getMetaState() { return metaState; }
    public int getSource() { return source; }
    public int getToolType(int pointerIndex) { return TOOL_TYPE_FINGER; }
    public float getPressure() { return 1f; }
    public float getPressure(int pointerIndex) { return 1f; }
    public float getSize() { return 1f; }
    public int getHistorySize() { return 0; }
    public float getHistoricalX(int pointerIndex, int pos) { return getX(pointerIndex); }
    public float getHistoricalY(int pointerIndex, int pos) { return getY(pointerIndex); }
    public float getHistoricalX(int pos) { return getX(); }
    public float getHistoricalY(int pos) { return getY(); }
    public int getDeviceId() { return deviceId; }
    public int getEdgeFlags() { return edgeFlags; }
    public int getFlags() { return flags; }
    public int getButtonState() { return 0; }
    public int getActionButton() { return 0; }
    public float getAxisValue(int axis) { return 0; }
    public boolean isTouchEvent() { return true; }
    public boolean isHoverEvent() { return false; }
    public void setLocation(float x, float y) { this.x[0] = x; this.y[0] = y; }
    public void offsetLocation(float dx, float dy) {
        for (int i = 0; i < pointerCount; i++) { x[i] += dx; y[i] += dy; }
    }
    public void transform(Matrix matrix) {
        float[] pts = new float[pointerCount * 2];
        for (int i = 0; i < pointerCount; i++) { pts[i * 2] = x[i]; pts[i * 2 + 1] = y[i]; }
        matrix.mapPoints(pts);
        for (int i = 0; i < pointerCount; i++) { x[i] = pts[i * 2]; y[i] = pts[i * 2 + 1]; }
    }
    public void setSource(int source) { this.source = source; }
    public String toString() { return "MotionEvent(action=" + action + ", x=" + x[0] + ", y=" + y[0] + ")"; }
}
