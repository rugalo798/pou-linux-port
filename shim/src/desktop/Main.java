package desktop;

import android.os.Bundle;

import java.io.File;

/** Desktop entry point: boots the original me.pou.app.App class on the JVM. */
public final class Main {
    public static void main(String[] args) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.err.println("[pou-desktop] uncaught in " + t.getName());
            e.printStackTrace();
        });

        File home = new File(System.getProperty("pou.home", System.getProperty("user.dir")));
        int width = Integer.getInteger("pou.width", 720);
        int height = Integer.getInteger("pou.height", 1280);
        DesktopEnv.init(home, width, height);

        Class<?> appClass = Class.forName("me.pou.app.App");
        android.app.Activity app = (android.app.Activity) appClass.getDeclaredConstructor().newInstance();

        app.onCreate(new Bundle());
        try { app.onStart(); } catch (Throwable ignored) {}
        try { app.onResume(); } catch (Throwable ignored) {}
        javax.swing.SwingUtilities.invokeLater(app::showWindow);

        // Keep the JVM alive; the Swing window owns the app lifetime.
        Thread.sleep(Long.MAX_VALUE);
    }
}
