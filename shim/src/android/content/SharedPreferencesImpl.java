package android.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/** SharedPreferences persisted as a properties file. */
public class SharedPreferencesImpl implements SharedPreferences {
    private final File file;
    private final Properties props = new Properties();
    private final Map<OnSharedPreferenceChangeListener, Object> listeners = new HashMap<>();

    public SharedPreferencesImpl(File file) {
        this.file = file;
        load();
    }

    private void load() {
        if (file == null || !file.exists()) return;
        try (InputStream in = new FileInputStream(file)) { props.load(in); }
        catch (Exception e) { System.err.println("[pou-desktop] prefs load: " + e); }
    }

    private void save() {
        if (file == null) return;
        try {
            file.getParentFile().mkdirs();
            try (OutputStream out = new FileOutputStream(file)) { props.store(out, "Pou desktop preferences"); }
        } catch (Exception e) { System.err.println("[pou-desktop] prefs save: " + e); }
    }

    @Override public Map<String, ?> getAll() {
        Map<String, Object> out = new HashMap<>();
        for (String name : props.stringPropertyNames()) out.put(name, props.getProperty(name));
        return out;
    }
    @Override public String getString(String key, String defValue) { return props.getProperty(key, defValue); }
    @Override public Set<String> getStringSet(String key, Set<String> defValues) {
        String v = props.getProperty(key);
        if (v == null) return defValues;
        Set<String> out = new HashSet<>();
        for (String s : v.split("\\|")) if (!s.isEmpty()) out.add(s);
        return out;
    }
    @Override public int getInt(String key, int defValue) { try { return Integer.parseInt(props.getProperty(key)); } catch (Exception e) { return defValue; } }
    @Override public long getLong(String key, long defValue) { try { return Long.parseLong(props.getProperty(key)); } catch (Exception e) { return defValue; } }
    @Override public float getFloat(String key, float defValue) { try { return Float.parseFloat(props.getProperty(key)); } catch (Exception e) { return defValue; } }
    @Override public boolean getBoolean(String key, boolean defValue) { String v = props.getProperty(key); return v == null ? defValue : Boolean.parseBoolean(v); }
    @Override public boolean contains(String key) { return props.containsKey(key); }
    @Override public Editor edit() { return new EditorImpl(); }
    @Override public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) { if (listener != null) listeners.put(listener, listener); }
    @Override public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) { listeners.remove(listener); }

    private class EditorImpl implements Editor {
        private final Properties pending = new Properties();
        private boolean clear;
        public Editor putString(String key, String value) { if (value == null) pending.remove(key); else pending.setProperty(key, value); return this; }
        public Editor putStringSet(String key, Set<String> values) { return putString(key, values == null ? null : String.join("|", values)); }
        public Editor putInt(String key, int value) { return putString(key, String.valueOf(value)); }
        public Editor putLong(String key, long value) { return putString(key, String.valueOf(value)); }
        public Editor putFloat(String key, float value) { return putString(key, String.valueOf(value)); }
        public Editor putBoolean(String key, boolean value) { return putString(key, String.valueOf(value)); }
        public Editor remove(String key) { pending.remove(key); props.remove(key); return this; }
        public Editor clear() { clear = true; return this; }
        public boolean commit() {
            if (clear) props.clear();
            for (String key : pending.stringPropertyNames()) props.setProperty(key, pending.getProperty(key));
            save();
            return true;
        }
        public void apply() { commit(); }
    }
}
