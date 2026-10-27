package android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import desktop.DesktopEnv;

import javax.swing.*;
import java.awt.*;

/** Minimal Activity that hosts the game's View tree in a Swing JFrame. */
public class Activity extends Context {
    public JFrame frame;
    public JPanel rootPanel;
    public View contentView;
    public Window window;
    public WindowManager windowManager;
    public boolean finishing;
    public int requestedOrientation = 1;
    public Intent intent = new Intent();

    public Activity() {
        super();
        window = new Window(this);
        windowManager = new WindowManagerImpl();
        frame = new JFrame("Pou Desktop");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(java.awt.Color.BLACK);
        frame.setContentPane(rootPanel);
        frame.setSize(DesktopEnv.width, DesktopEnv.height);
        frame.setLocationRelativeTo(null);
    }

    public void onCreate(Bundle b) {}
    public void onStart() {}
    public void onResume() {}
    public void onPause() {}
    public void onStop() {}
    public void onDestroy() {}
    public void onRestart() {}
    public void onBackPressed() {}
    public void onPostCreate(Bundle b) {}
    public void onPostResume() {}

    public void setContentView(View v) {
        contentView = v;
        rootPanel.removeAll();
        if (v != null && v.getComponent() != null) {
            rootPanel.add(v.getComponent(), BorderLayout.CENTER);
        }
        rootPanel.revalidate();
        rootPanel.repaint();
    }
    public void setContentView(int id) {}
    public void addContentView(View v, android.view.ViewGroup.LayoutParams p) {
        if (v != null && v.getComponent() != null) rootPanel.add(v.getComponent());
    }
    public View findViewById(int id) { return null; }
    public Window getWindow() { return window; }
    public WindowManager getWindowManager() { return windowManager; }
    public void runOnUiThread(Runnable r) {
        if (SwingUtilities.isEventDispatchThread()) r.run(); else SwingUtilities.invokeLater(r);
    }
    public void setRequestedOrientation(int o) {
        requestedOrientation = o;
    }
    public int getRequestedOrientation() { return requestedOrientation; }
    public void moveTaskToBack(boolean b) {}
    public void finish() { finishing = true; frame.dispose(); }
    public boolean isFinishing() { return finishing; }
    public Intent getIntent() { return intent; }
    public void setIntent(Intent i) { intent = i; }
    public void startActivity(Intent i) {}
    public void startActivityForResult(Intent i, int c) {}
    public void startActivityForResult(Intent i, int c, Bundle b) {}
    public void requestPermissions(String[] p, int c) {}
    public boolean shouldShowRequestPermissionRationale(String p) { return false; }
    public void onRequestPermissionsResult(int a, String[] p, int[] g) {}
    public void onActivityResult(int a, int b, Intent i) {}
    public void onNewIntent(Intent i) {}
    public void onConfigurationChanged(android.content.res.Configuration c) {}
    public void onWindowFocusChanged(boolean hasFocus) {}
    public Object getLastNonConfigurationInstance() { return null; }
    public Object onRetainNonConfigurationInstance() { return null; }
    public void setTitle(CharSequence t) { frame.setTitle(t == null ? "Pou Desktop" : t.toString()); }
    public void setVolumeControlStream(int s) {}
    public int getVolumeControlStream() { return 0; }
    public void setDefaultKeyMode(int m) {}
    public void setFinishOnTouchOutside(boolean b) {}
    public void setShowWhenLocked(boolean b) {}
    public void setTurnScreenOn(boolean b) {}
    public void setRequestedOrientation(int o, boolean b) { setRequestedOrientation(o); }
    public boolean dispatchTouchEvent(android.view.MotionEvent e) { return contentView != null && contentView.dispatchTouchEvent(e); }
    public boolean onTouchEvent(android.view.MotionEvent e) { return false; }
    public boolean onKeyDown(int keyCode, android.view.KeyEvent e) { return false; }
    public boolean onKeyUp(int keyCode, android.view.KeyEvent e) { return false; }
    public void onWindowAttributesChanged(WindowManager.LayoutParams p) {}
    public void onContentChanged() {}
    public void onUserInteraction() {}
    public void onUserLeaveHint() {}
    public void onTrimMemory(int level) {}
    public void onLowMemory() {}
    public void onSaveInstanceState(Bundle b) {}
    public void onRestoreInstanceState(Bundle b) {}

    /** Attach the view tree to the visible window and start SurfaceView callbacks. */
    public void showWindow() {
        frame.setVisible(true);
        if (contentView != null) contentView.onAttachedToWindow();
        frame.revalidate();
        frame.repaint();
    }
}
