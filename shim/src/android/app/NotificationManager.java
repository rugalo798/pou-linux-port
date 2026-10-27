package android.app;

import java.util.ArrayList;
import java.util.List;

/** Notification manager stub. */
public class NotificationManager {
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;

    private final List<NotificationChannel> channels = new ArrayList<>();
    private final List<NotificationChannelGroup> groups = new ArrayList<>();

    public void notify(int id, Notification notification) {}
    public void notify(String tag, int id, Notification notification) {}
    public void cancel(int id) {}
    public void cancel(String tag, int id) {}
    public void cancelAll() {}
    public void createNotificationChannel(NotificationChannel channel) { if (channel != null) channels.add(channel); }
    public void createNotificationChannelGroup(NotificationChannelGroup group) { if (group != null) groups.add(group); }
    public void createNotificationChannels(List<NotificationChannel> channels) { if (channels != null) this.channels.addAll(channels); }
    public void createNotificationChannelGroups(List<NotificationChannelGroup> groups) { if (groups != null) this.groups.addAll(groups); }
    public void deleteNotificationChannel(String channelId) {}
    public void deleteNotificationChannelGroup(String groupId) {}
    public NotificationChannel getNotificationChannel(String channelId) {
        for (NotificationChannel c : channels) if (c.getId().equals(channelId)) return c;
        return null;
    }
    public List<NotificationChannel> getNotificationChannels() { return channels; }
    public List<NotificationChannelGroup> getNotificationChannelGroups() { return groups; }
    public int getImportance() { return IMPORTANCE_DEFAULT; }
    public boolean areNotificationsEnabled() { return false; }
    public void setNotificationDelegate(Object delegate) {}
    public boolean isNotificationPolicyAccessGranted() { return false; }
    public boolean isNotificationListenerAccessGranted(Object component) { return false; }
    public void setInterruptionFilter(int interruptionFilter) {}
    public int getInterruptionFilter() { return 1; }
    public void setNotificationPolicy(Object policy) {}
    public Object getNotificationPolicy() { return null; }

    public static class Policy {
        public static final int PRIORITY_CATEGORY_REMINDERS = 1;
        public static final int PRIORITY_CATEGORY_EVENTS = 2;
        public static final int PRIORITY_CATEGORY_MESSAGES = 4;
        public static final int PRIORITY_CATEGORY_CALLS = 8;
        public static final int PRIORITY_CATEGORY_ALARMS = 32;
        public static final int PRIORITY_CATEGORY_MEDIA = 64;
        public static final int PRIORITY_CATEGORY_SYSTEM = 128;
        public static final int PRIORITY_CATEGORY_GAME = 256;
        public int priorityCategories;
        public int priorityCallSenders;
        public int priorityMessageSenders;
        public int suppressedVisualEffects;
        public int explanation;
    }
}
