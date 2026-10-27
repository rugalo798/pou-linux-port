package android.app;

import android.os.Parcel;
import android.os.Parcelable;

/** Notification channel group. */
public class NotificationChannelGroup implements Parcelable {
    private final String id;
    private CharSequence name;
    private String description;

    public NotificationChannelGroup(String id, CharSequence name) { this.id = id; this.name = name; }
    public String getId() { return id; }
    public CharSequence getName() { return name; }
    public void setName(CharSequence name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isBlocked() { return false; }
    public java.util.List<NotificationChannel> getChannels() { return new java.util.ArrayList<>(); }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public String toString() { return "NotificationChannelGroup(" + id + ")"; }

    public static final Parcelable.Creator<NotificationChannelGroup> CREATOR = new Parcelable.Creator<NotificationChannelGroup>() {
        public NotificationChannelGroup createFromParcel(Parcel source) { return null; }
        public NotificationChannelGroup[] newArray(int size) { return new NotificationChannelGroup[size]; }
    };
}
