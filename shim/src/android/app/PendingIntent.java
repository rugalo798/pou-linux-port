package android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/** PendingIntent stub. */
public class PendingIntent implements Parcelable {
    public static final int FLAG_ONE_SHOT = 1 << 30;
    public static final int FLAG_NO_CREATE = 1 << 29;
    public static final int FLAG_CANCEL_CURRENT = 1 << 28;
    public static final int FLAG_UPDATE_CURRENT = 1 << 27;
    public static final int FLAG_IMMUTABLE = 1 << 26;
    public static final int FLAG_MUTABLE = 1 << 25;
    public static final int FLAG_ALLOW_UNSAFE_IMPLICIT_INTENT = 1 << 24;

    private final Intent intent;

    private PendingIntent(Intent intent) { this.intent = intent; }

    public static PendingIntent getActivity(Context context, int requestCode, Intent intent, int flags) { return new PendingIntent(intent); }
    public static PendingIntent getActivity(Context context, int requestCode, Intent intent, int flags, Bundle options) { return new PendingIntent(intent); }
    public static PendingIntent getActivities(Context context, int requestCode, Intent[] intents, int flags) { return new PendingIntent(intents != null && intents.length > 0 ? intents[0] : null); }
    public static PendingIntent getActivities(Context context, int requestCode, Intent[] intents, int flags, Bundle options) { return getActivities(context, requestCode, intents, flags); }
    public static PendingIntent getBroadcast(Context context, int requestCode, Intent intent, int flags) { return new PendingIntent(intent); }
    public static PendingIntent getService(Context context, int requestCode, Intent intent, int flags) { return new PendingIntent(intent); }
    public static PendingIntent getForegroundService(Context context, int requestCode, Intent intent, int flags) { return new PendingIntent(intent); }

    public void send() throws CanceledException {}
    public void send(int code) throws CanceledException {}
    public void send(int code, Intent intent) throws CanceledException {}
    public void send(Context context, int code, Intent intent) throws CanceledException {}
    public void send(int code, Intent intent, OnFinished onFinished, android.os.Handler handler) throws CanceledException {}
    public void send(Context context, int code, Intent intent, OnFinished onFinished, android.os.Handler handler) throws CanceledException {}
    public void send(Context context, int code, Intent intent, OnFinished onFinished, android.os.Handler handler, String requiredPermission) throws CanceledException {}
    public void cancel() {}
    public Intent getIntent() { return intent; }
    public String getCreatorPackage() { return "me.pou.app"; }
    public int getCreatorUid() { return 10000; }
    public boolean isActivity() { return false; }
    public boolean isBroadcast() { return false; }
    public boolean isService() { return false; }
    public boolean isForegroundService() { return false; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}

    public static class CanceledException extends Exception {
        public CanceledException() {}
        public CanceledException(String name) { super(name); }
        public CanceledException(Exception cause) { super(cause); }
    }

    public interface OnFinished {
        void onSendFinished(PendingIntent pendingIntent, Intent intent, int resultCode, String resultData, Bundle resultExtras);
    }

    public static final Parcelable.Creator<PendingIntent> CREATOR = new Parcelable.Creator<PendingIntent>() {
        public PendingIntent createFromParcel(Parcel source) { return new PendingIntent(null); }
        public PendingIntent[] newArray(int size) { return new PendingIntent[size]; }
    };
}
