package android.preference;

/** PreferenceManager. */
public class PreferenceManager {
    public static final String KEY_HAS_SET_DEFAULT_VALUES = "_has_set_default_values";
    public static android.content.SharedPreferences getDefaultSharedPreferences(android.content.Context context) {
        return context.getSharedPreferences(getDefaultSharedPreferencesName(context), 0);
    }
    public static String getDefaultSharedPreferencesName(android.content.Context context) { return "default"; }
    public static void setDefaultValues(android.content.Context context, int resId, boolean readAgain) {}
    public static void setSharedPreferencesName(String sharedPreferencesName) {}
    public static void setSharedPreferencesMode(int sharedPreferencesMode) {}
    public static void setStorageDefault(Object storage) {}
    public static void setStorageImplementation(Object storage) {}
}
