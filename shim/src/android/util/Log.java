package android.util;

/** Android Log to stdout/stderr. */
public final class Log {
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;

    private Log() {}
    public static int v(String tag, String msg) { System.out.println("V/" + tag + ": " + msg); return 0; }
    public static int v(String tag, String msg, Throwable tr) { System.out.println("V/" + tag + ": " + msg); tr.printStackTrace(); return 0; }
    public static int d(String tag, String msg) { System.out.println("D/" + tag + ": " + msg); return 0; }
    public static int d(String tag, String msg, Throwable tr) { System.out.println("D/" + tag + ": " + msg); tr.printStackTrace(); return 0; }
    public static int i(String tag, String msg) { System.out.println("I/" + tag + ": " + msg); return 0; }
    public static int i(String tag, String msg, Throwable tr) { System.out.println("I/" + tag + ": " + msg); tr.printStackTrace(); return 0; }
    public static int w(String tag, String msg) { System.out.println("W/" + tag + ": " + msg); return 0; }
    public static int w(String tag, String msg, Throwable tr) { System.out.println("W/" + tag + ": " + msg); tr.printStackTrace(); return 0; }
    public static int w(String tag, Throwable tr) { System.out.println("W/" + tag); tr.printStackTrace(); return 0; }
    public static int e(String tag, String msg) { System.err.println("E/" + tag + ": " + msg); return 0; }
    public static int e(String tag, String msg, Throwable tr) { System.err.println("E/" + tag + ": " + msg); tr.printStackTrace(); return 0; }
    public static int wtf(String tag, String msg) { System.err.println("WTF/" + tag + ": " + msg); return 0; }
    public static int wtf(String tag, Throwable tr) { System.err.println("WTF/" + tag); tr.printStackTrace(); return 0; }
    public static int wtf(String tag, String msg, Throwable tr) { System.err.println("WTF/" + tag + ": " + msg); tr.printStackTrace(); return 0; }
    public static boolean isLoggable(String tag, int level) { return true; }
    public static String getStackTraceString(Throwable tr) {
        if (tr == null) return "";
        java.io.StringWriter sw = new java.io.StringWriter();
        tr.printStackTrace(new java.io.PrintWriter(sw));
        return sw.toString();
    }
    public static int println(int priority, String tag, String msg) { return 0; }
}
