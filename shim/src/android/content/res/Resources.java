package android.content.res;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import desktop.DesktopEnv;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** Resources backed by aapt2-dumped string/name maps. */
public class Resources {
    public static class NotFoundException extends RuntimeException {
        public NotFoundException() {}
        public NotFoundException(String name) { super(name); }
    }

    public static class Theme {
        public void applyStyle(int resId, boolean force) {}
        public void setTo(Theme other) {}
        public void resolveAttribute(int resId, TypedValue outValue, boolean resolveRefs) {}
        public boolean resolveAttribute(int resId, TypedValue outValue, boolean resolveRefs, boolean resolveThemeAttrs) { return false; }
    }

    private final Map<Integer, String> strings = new HashMap<>();
    private final Map<Integer, String> rawFiles = new HashMap<>();
    private final Map<String, Integer> names = new HashMap<>();
    private final Configuration configuration = new Configuration();

    public Resources() {
        loadProperties(new File(DesktopEnv.resDir, "strings.properties"), strings);
        loadProperties(new File(DesktopEnv.resDir, "raw.properties"), rawFiles);
        loadNames(new File(DesktopEnv.resDir, "names.properties"));
    }

    private void loadProperties(File file, Map<Integer, String> out) {
        Properties p = new Properties();
        try (InputStream in = new java.io.FileInputStream(file)) {
            p.load(in);
        } catch (Exception e) {
            System.err.println("[pou-desktop] cannot load " + file + ": " + e);
            return;
        }
        for (String key : p.stringPropertyNames()) {
            try {
                out.put((int) Long.parseLong(key.replace("0x", ""), 16), p.getProperty(key));
            } catch (NumberFormatException ignored) {}
        }
    }

    private void loadNames(File file) {
        Properties p = new Properties();
        try (InputStream in = new java.io.FileInputStream(file)) {
            p.load(in);
        } catch (Exception e) {
            System.err.println("[pou-desktop] cannot load " + file + ": " + e);
            return;
        }
        for (String key : p.stringPropertyNames()) {
            try {
                names.put(key, (int) Long.parseLong(p.getProperty(key).replace("0x", ""), 16));
            } catch (NumberFormatException ignored) {}
        }
    }

    public String getString(int id) {
        String s = strings.get(id);
        return s != null ? s : "";
    }
    public String getString(int id, Object... args) {
        String s = getString(id);
        try { return String.format(s, args); } catch (Exception e) { return s; }
    }
    public CharSequence getText(int id) { return getString(id); }
    public CharSequence getQuantityText(int id, int q) { return getString(id); }
    public String getQuantityString(int id, int q) { return getString(id); }
    public String getQuantityString(int id, int q, Object... args) { return getString(id, args); }
    public int getIdentifier(String name, String defType, String defPackage) {
        Integer id = names.get(defType + "/" + name);
        return id != null ? id : 0;
    }
    public int[] getIntArray(int id) { return new int[0]; }
    public String[] getStringArray(int id) { return new String[0]; }
    public CharSequence[] getTextArray(int id) { return new CharSequence[0]; }
    public DisplayMetrics getDisplayMetrics() { return DesktopEnv.metrics; }
    public Configuration getConfiguration() { return configuration; }
    public AssetManager getAssets() { return DesktopEnv.assets; }
    public Theme newTheme() { return new Theme(); }
    public int getColor(int id) { return 0xFF000000; }
    public int getColor(int id, Theme theme) { return 0xFF000000; }
    public Drawable getDrawable(int id) { return new ColorDrawable(0xFF000000); }
    public Drawable getDrawable(int id, Theme theme) { return getDrawable(id); }
    public float getDimension(int id) { return 0f; }
    public int getDimensionPixelSize(int id) { return 0; }
    public int getDimensionPixelOffset(int id) { return 0; }
    public boolean getBoolean(int id) { return false; }
    public int getInteger(int id) { return 0; }
    public InputStream openRawResource(int id) {
        String path = rawFiles.get(id);
        if (path == null) {
            throw new NotFoundException("raw resource 0x" + Integer.toHexString(id));
        }
        try {
            return new FileInputStream(new File(DesktopEnv.assetsDir, path));
        } catch (Exception e) {
            throw new NotFoundException("raw resource 0x" + Integer.toHexString(id) + ": " + e);
        }
    }

    public InputStream openRawResource(int id, TypedValue value) { return openRawResource(id); }

    public AssetFileDescriptor openRawResourceFd(int id) {
        String path = rawFiles.get(id);
        if (path == null) return null;
        File file = new File(DesktopEnv.assetsDir, path);
        try {
            FileInputStream in = new FileInputStream(file);
            return new AssetFileDescriptor(in.getFD(), 0, file.length());
        } catch (Exception e) {
            return null;
        }
    }
    public TypedArray obtainTypedArray(int id) { return new TypedArray(); }
    public void getValue(int id, TypedValue outValue, boolean resolveRefs) {}
    public void getValueForDensity(int id, int density, TypedValue outValue, boolean resolveRefs) {}
    public void updateConfiguration(Configuration config, DisplayMetrics metrics) {}
}
