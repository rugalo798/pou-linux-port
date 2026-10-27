package android.app;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/** Notification channel. */
public class NotificationChannel implements Parcelable {
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    private String id;
    private CharSequence name;
    private int importance;
    private String description;
    private String group;
    private boolean showBadge = true;
    private boolean bypassDnd;
    private int lockscreenVisibility;
    private Uri sound;
    private boolean lights;
    private int lightColor;
    private boolean vibration;
    private long[] vibrationPattern;

    public NotificationChannel(String id, CharSequence name, int importance) {
        this.id = id; this.name = name; this.importance = importance;
    }
    public String getId() { return id; }
    public CharSequence getName() { return name; }
    public void setName(CharSequence name) { this.name = name; }
    public int getImportance() { return importance; }
    public void setImportance(int importance) { this.importance = importance; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }
    public boolean canShowBadge() { return showBadge; }
    public void setShowBadge(boolean showBadge) { this.showBadge = showBadge; }
    public boolean canBypassDnd() { return bypassDnd; }
    public void setBypassDnd(boolean bypassDnd) { this.bypassDnd = bypassDnd; }
    public int getLockscreenVisibility() { return lockscreenVisibility; }
    public void setLockscreenVisibility(int lockscreenVisibility) { this.lockscreenVisibility = lockscreenVisibility; }
    public Uri getSound() { return sound; }
    public void setSound(Uri sound, android.media.AudioAttributes attributes) { this.sound = sound; }
    public boolean shouldShowLights() { return lights; }
    public void enableLights(boolean lights) { this.lights = lights; }
    public int getLightColor() { return lightColor; }
    public void setLightColor(int argb) { this.lightColor = argb; }
    public boolean shouldVibrate() { return vibration; }
    public void enableVibration(boolean vibration) { this.vibration = vibration; }
    public long[] getVibrationPattern() { return vibrationPattern; }
    public void setVibrationPattern(long[] vibrationPattern) { this.vibrationPattern = vibrationPattern; }
    public boolean isBlockable() { return true; }
    public void setBlockable(boolean blockable) {}
    public boolean canBubble() { return false; }
    public void setAllowBubbles(boolean allowBubbles) {}
    public String getConversationId() { return null; }
    public void setConversationId(String conversationId, String parentChannelId) {}
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public String toString() { return "NotificationChannel(" + id + ")"; }

    public static final Parcelable.Creator<NotificationChannel> CREATOR = new Parcelable.Creator<NotificationChannel>() {
        public NotificationChannel createFromParcel(Parcel source) { return null; }
        public NotificationChannel[] newArray(int size) { return new NotificationChannel[size]; }
    };
}
