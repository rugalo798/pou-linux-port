package android.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/** RemoteViews stub. */
public class RemoteViews implements Parcelable {
    private String packageName;
    private int layoutId;

    public RemoteViews(String packageName, int layoutId) { this.packageName = packageName; this.layoutId = layoutId; }
    public RemoteViews(RemoteViews src) { if (src != null) { packageName = src.packageName; layoutId = src.layoutId; } }
    public RemoteViews(Parcel parcel) {}
    public String getPackage() { return packageName; }
    public int getLayoutId() { return layoutId; }
    public void setTextViewText(int viewId, CharSequence text) {}
    public void setTextViewTextSize(int viewId, int units, float size) {}
    public void setImageViewResource(int viewId, int srcId) {}
    public void setImageViewBitmap(int viewId, Bitmap bitmap) {}
    public void setImageViewUri(int viewId, Uri uri) {}
    public void setInt(int viewId, String methodName, int value) {}
    public void setLong(int viewId, String methodName, long value) {}
    public void setBoolean(int viewId, String methodName, boolean value) {}
    public void setCharSequence(int viewId, String methodName, CharSequence value) {}
    public void setString(int viewId, String methodName, String value) {}
    public void setBitmap(int viewId, String methodName, Bitmap value) {}
    public void setUri(int viewId, String methodName, Uri value) {}
    public void setBundle(int viewId, String methodName, android.os.Bundle value) {}
    public void setTextColor(int viewId, int color) {}
    public void setViewVisibility(int viewId, int visibility) {}
    public void setViewPadding(int viewId, int left, int top, int right, int bottom) {}
    public void setOnClickPendingIntent(int viewId, PendingIntent pendingIntent) {}
    public void setOnClickResponse(int viewId, Object response) {}
    public void setPendingIntentTemplate(int viewId, PendingIntent pendingIntentTemplate) {}
    public void setProgressBar(int viewId, int max, int progress, boolean indeterminate) {}
    public void setChronometer(int viewId, long base, String format, boolean started) {}
    public void setChronometerCountDown(int viewId, boolean countDown) {}
    public void removeAllViews(int viewId) {}
    public void addView(int viewId, RemoteViews nestedView) {}
    public void reapply(Context context, View parent) {}
    public void showNext(int viewId) {}
    public void showPrevious(int viewId) {}
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public static final Parcelable.Creator<RemoteViews> CREATOR = new Parcelable.Creator<RemoteViews>() {
        public RemoteViews createFromParcel(Parcel source) { return new RemoteViews(source); }
        public RemoteViews[] newArray(int size) { return new RemoteViews[size]; }
    };
}
