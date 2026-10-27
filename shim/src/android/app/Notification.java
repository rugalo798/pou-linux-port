package android.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RemoteViews;

import java.util.ArrayList;

/** Notification stub with a Builder. */
public class Notification implements Parcelable {
    public static final int PRIORITY_MIN = -2;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MAX = 2;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_SECRET = -1;
    public static final int BUBBLE_METADATA_NONE = 0;
    public static final int BUBBLE_METADATA_IMPORTANT = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final int BADGE_ICON_LARGE = 2;
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_LOCATION = "location";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final String CATEGORY_NAVIGATION = "navigation";
    public static final String CATEGORY_STOPWATCH = "stopwatch";
    public static final String CATEGORY_WORKOUT = "workout";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_TEMPLATE = "android.template";

    public int icon;
    public int iconLevel;
    public int number;
    public long when;
    public int priority;
    public int flags;
    public int defaults;
    public int ledARGB;
    public int ledOnMS;
    public int ledOffMS;
    public CharSequence tickerText;
    public RemoteViews contentView;
    public RemoteViews bigContentView;
    public RemoteViews headsUpContentView;
    public Intent contentIntent;
    public Intent deleteIntent;
    public Intent fullScreenIntent;
    public CharSequence category;
    public int color;
    public int visibility;
    public String group;
    public String sortKey;
    public String channelId;
    public Bundle extras;
    public ArrayList<Action> actions;
    public String mGroupKey;
    public String mSortKey;
    public String mChannelId;
    public int mGroupAlertBehavior;
    public int mBadgeIcon;
    public CharSequence mSettingsText;
    public String mShortcutId;
    public long mTimeout;
    public boolean mColorized;
    public boolean mColorizedSet;
    public boolean mAllowSystemGeneratedContextualActions;
    public Object mBubbleMetadata;
    public int mFgsDeferBehavior;
    public boolean mRequestPromotedOngoing;
    public Object mLocusId;

    public Notification() {}
    public Notification(int icon, CharSequence tickerText, long when) {
        this.icon = icon; this.tickerText = tickerText; this.when = when;
    }
    public Notification(Parcel parcel) {}

    public String getChannelId() { return channelId; }
    public String getGroup() { return group; }
    public String getSortKey() { return sortKey; }
    public long getWhen() { return when; }
    public int getPriority() { return priority; }
    public int getNumber() { return number; }
    public int getColor() { return color; }
    public int getVisibility() { return visibility; }
    public Bundle getExtras() { return extras; }
    public ArrayList<Action> getActions() { return actions; }
    public String getShortcutId() { return mShortcutId; }
    public int getBadgeIconType() { return mBadgeIcon; }
    public long getTimeoutAfter() { return mTimeout; }
    public boolean isColorized() { return mColorized; }
    public boolean getAllowSystemGeneratedContextualActions() { return mAllowSystemGeneratedContextualActions; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}

    public static class Action implements Parcelable {
        public int icon;
        public CharSequence title;
        public Intent intent;
        public Bundle extras;
        public RemoteViews remoteInputs;
        public boolean allowGeneratedReplies;
        public Action(int icon, CharSequence title, Intent intent) {
            this.icon = icon; this.title = title; this.intent = intent;
        }
        public int describeContents() { return 0; }
        public void writeToParcel(Parcel dest, int flags) {}
        public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {
            public Action createFromParcel(Parcel source) { return null; }
            public Action[] newArray(int size) { return new Action[size]; }
        };
    }

    public static class Builder {
        private final Notification n = new Notification();
        private final Bundle extras = new Bundle();
        private final ArrayList<Action> actions = new ArrayList<>();
        private Context context;

