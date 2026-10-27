package android.os;

/** Minimal desktop implementation of android.os.Process. */
public final class Process {
    public static int myPid() { return (int) ProcessHandle.current().pid(); }
    public static int myTid() { return (int) Thread.currentThread().getId(); }
    public static int myUid() { return 0; }
    public static int myPpid() { return 1; }
    public static int getUidForPid(int pid) { return 0; }
    public static boolean is64Bit() { return true; }
    public static void killProcess(int pid) {}
    public static void sendSignal(int pid, int signal) {}
    public static void setThreadPriority(int priority) {}
    public static void setThreadPriority(int tid, int priority) {}
    public static long getStartElapsedRealtime() { return System.currentTimeMillis(); }
    public static long getStartUptimeMillis() { return System.nanoTime() / 1_000_000L; }
}
