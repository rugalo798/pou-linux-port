package android.os;

/** Messenger stub. */
public final class Messenger implements Parcelable {
    private final Handler handler;
    public Messenger(Handler target) { this.handler = target; }
    public void send(Message message) throws RemoteException { if (handler != null) handler.sendMessage(message); }
    public IBinder getBinder() { return null; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
}