        public Builder(Context context) { this.context = context; n.extras = extras; n.actions = actions; }
        public Builder(Context context, Notification toAdopt) { this(context); }
        public Builder(Context context, String channelId) { this(context); n.channelId = channelId; }
        public Builder setSmallIcon(int icon) { n.icon = icon; return this; }
        public Builder setSmallIcon(int icon, int level) { n.icon = icon; n.iconLevel = level; return this; }
        public Builder setLargeIcon(Bitmap icon) { return this; }
        public Builder setLargeIcon(Icon icon) { return this; }
        public Builder setContentTitle(CharSequence title) { extras.putCharSequence(EXTRA_TITLE, title); return this; }
        public Builder setContentText(CharSequence text) { extras.putCharSequence(EXTRA_TEXT, text); return this; }
        public Builder setSubText(CharSequence text) { extras.putCharSequence(EXTRA_SUB_TEXT, text); return this; }
        public Builder setContentInfo(CharSequence info) { extras.putCharSequence(EXTRA_INFO_TEXT, info); return this; }
        public Builder setContentIntent(Intent intent) { n.contentIntent = intent; return this; }
        public Builder setDeleteIntent(Intent intent) { n.deleteIntent = intent; return this; }
        public Builder setFullScreenIntent(Intent intent, boolean highPriority) { n.fullScreenIntent = intent; return this; }
        public Builder setTicker(CharSequence tickerText) { n.tickerText = tickerText; return this; }
        public Builder setTicker(CharSequence tickerText, RemoteViews views) { n.tickerText = tickerText; return this; }
        public Builder setWhen(long when) { n.when = when; return this; }
        public Builder setShowWhen(boolean show) { return this; }
        public Builder setUsesChronometer(boolean b) { return this; }
        public Builder setChronometerCountDown(boolean b) { return this; }
        public Builder setSmallIcon(Icon icon) { return this; }
        public Builder setAutoCancel(boolean autoCancel) { n.flags = autoCancel ? (n.flags | 16) : (n.flags & ~16); return this; }
        public Builder setOngoing(boolean ongoing) { n.flags = ongoing ? (n.flags | 2) : (n.flags & ~2); return this; }
        public Builder setOnlyAlertOnce(boolean onlyAlertOnce) { n.flags = onlyAlertOnce ? (n.flags | 8) : (n.flags & ~8); return this; }
        public Builder setLocalOnly(boolean localOnly) { return this; }
        public Builder setDefaults(int defaults) { n.defaults = defaults; return this; }
        public Builder setPriority(int priority) { n.priority = priority; return this; }
        public Builder setCategory(String category) { n.category = category; return this; }
        public Builder setColor(int argb) { n.color = argb; return this; }
        public Builder setVisibility(int visibility) { n.visibility = visibility; return this; }
        public Builder setPublicVersion(Notification n) { return this; }
        public Builder setSound(Uri sound) { return this; }
        public Builder setSound(Uri sound, AudioAttributes audioAttributes) { return this; }
        public Builder setVibrate(long[] pattern) { return this; }
        public Builder setLights(int argb, int onMs, int offMs) { n.ledARGB = argb; n.ledOnMS = onMs; n.ledOffMS = offMs; return this; }
        public Builder setNumber(int number) { n.number = number; return this; }
        public Builder setGroup(String groupKey) { n.group = groupKey; return this; }
        public Builder setGroupSummary(boolean isGroupSummary) { return this; }
        public Builder setSortKey(String sortKey) { n.sortKey = sortKey; return this; }
        public Builder setChannelId(String channelId) { n.channelId = channelId; return this; }
        public Builder setTimeoutAfter(long durationMs) { n.mTimeout = durationMs; return this; }
        public Builder setShortcutId(String shortcutId) { n.mShortcutId = shortcutId; return this; }
        public Builder setBadgeIconType(int icon) { n.mBadgeIcon = icon; return this; }
        public Builder setSettingsText(CharSequence text) { n.mSettingsText = text; return this; }
        public Builder setRemoteInputHistory(CharSequence[] text) { return this; }
        public Builder setProgress(int max, int progress, boolean indeterminate) {
            extras.putInt(EXTRA_PROGRESS_MAX, max);
            extras.putInt(EXTRA_PROGRESS, progress);
            extras.putBoolean(EXTRA_PROGRESS_INDETERMINATE, indeterminate);
            return this;
        }
        public Builder setStyle(Style style) { return this; }
        public Builder setBigTextStyle(BigTextStyle style) { return this; }
        public Builder setBigPictureStyle(BigPictureStyle style) { return this; }
        public Builder setInboxStyle(InboxStyle style) { return this; }
        public Builder setMessagingStyle(MessagingStyle style) { return this; }
        public Builder setCustomContentView(RemoteViews views) { n.contentView = views; return this; }
        public Builder setCustomBigContentView(RemoteViews views) { n.bigContentView = views; return this; }
        public Builder setCustomHeadsUpContentView(RemoteViews views) { n.headsUpContentView = views; return this; }
        public Builder addAction(int icon, CharSequence title, android.app.PendingIntent intent) { return this; }
        public Builder addAction(Action action) { actions.add(action); return this; }
        public Builder addExtras(Bundle extras) { if (extras != null) this.extras.putAll(extras); return this; }
        public Builder setExtras(Bundle extras) { if (extras != null) this.extras.putAll(extras); return this; }
        public Bundle getExtras() { return extras; }
        public Builder setContent(RemoteViews views) { n.contentView = views; return this; }
        public Builder setCustomContentView(RemoteViews views, boolean ignored) { n.contentView = views; return this; }
        public Builder setColorized(boolean colorize) { n.mColorized = colorize; return this; }
        public Builder setAllowSystemGeneratedContextualActions(boolean allowed) { n.mAllowSystemGeneratedContextualActions = allowed; return this; }
        public Builder setBubbleMetadata(Object metadata) { n.mBubbleMetadata = metadata; return this; }
        public Builder setLocusId(Object locusId) { n.mLocusId = locusId; return this; }
        public Builder setGroupAlertBehavior(int groupAlertBehavior) { n.mGroupAlertBehavior = groupAlertBehavior; return this; }
        public Builder setForegroundServiceBehavior(int behavior) { return this; }
        public Notification build() { return n; }
        public Notification getNotification() { return n; }
    }

