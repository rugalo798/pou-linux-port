package android.content;

import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Looper;

import java.io.File;

/** Context wrapper delegating to a base context. */
public class ContextWrapper extends Context {
    protected Context base;

    public ContextWrapper(Context base) { this.base = base; }

    public Context getBaseContext() { return base; }

    @Override public AssetManager getAssets() { return base != null ? base.getAssets() : super.getAssets(); }
    @Override public Resources getResources() { return base != null ? base.getResources() : super.getResources(); }
    @Override public PackageManager getPackageManager() { return base != null ? base.getPackageManager() : super.getPackageManager(); }
    @Override public String getPackageName() { return base != null ? base.getPackageName() : super.getPackageName(); }
    @Override public String getOpPackageName() { return base != null ? base.getOpPackageName() : super.getOpPackageName(); }
    @Override public String getAttributionTag() { return base != null ? base.getAttributionTag() : super.getAttributionTag(); }
    @Override public SharedPreferences getSharedPreferences(String name, int mode) { return base != null ? base.getSharedPreferences(name, mode) : super.getSharedPreferences(name, mode); }
    @Override public String getString(int id) { return base != null ? base.getString(id) : super.getString(id); }
    @Override public String getString(int id, Object... args) { return base != null ? base.getString(id, args) : super.getString(id, args); }
    @Override public CharSequence getText(int id) { return base != null ? base.getText(id) : super.getText(id); }
    @Override public Object getSystemService(String name) { return base != null ? base.getSystemService(name) : super.getSystemService(name); }
    @Override public Object getSystemService(Class<?> serviceClass) { return base != null ? base.getSystemService(serviceClass) : super.getSystemService(serviceClass); }
    @Override public String getSystemServiceName(Class<?> serviceClass) { return base != null ? base.getSystemServiceName(serviceClass) : super.getSystemServiceName(serviceClass); }
    @Override public File getFilesDir() { return base != null ? base.getFilesDir() : super.getFilesDir(); }
    @Override public File getCacheDir() { return base != null ? base.getCacheDir() : super.getCacheDir(); }
    @Override public File getCodeCacheDir() { return base != null ? base.getCodeCacheDir() : super.getCodeCacheDir(); }
    @Override public File getNoBackupFilesDir() { return base != null ? base.getNoBackupFilesDir() : super.getNoBackupFilesDir(); }
    @Override public File getExternalFilesDir(String type) { return base != null ? base.getExternalFilesDir(type) : super.getExternalFilesDir(type); }
    @Override public File[] getExternalFilesDirs(String type) { return base != null ? base.getExternalFilesDirs(type) : super.getExternalFilesDirs(type); }
    @Override public File getExternalCacheDir() { return base != null ? base.getExternalCacheDir() : super.getExternalCacheDir(); }
    @Override public File[] getExternalCacheDirs() { return base != null ? base.getExternalCacheDirs() : super.getExternalCacheDirs(); }
    @Override public File getObbDir() { return base != null ? base.getObbDir() : super.getObbDir(); }
    @Override public File getDir(String name, int mode) { return base != null ? base.getDir(name, mode) : super.getDir(name, mode); }
    @Override public Context getApplicationContext() { return base != null ? base.getApplicationContext() : super.getApplicationContext(); }
    @Override public android.app.Application getApplication() { return base != null ? base.getApplication() : super.getApplication(); }
    @Override public Looper getMainLooper() { return base != null ? base.getMainLooper() : super.getMainLooper(); }
    @Override public Resources.Theme getTheme() { return base != null ? base.getTheme() : super.getTheme(); }
    @Override public void setTheme(int resid) { if (base != null) base.setTheme(resid); else super.setTheme(resid); }
    @Override public ClassLoader getClassLoader() { return base != null ? base.getClassLoader() : super.getClassLoader(); }
    @Override public int checkSelfPermission(String permission) { return base != null ? base.checkSelfPermission(permission) : super.checkSelfPermission(permission); }
    @Override public int checkCallingOrSelfPermission(String permission) { return base != null ? base.checkCallingOrSelfPermission(permission) : super.checkCallingOrSelfPermission(permission); }
    @Override public int checkPermission(String permission, int pid, int uid) { return base != null ? base.checkPermission(permission, pid, uid) : super.checkPermission(permission, pid, uid); }
    @Override public void enforcePermission(String permission, int pid, int uid, String message) { if (base != null) base.enforcePermission(permission, pid, uid, message); else super.enforcePermission(permission, pid, uid, message); }
    @Override public int getColor(int id) { return base != null ? base.getColor(id) : super.getColor(id); }
    @Override public android.graphics.drawable.Drawable getDrawable(int id) { return base != null ? base.getDrawable(id) : super.getDrawable(id); }
    @Override public ContentResolver getContentResolver() { return base != null ? base.getContentResolver() : super.getContentResolver(); }
    @Override public android.content.pm.ApplicationInfo getApplicationInfo() { return base != null ? base.getApplicationInfo() : super.getApplicationInfo(); }
    @Override public boolean isRestricted() { return base != null ? base.isRestricted() : super.isRestricted(); }
    @Override public void sendBroadcast(Intent intent) { if (base != null) base.sendBroadcast(intent); else super.sendBroadcast(intent); }
    @Override public void sendBroadcast(Intent intent, String receiverPermission) { if (base != null) base.sendBroadcast(intent, receiverPermission); else super.sendBroadcast(intent, receiverPermission); }
    @Override public void sendOrderedBroadcast(Intent intent, String receiverPermission) { if (base != null) base.sendOrderedBroadcast(intent, receiverPermission); else super.sendOrderedBroadcast(intent, receiverPermission); }
    @Override public Intent registerReceiver(Object receiver, IntentFilter filter) { return base != null ? base.registerReceiver(receiver, filter) : super.registerReceiver(receiver, filter); }
    @Override public void unregisterReceiver(Object receiver) { if (base != null) base.unregisterReceiver(receiver); else super.unregisterReceiver(receiver); }
    @Override public void startActivity(Intent intent) { if (base != null) base.startActivity(intent); else super.startActivity(intent); }
    @Override public void startActivity(Intent intent, android.os.Bundle options) { if (base != null) base.startActivity(intent, options); else super.startActivity(intent, options); }
    @Override public void startService(Intent intent) { if (base != null) base.startService(intent); else super.startService(intent); }
    @Override public boolean stopService(Intent intent) { return base != null ? base.stopService(intent) : super.stopService(intent); }
}
