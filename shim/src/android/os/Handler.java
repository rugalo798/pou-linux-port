package android.os;

import javax.swing.SwingUtilities;

/** Handler posting to the Swing event dispatch thread. */
public class Handler {
    public interface Callback { boolean handleMessage(Message msg); }

    private final Callback callback;
    private final Looper looper;

    public Handler() { this(Looper.getMainLooper(), null); }
    public Handler(Callback callback) { this(Looper.getMainLooper(), callback); }
    public Handler(Looper looper) { this(looper, null); }
    public Handler(Looper looper, Callback callback) { this.looper = looper; this.callback = callback; }

    public void handleMessage(Message msg) {}
    public void dispatchMessage(Message msg) {
        if (msg.callback != null) msg.callback.run();
        else if (callback != null) { if (!callback.handleMessage(msg)) handleMessage(msg); }
        else handleMessage(msg);
    }
    public final boolean post(Runnable r) { SwingUtilities.invokeLater(r); return true; }
    public final boolean postDelayed(Runnable r, long delayMillis) { postDelayed(r, null, delayMillis); return true; }
    public final boolean postDelayed(Runnable r, Object token, long delayMillis) {
        javax.swing.Timer timer = new javax.swing.Timer((int) Math.max(1, delayMillis), e -> r.run());
        timer.setRepeats(false);
        timer.start();
        return true;
    }
    public final boolean postAtTime(Runnable r, long uptimeMillis) { return post(r); }
    public final boolean postAtTime(Runnable r, Object token, long uptimeMillis) { return post(r); }
    public final boolean postAtFrontOfQueue(Runnable r) { return post(r); }
    public final void removeCallbacks(Runnable r) {}
    public final void removeCallbacks(Runnable r, Object token) {}
    public final void removeCallbacksAndMessages(Object token) {}
    public final boolean sendMessage(Message msg) { SwingUtilities.invokeLater(() -> dispatchMessage(msg)); return true; }
    public final boolean sendEmptyMessage(int what) { Message m = Message.obtain(this, what); return sendMessage(m); }
    public final boolean sendEmptyMessageDelayed(int what, long delayMillis) {
        Message m = Message.obtain(this, what);
        return sendMessageDelayed(m, delayMillis);
    }
    public final boolean sendEmptyMessageAtTime(int what, long uptimeMillis) { return sendEmptyMessage(what); }
    public final boolean sendMessageDelayed(Message msg, long delayMillis) {
        javax.swing.Timer timer = new javax.swing.Timer((int) Math.max(1, delayMillis), e -> dispatchMessage(msg));
        timer.setRepeats(false);
        timer.start();
        return true;
    }
    public final boolean sendMessageAtTime(Message msg, long uptimeMillis) { return sendMessage(msg); }
    public final boolean sendMessageAtFrontOfQueue(Message msg) { return sendMessage(msg); }
    public final Message obtainMessage() { return Message.obtain(this); }
    public final Message obtainMessage(int what) { return Message.obtain(this, what); }
    public final Message obtainMessage(int what, Object obj) { return Message.obtain(this, what, obj); }
    public final Message obtainMessage(int what, int arg1, int arg2) { return Message.obtain(this, what, arg1, arg2); }
    public final Message obtainMessage(int what, int arg1, int arg2, Object obj) { return Message.obtain(this, what, arg1, arg2, obj); }
    public final Looper getLooper() { return looper != null ? looper : Looper.getMainLooper(); }
    public final String getMessageName(Message message) { return null; }
    public final void dump(Object printer, String prefix) {}
    public static Handler createAsync(Looper looper) { return new Handler(looper); }
    public String toString() { return "Handler(" + getLooper() + ")"; }
}
