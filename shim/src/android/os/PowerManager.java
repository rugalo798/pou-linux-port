package android.os;

/** PowerManager with a no-op wake lock. */
public class PowerManager {
    public static final int PARTIAL_WAKE_LOCK = 1;
    public static final int SCREEN_DIM_WAKE_LOCK = 6;
    public static final int SCREEN_BRIGHT_WAKE_LOCK = 10;
    public static final int FULL_WAKE_LOCK = 26;
    public static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
    public static final int ACQUIRE_CAUSES_WAKEUP = 0x10000000;
    public static final int ON_AFTER_RELEASE = 0x20000000;

    public WakeLock newWakeLock(int levelAndFlags, String tag) { return new WakeLock(tag); }
    public boolean isWakeLockLevelSupported(int level) { return true; }
    public boolean isInteractive() { return true; }
    public boolean isPowerSaveMode() { return false; }
    public boolean isDeviceIdleMode() { return false; }
    public boolean isIgnoringBatteryOptimizations(String packageName) { return true; }
    public void goToSleep(long time) {}
    public void wakeUp(long time) {}
    public void reboot(String reason) {}

    public final class WakeLock {
        private final String tag;
        WakeLock(String tag) { this.tag = tag; }
        public void acquire() {}
        public void acquire(long timeout) {}
        public void release() {}
        public void release(int flags) {}
        public boolean isHeld() { return false; }
        public void setReferenceCounted(boolean value) {}
        public void setWorkSource(Object ws) {}
        public String getTag() { return tag; }
        public void setTag(String tag) {}
        public String toString() { return "WakeLock(" + tag + ")"; }
    }
}
