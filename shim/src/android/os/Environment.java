package android.os;

import java.io.File;

/** Environment paths under the port's save directory. */
public class Environment {
    public static final String MEDIA_MOUNTED = "mounted";
    public static final String MEDIA_UNMOUNTED = "unmounted";
    public static final String MEDIA_REMOVED = "removed";
    public static final String MEDIA_BAD_REMOVAL = "bad_removal";
    public static final String MEDIA_SHARED = "shared";
    public static final String MEDIA_CHECKING = "checking";
    public static final String DIRECTORY_MUSIC = "Music";
    public static final String DIRECTORY_PODCASTS = "Podcasts";
    public static final String DIRECTORY_RINGTONES = "Ringtones";
    public static final String DIRECTORY_ALARMS = "Alarms";
    public static final String DIRECTORY_NOTIFICATIONS = "Notifications";
    public static final String DIRECTORY_PICTURES = "Pictures";
    public static final String DIRECTORY_MOVIES = "Movies";
    public static final String DIRECTORY_DOWNLOADS = "Downloads";
    public static final String DIRECTORY_DCIM = "DCIM";
    public static final String DIRECTORY_DOCUMENTS = "Documents";

    public static File getExternalStorageDirectory() { File f = new File(desktop.DesktopEnv.saveDir, "external"); f.mkdirs(); return f; }
    public static File getExternalStoragePublicDirectory(String type) { File f = new File(getExternalStorageDirectory(), type == null ? "" : type); f.mkdirs(); return f; }
    public static String getExternalStorageState() { return MEDIA_MOUNTED; }
    public static String getExternalStorageState(File file) { return MEDIA_MOUNTED; }
    public static boolean isExternalStorageEmulated() { return true; }
    public static boolean isExternalStorageEmulated(File file) { return true; }
    public static boolean isExternalStorageRemovable() { return false; }
    public static boolean isExternalStorageRemovable(File file) { return false; }
    public static File getDataDirectory() { File f = new File(desktop.DesktopEnv.saveDir, "data"); f.mkdirs(); return f; }
    public static File getDownloadCacheDirectory() { File f = new File(desktop.DesktopEnv.saveDir, "download-cache"); f.mkdirs(); return f; }
    public static File getRootDirectory() { return new File("/"); }
    public static File getStorageDirectory() { return getExternalStorageDirectory(); }
    public static void initForNewUser() {}
}
