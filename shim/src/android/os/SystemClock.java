package android.os;

/** System clock. */
public final class SystemClock {
    private SystemClock() {}
    public static long elapsedRealtime() { return System.nanoTime() / 1_000_000L; }
    public static long elapsedRealtimeNanos() { return System.nanoTime(); }
    public static long uptimeMillis() { return System.nanoTime() / 1_000_000L; }
    public static long currentThreadTimeMillis() { return System.nanoTime() / 1_000_000L; }
    public static long currentThreadTimeMicro() { return System.nanoTime() / 1_000L; }
    public static long currentTimeMicro() { return System.currentTimeMillis() * 1000L; }
    public static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {} }
    public static boolean setCurrentTimeMillis(long millis) { return false; }
}
