package desktop;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.WindowManagerImpl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/** Shared desktop environment for the Android compatibility shim. */
public final class DesktopEnv {
    public static File home;
    public static File assetsDir;
    public static File resDir;
    public static File saveDir;
    public static int width = 720;
    public static int height = 1280;
    public static float density = 1.0f;

    public static Resources resources;
    public static AssetManager assets;
    public static PackageManager packageManager;
    public static DisplayMetrics metrics;
    public static WindowManager windowManager;
    public static Object application;

    private static final Map<String, SharedPreferences> PREFS = new HashMap<>();

    private DesktopEnv() {}

    public static void init(File homeDir, int w, int h) {
        home = homeDir;
        assetsDir = new File(homeDir, "assets");
        resDir = new File(homeDir, "res");
        saveDir = new File(homeDir, "save");
        saveDir.mkdirs();
        width = w;
        height = h;

        metrics = new DisplayMetrics();
        metrics.widthPixels = w;
        metrics.heightPixels = h;
        metrics.density = density;
        metrics.scaledDensity = density;
        metrics.densityDpi = (int) (density * 160);
        metrics.xdpi = 160 * density;
        metrics.ydpi = 160 * density;

        assets = new AssetManager();
        resources = new Resources();
        packageManager = new PackageManager();
        windowManager = new WindowManagerImpl();
        application = new android.app.Application();
    }

    public static SharedPreferences prefs(String name) {
        String key = name == null ? "default" : name;
        return PREFS.computeIfAbsent(key, k -> new android.content.SharedPreferencesImpl(new File(saveDir, "prefs-" + k + ".properties")));
    }

    public static Object systemService(String name) {
        if (name == null) return new Object();
        switch (name) {
            case "window": return windowManager;
            case "power": return new android.os.PowerManager();
            case "sensor": return new android.hardware.SensorManager();
            case "vibrator": return new android.os.Vibrator();
            case "notification": return new android.app.NotificationManager();
            case "input_method": return new android.view.inputmethod.InputMethodManager();
            case "clipboard": return new android.content.ClipboardManager();
            case "bluetooth": return new android.bluetooth.BluetoothManager();
            default: return service("android." + packageFor(name) + "." + classNameFor(name));
        }
    }

    private static String packageFor(String service) {
        switch (service) {
            case "audio": return "media";
            case "connectivity": case "wifi": return "net";
            case "location": return "location";
            case "activity": return "app";
            case "telephony": return "telephony";
            case "storage": return "os.storage";
            case "alarm": case "keyguard": case "uimode": case "download": return "app";
            case "display": return "hardware.display";
            case "battery": return "os";
            case "jobscheduler": return "app.job";
            case "appops": return "app";
            case "autofill": return "view.autofill";
            case "textclassification": return "view.textclassifier";
            case "media_router": return "media";
            case "nfc": return "nfc";
            case "usb": return "hardware.usb";
            case "media_projection": return "media.projection";
            case "dropbox": return "os";
            default: return "content";
        }
    }

    private static String classNameFor(String service) {
        switch (service) {
            case "audio": return "AudioManager";
            case "connectivity": return "ConnectivityManager";
            case "wifi": return "wifi.WifiManager";
            case "location": return "LocationManager";
            case "activity": return "ActivityManager";
            case "telephony": return "TelephonyManager";
            case "storage": return "StorageManager";
            case "alarm": return "AlarmManager";
            case "keyguard": return "KeyguardManager";
            case "uimode": return "UiModeManager";
            case "display": return "DisplayManager";
            case "battery": return "BatteryManager";
            case "jobscheduler": return "JobScheduler";
            case "appops": return "AppOpsManager";
            case "autofill": return "AutofillManager";
            case "textclassification": return "TextClassificationManager";
            case "media_router": return "MediaRouter";
            case "nfc": return "NfcManager";
            case "usb": return "UsbManager";
            case "download": return "DownloadManager";
            case "media_projection": return "MediaProjectionManager";
            case "dropbox": return "DropBoxManager";
            default: return "Object";
        }
    }

    private static Object service(String className) {
        try {
            return Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Throwable t) {
            return new Object();
        }
    }
}
