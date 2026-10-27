package android.content;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Looper;
import android.util.DisplayMetrics;
import desktop.DesktopEnv;

import java.io.File;

/** Minimal android.content.Context implementation backed by DesktopEnv. */
public class Context {
    public Context() {}

    public AssetManager getAssets() { return DesktopEnv.assets; }
    public Resources getResources() { return DesktopEnv.resources; }
    public PackageManager getPackageManager() { return DesktopEnv.packageManager; }
    public String getPackageName() { return "me.pou.app"; }
    public String getOpPackageName() { return getPackageName(); }
    public String getAttributionTag() { return null; }
    public SharedPreferences getSharedPreferences(String name, int mode) { return DesktopEnv.prefs(name); }
    public String getString(int id) { return getResources().getString(id); }
    public String getString(int id, Object... args) { return getResources().getString(id, args); }
    public CharSequence getText(int id) { return getResources().getText(id); }
    public Object getSystemService(String name) { return DesktopEnv.systemService(name); }
    public Object getSystemService(Class<?> serviceClass) { return null; }
    public String getSystemServiceName(Class<?> serviceClass) { return null; }

    public File getFilesDir() { File f = new File(DesktopEnv.saveDir, "files"); f.mkdirs(); return f; }
    public File getCacheDir() { File f = new File(DesktopEnv.saveDir, "cache"); f.mkdirs(); return f; }
    public File getCodeCacheDir() { return getCacheDir(); }
    public File getNoBackupFilesDir() { return getFilesDir(); }
    public File getExternalFilesDir(String type) {
        File f = new File(DesktopEnv.saveDir, "external-files" + (type == null ? "" : "/" + type));
        f.mkdirs();
        return f;
    }
    public File[] getExternalFilesDirs(String type) { return new File[]{getExternalFilesDir(type)}; }
    public File getExternalCacheDir() { File f = new File(DesktopEnv.saveDir, "external-cache"); f.mkdirs(); return f; }
    public File[] getExternalCacheDirs() { return new File[]{getExternalCacheDir()}; }
    public File getObbDir() { return getFilesDir(); }
    public File getDir(String name, int mode) { File f = new File(DesktopEnv.saveDir, "app_" + name); f.mkdirs(); return f; }

    public Context getApplicationContext() { return this; }
    public android.app.Application getApplication() { return (android.app.Application) DesktopEnv.application; }
    public Looper getMainLooper() { return Looper.getMainLooper(); }
    public Resources.Theme getTheme() { return getResources().newTheme(); }
    public void setTheme(int resid) {}
    public ClassLoader getClassLoader() { return getClass().getClassLoader(); }
    public int checkSelfPermission(String permission) { return 0; }
    public int checkCallingOrSelfPermission(String permission) { return 0; }
    public int checkPermission(String permission, int pid, int uid) { return 0; }
    public void enforcePermission(String permission, int pid, int uid, String message) {}
    public int getColor(int id) { return getResources().getColor(id); }
    public android.graphics.drawable.Drawable getDrawable(int id) { return getResources().getDrawable(id); }
    public ContentResolver getContentResolver() { return new ContentResolver(); }
    public ApplicationInfo getApplicationInfo() {
        try { return getPackageManager().getApplicationInfo(getPackageName(), 0); }
        catch (Exception e) { return new ApplicationInfo(); }
    }
    public boolean isRestricted() { return false; }
    public void sendBroadcast(Intent intent) {}
    public void sendBroadcast(Intent intent, String receiverPermission) {}
    public void sendOrderedBroadcast(Intent intent, String receiverPermission) {}
    public Intent registerReceiver(Object receiver, IntentFilter filter) { return null; }
    public void unregisterReceiver(Object receiver) {}
    public void startActivity(Intent intent) {}
    public void startActivity(Intent intent, android.os.Bundle options) {}
    public void startService(Intent intent) {}
    public boolean stopService(Intent intent) { return false; }
    public void revokeSelfPermissionsOnKill(java.util.Collection<String> permissions) {}
}
