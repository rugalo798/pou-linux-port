package android.content;

import android.os.Parcel;
import android.os.Parcelable;

/** IntentSender stub. */
public class IntentSender implements Parcelable {
    private final Intent intent;
    public IntentSender(Intent intent) { this.intent = intent; }
    public Intent getIntent() { return intent; }
    public void sendIntent(Context context, int code, Intent intent, OnFinished onFinished, android.os.Handler handler) {}
    public void sendIntent(Context context, int code, Intent intent, OnFinished onFinished, android.os.Handler handler, String requiredPermission) {}
    public String getCreatorPackage() { return "me.pou.app"; }
    public String getTargetPackage() { return "me.pou.app"; }
    public int getCreatorUid() { return 10000; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}

    public interface OnFinished { void onSendFinished(IntentSender sender, Intent intent, int resultCode, String resultData, android.os.Bundle resultExtras); }

    public static final Parcelable.Creator<IntentSender> CREATOR = new Parcelable.Creator<IntentSender>() {
        public IntentSender createFromParcel(Parcel source) { return new IntentSender(null); }
        public IntentSender[] newArray(int size) { return new IntentSender[size]; }
    };
}