    public static class Style {
        public Style() {}
        public Notification build() { return null; }
    }
    public static class BigTextStyle extends Style {
        public BigTextStyle() {}
        public BigTextStyle setBigContentTitle(CharSequence title) { return this; }
        public BigTextStyle setSummaryText(CharSequence cs) { return this; }
        public BigTextStyle bigText(CharSequence cs) { return this; }
    }
    public static class BigPictureStyle extends Style {
        public BigPictureStyle() {}
        public BigPictureStyle setBigContentTitle(CharSequence title) { return this; }
        public BigPictureStyle setSummaryText(CharSequence cs) { return this; }
        public BigPictureStyle bigPicture(Bitmap b) { return this; }
        public BigPictureStyle bigLargeIcon(Bitmap b) { return this; }
    }
    public static class InboxStyle extends Style {
        public InboxStyle() {}
        public InboxStyle setBigContentTitle(CharSequence title) { return this; }
        public InboxStyle setSummaryText(CharSequence cs) { return this; }
        public InboxStyle addLine(CharSequence cs) { return this; }
    }
    public static class MessagingStyle extends Style {
        public MessagingStyle(CharSequence userDisplayName) {}
        public MessagingStyle setConversationTitle(CharSequence title) { return this; }
        public MessagingStyle addMessage(CharSequence text, long timestamp, CharSequence sender) { return this; }
        public MessagingStyle addMessage(Message message) { return this; }
    }
    public static class Message {
        public Message(CharSequence text, long timestamp, CharSequence sender) {}
    }

    public static final Parcelable.Creator<Notification> CREATOR = new Parcelable.Creator<Notification>() {
        public Notification createFromParcel(Parcel source) { return new Notification(source); }
        public Notification[] newArray(int size) { return new Notification[size]; }
    };
}
