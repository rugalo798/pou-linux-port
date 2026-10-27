package android.view;

/** Minimal GestureDetector for taps and drags. */
public class GestureDetector {
    public interface OnGestureListener {
        boolean onDown(MotionEvent e);
        void onShowPress(MotionEvent e);
        boolean onSingleTapUp(MotionEvent e);
        boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);
        void onLongPress(MotionEvent e);
        boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
    }
    public interface OnDoubleTapListener {
        boolean onSingleTapConfirmed(MotionEvent e);
        boolean onDoubleTap(MotionEvent e);
        boolean onDoubleTapEvent(MotionEvent e);
    }
    public static class SimpleOnGestureListener implements OnGestureListener, OnDoubleTapListener {
        public boolean onDown(MotionEvent e) { return false; }
        public void onShowPress(MotionEvent e) {}
        public boolean onSingleTapUp(MotionEvent e) { return false; }
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return false; }
        public void onLongPress(MotionEvent e) {}
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { return false; }
        public boolean onSingleTapConfirmed(MotionEvent e) { return false; }
        public boolean onDoubleTap(MotionEvent e) { return false; }
        public boolean onDoubleTapEvent(MotionEvent e) { return false; }
    }

    private final OnGestureListener listener;
    private OnDoubleTapListener doubleTapListener;
    private float downX, downY, lastX, lastY;
    private long downTime;
    private boolean scrolling;

    public GestureDetector(android.content.Context context, OnGestureListener listener) { this.listener = listener; }
    public GestureDetector(android.content.Context context, OnGestureListener listener, android.os.Handler handler) { this.listener = listener; }
    public void setOnDoubleTapListener(OnDoubleTapListener listener) { this.doubleTapListener = listener; }
    public void setIsLongpressEnabled(boolean enabled) {}
    public boolean isLongpressEnabled() { return false; }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downX = lastX = ev.getX();
                downY = lastY = ev.getY();
                downTime = ev.getEventTime();
                scrolling = false;
                return listener.onDown(ev);
            case MotionEvent.ACTION_MOVE: {
                float dx = lastX - ev.getX();
                float dy = lastY - ev.getY();
                if (!scrolling && (Math.abs(ev.getX() - downX) > 8 || Math.abs(ev.getY() - downY) > 8)) scrolling = true;
                if (scrolling) listener.onScroll(null, ev, dx, dy);
                lastX = ev.getX();
                lastY = ev.getY();
                return true;
            }
            case MotionEvent.ACTION_UP:
                if (!scrolling) {
                    listener.onSingleTapUp(ev);
                    if (doubleTapListener != null) doubleTapListener.onSingleTapConfirmed(ev);
                } else {
                    listener.onFling(null, ev, 0, 0);
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
                return true;
            default:
                return false;
        }
    }
}
