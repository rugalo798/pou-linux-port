package android.os;

/** Main looper (the Swing EDT). */
public final class Looper {
    private static final Looper MAIN = new Looper(Thread.currentThread());
    private final Thread thread;

    private Looper(Thread thread) { this.thread = thread; }

    public static Looper getMainLooper() { return MAIN; }
    public static Looper myLooper() { return MAIN; }
    public static void prepare() {}
    public static void prepareMainLooper() {}
    public static void loop() {
        while (true) {
            try { Thread.sleep(1000); } catch (InterruptedException e) { return; }
        }
    }
    public Thread getThread() { return thread; }
    public boolean isCurrentThread() { return Thread.currentThread() == thread; }
    public void quit() {}
    public void quitSafely() {}
    public MessageQueue getQueue() { return null; }
    public String toString() { return "Looper(" + thread.getName() + ")"; }

    /** MessageQueue stub. */
    public static final class MessageQueue {
        public boolean isIdle() { return true; }
    }
}
