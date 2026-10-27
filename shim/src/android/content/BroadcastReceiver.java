package android.content;

import android.os.Bundle;

/** BroadcastReceiver stub. */
public abstract class BroadcastReceiver {
    public BroadcastReceiver() {}

    public void onReceive(Context context, Intent intent) {}

    public final boolean isInitialStickyBroadcast() { return false; }
    public final boolean isOrderedBroadcast() { return false; }
    public final void setResultCode(int code) {}
    public final int getResultCode() { return 0; }
    public final void setResultData(String data) {}
    public final String getResultData() { return null; }
    public final void setResultExtras(Bundle extras) {}
    public final Bundle getResultExtras(boolean makeMap) { return null; }
    public final void setResult(int code, String data, Bundle extras) {}
    public final boolean getAbortBroadcast() { return false; }
    public final void abortBroadcast() {}
    public final void clearAbortBroadcast() {}
    public final void setOrderedHint(boolean isOrdered) {}
    public final void setDebugUnregister(boolean debug) {}
    public final void setPendingResult(PendingResult result) {}
    public final PendingResult getPendingResult() { return null; }
    public final PendingResult goAsync() { return new PendingResult(); }

    public static class PendingResult {
        public final void setResultCode(int code) {}
        public final int getResultCode() { return 0; }
        public final void setResultData(String data) {}
        public final String getResultData() { return null; }
        public final void setResultExtras(Bundle extras) {}
        public final Bundle getResultExtras(boolean makeMap) { return null; }
        public final void setResult(int code, String data, Bundle extras) {}
        public final boolean getAbortBroadcast() { return false; }
        public final void abortBroadcast() {}
        public final void clearAbortBroadcast() {}
        public final void finish() {}
    }
}
