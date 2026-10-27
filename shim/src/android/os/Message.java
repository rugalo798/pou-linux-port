package android.os;

/** Message. */
public final class Message implements Parcelable {
    public int what;
    public int arg1;
    public int arg2;
    public Object obj;
    public Messenger replyTo;
    public int sendingUid = -1;
    public int flags;
    public long when;
    public Bundle data;
    public Handler target;
    public Runnable callback;

    public Message() {}
    public static Message obtain() { return new Message(); }
    public static Message obtain(Message orig) {
        Message m = new Message();
        m.what = orig.what; m.arg1 = orig.arg1; m.arg2 = orig.arg2; m.obj = orig.obj;
        m.replyTo = orig.replyTo; m.flags = orig.flags; m.when = orig.when; m.data = orig.data;
        return m;
    }
    public static Message obtain(Handler h) { Message m = new Message(); m.target = h; return m; }
    public static Message obtain(Handler h, int what) { Message m = obtain(h); m.what = what; return m; }
    public static Message obtain(Handler h, int what, Object obj) { Message m = obtain(h, what); m.obj = obj; return m; }
    public static Message obtain(Handler h, int what, int arg1, int arg2) { Message m = obtain(h, what); m.arg1 = arg1; m.arg2 = arg2; return m; }
    public static Message obtain(Handler h, int what, int arg1, int arg2, Object obj) { Message m = obtain(h, what, arg1, arg2); m.obj = obj; return m; }
    public static Message obtain(Handler h, Runnable callback) { Message m = obtain(h); m.callback = callback; return m; }
    public void recycle() {}
    public void copyFrom(Message o) {
        what = o.what; arg1 = o.arg1; arg2 = o.arg2; obj = o.obj; replyTo = o.replyTo;
        flags = o.flags; when = o.when; data = o.data; target = o.target; callback = o.callback;
    }
    public long getWhen() { return when; }
    public void setTarget(Handler target) { this.target = target; }
    public Handler getTarget() { return target; }
    public Bundle getData() { if (data == null) data = new Bundle(); return data; }
    public Bundle peekData() { return data; }
    public void setData(Bundle data) { this.data = data; }
    public void sendToTarget() { if (target != null) target.sendMessage(this); }
    public boolean isAsynchronous() { return false; }
    public void setAsynchronous(boolean async) {}
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public String toString() { return "Message(what=" + what + ")"; }
}
