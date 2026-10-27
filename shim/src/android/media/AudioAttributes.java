package android.media;

import android.os.Parcel;
import android.os.Parcelable;

/** Audio attributes. */
public class AudioAttributes implements Parcelable {
    public static final int USAGE_UNKNOWN = 0;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_GAME = 14;
    public static final int USAGE_ASSISTANT = 16;
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_MOVIE = 3;
    public static final int CONTENT_TYPE_SONIFICATION = 4;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;
    public static final int FLAG_HW_AV_SYNC = 16;
    public static final int FLAG_LOW_LATENCY = 256;
    public static final int FLAG_DEEP_BUFFER = 512;

    private int usage = USAGE_UNKNOWN;
    private int contentType = CONTENT_TYPE_UNKNOWN;
    private int flags;

    public AudioAttributes() {}
    public int getUsage() { return usage; }
    public int getContentType() { return contentType; }
    public int getFlags() { return flags; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public String toString() { return "AudioAttributes(usage=" + usage + ")"; }

    public static class Builder {
        private final AudioAttributes a = new AudioAttributes();
        public Builder() {}
        public Builder setUsage(int usage) { a.usage = usage; return this; }
        public Builder setContentType(int contentType) { a.contentType = contentType; return this; }
        public Builder setFlags(int flags) { a.flags = flags; return this; }
        public Builder setLegacyStreamType(int streamType) { return this; }
        public Builder setCapturePreset(int preset) { return this; }
        public AudioAttributes build() { return a; }
    }

    public static final Parcelable.Creator<AudioAttributes> CREATOR = new Parcelable.Creator<AudioAttributes>() {
        public AudioAttributes createFromParcel(Parcel source) { return new AudioAttributes(); }
        public AudioAttributes[] newArray(int size) { return new AudioAttributes[size]; }
    };
}
