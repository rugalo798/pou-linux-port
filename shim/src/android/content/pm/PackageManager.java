package android.content.pm;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import desktop.DesktopEnv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** PackageManager stub with the app's package info. */
public class PackageManager {
    public static final int GET_ACTIVITIES = 1;
    public static final int GET_RECEIVERS = 2;
    public static final int GET_SERVICES = 4;
    public static final int GET_PROVIDERS = 8;
    public static final int GET_INSTRUMENTATION = 16;
    public static final int GET_INTENT_FILTERS = 32;
    public static final int GET_SIGNATURES = 64;
    public static final int GET_META_DATA = 128;
    public static final int GET_RESOLVED_FILTER = 64;
    public static final int GET_SIGNING_CERTIFICATES = 134217728;
    public static final int GET_PERMISSIONS = 4096;
    public static final int MATCH_DEFAULT_ONLY = 65536;
    public static final int MATCH_ALL = 131072;
    public static final int PERMISSION_GRANTED = 0;
    public static final int PERMISSION_DENIED = -1;
    public static final int COMPONENT_ENABLED_STATE_DEFAULT = 0;
    public static final int COMPONENT_ENABLED_STATE_ENABLED = 1;
    public static final int COMPONENT_ENABLED_STATE_DISABLED = 2;
    public static final String FEATURE_MICROPHONE = "android.hardware.microphone";
    public static final String FEATURE_CAMERA = "android.hardware.camera";
    public static final String FEATURE_CAMERA_ANY = "android.hardware.camera.any";
    public static final String FEATURE_TOUCHSCREEN = "android.hardware.touchscreen";
    public static final String FEATURE_SENSOR_ACCELEROMETER = "android.hardware.sensor.accelerometer";

    public PackageInfo getPackageInfo(String packageName, int flags) throws NameNotFoundException {
        PackageInfo info = new PackageInfo();
        info.packageName = "me.pou.app";
        info.versionName = "1.4.139";
        info.versionCode = 139;
        info.longVersionCode = 139;
        info.firstInstallTime = System.currentTimeMillis();
        info.lastUpdateTime = System.currentTimeMillis();
        info.applicationInfo = getApplicationInfo(packageName, flags);
        info.requestedPermissions = new String[0];
        info.signatures = new Signature[0];
        return info;
    }
    public PackageInfo getPackageInfo(VersionedPackage versionedPackage, int flags) throws NameNotFoundException { return getPackageInfo(versionedPackage.getPackageName(), flags); }
    public ApplicationInfo getApplicationInfo(String packageName, int flags) throws NameNotFoundException {
        ApplicationInfo info = new ApplicationInfo();
        info.packageName = "me.pou.app";
        info.name = "me.pou.app.App";
        info.className = "me.pou.app.App";
        info.processName = "me.pou.app";
        info.targetSdkVersion = 35;
        info.minSdkVersion = 21;
        info.compileSdkVersion = 35;
        info.versionCode = 139;
        info.longVersionCode = 139;
        info.uid = 10000;
        info.enabled = true;
        info.flags = 0;
        File home = DesktopEnv.home != null ? DesktopEnv.home : new File(".");
        File apk = new File(System.getProperty("pou.apk",
                new File(home, "Pou_1.4.139.apk").getPath()));
        info.sourceDir = apk.getAbsolutePath();
        info.publicSourceDir = info.sourceDir;
        info.dataDir = DesktopEnv.saveDir.getAbsolutePath();
        info.nativeLibraryDir = new File(home, "lib").getAbsolutePath();
        info.metaData = new android.os.Bundle();
        info.splitNames = new String[0];
        return info;
    }
    public String getInstallerPackageName(String packageName) { return null; }
    public void setInstallerPackageName(String targetPackage, String installerPackageName) {}
    public boolean hasSystemFeature(String name) { return false; }
    public boolean hasSystemFeature(String name, int version) { return false; }
    public Intent getLaunchIntentForPackage(String packageName) { return null; }
    public Intent getLeanbackLaunchIntentForPackage(String packageName) { return null; }
    public List<ResolveInfo> queryIntentActivities(Intent intent, int flags) { return new ArrayList<>(); }
    public List<ResolveInfo> queryIntentActivityOptions(ComponentName caller, Intent[] specifics, Intent intent, int flags) { return new ArrayList<>(); }
    public ResolveInfo resolveActivity(Intent intent, int flags) { return null; }
    public List<ResolveInfo> queryIntentServices(Intent intent, int flags) { return new ArrayList<>(); }
    public ResolveInfo resolveService(Intent intent, int flags) { return null; }
    public List<ResolveInfo> queryIntentReceivers(Intent intent, int flags) { return new ArrayList<>(); }
    public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags) { return new ArrayList<>(); }
    public List<PackageInfo> getInstalledPackages(int flags) { return new ArrayList<>(); }
    public List<ApplicationInfo> getInstalledApplications(int flags) { return new ArrayList<>(); }
    public int checkPermission(String permName, String pkgName) { return PERMISSION_GRANTED; }
    public int checkSignatures(String pkg1, String pkg2) { return 0; }
    public String[] getPackagesForUid(int uid) { return new String[0]; }
    public String getNameForUid(int uid) { return null; }
    public int getPackageUid(String packageName, int flags) throws NameNotFoundException { return 10000; }
    public boolean isPackageSuspended(String packageName) { return false; }
    public void setApplicationEnabledSetting(String packageName, int newState, int flags) {}
    public void setComponentEnabledSetting(ComponentName componentName, int newState, int flags) {}
    public int getComponentEnabledSetting(ComponentName componentName) { return COMPONENT_ENABLED_STATE_DEFAULT; }
    public Resources getResourcesForApplication(String appPackageName) { return null; }
    public Resources getResourcesForApplication(ApplicationInfo appInfo) { return null; }
    public CharSequence getApplicationLabel(ApplicationInfo info) { return info != null ? info.loadLabel(this) : null; }
    public android.graphics.drawable.Drawable getApplicationIcon(ApplicationInfo info) { return null; }
    public android.graphics.drawable.Drawable getApplicationIcon(String packageName) { return null; }
    public PackageInstaller getPackageInstaller() { return null; }
    public void grantRuntimePermission(String packageName, String permissionName, android.os.UserHandle user) {}
    public void revokeRuntimePermission(String packageName, String permissionName, android.os.UserHandle user) {}
    public int getPermissionFlags(String permissionName, String packageName, android.os.UserHandle user) { return 0; }
    public void updatePermissionFlags(String permissionName, String packageName, int flagMask, int flagValues, android.os.UserHandle user) {}
    public boolean shouldShowRequestPermissionRationale(String permissionName) { return false; }

    public static class NameNotFoundException extends Exception {
        public NameNotFoundException() {}
        public NameNotFoundException(String name) { super(name); }
    }
}
