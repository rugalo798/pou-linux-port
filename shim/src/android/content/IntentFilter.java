package android.content;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/** IntentFilter stub. */
public class IntentFilter implements Parcelable {
    public static final int NO_MATCH_ACTION = -3;
    public static final int NO_MATCH_CATEGORY = -4;
    public static final int NO_MATCH_DATA = -2;
    public static final int NO_MATCH_TYPE = -1;
    public static final int MATCH_CATEGORY_MASK = 0xfff0000;
    public static final int MATCH_ADJUSTMENT_MASK = 0x0000ffff;
    public static final int MATCH_ADJUSTMENT_NORMAL = 0x8000;
    public static final int SYSTEM_HIGH_PRIORITY = 1000;
    public static final int SYSTEM_LOW_PRIORITY = -1000;

    private final List<String> actions = new ArrayList<>();
    private final List<String> categories = new ArrayList<>();
    private final List<String> dataTypes = new ArrayList<>();
    private int priority;

    public IntentFilter() {}
    public IntentFilter(String action) { addAction(action); }
    public IntentFilter(IntentFilter o) {
        actions.addAll(o.actions); categories.addAll(o.categories); dataTypes.addAll(o.dataTypes); priority = o.priority;
    }

    public void addAction(String action) { if (!actions.contains(action)) actions.add(action); }
    public int countActions() { return actions.size(); }
    public String getAction(int index) { return actions.get(index); }
    public boolean hasAction(String action) { return actions.contains(action); }
    public boolean matchAction(String action) { return actions.isEmpty() || actions.contains(action); }
    public void addCategory(String category) { if (!categories.contains(category)) categories.add(category); }
    public int countCategories() { return categories.size(); }
    public String getCategory(int index) { return categories.get(index); }
    public boolean hasCategory(String category) { return categories.contains(category); }
    public void addDataType(String type) { if (!dataTypes.contains(type)) dataTypes.add(type); }
    public int countDataTypes() { return dataTypes.size(); }
    public String getDataType(int index) { return dataTypes.get(index); }
    public boolean hasDataType(String type) { return dataTypes.contains(type); }
    public void addDataScheme(String scheme) {}
    public void addDataAuthority(String host, String port) {}
    public void addDataPath(String path, int type) {}
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public final int match(String action, String type, String scheme, android.net.Uri data, java.util.Set<String> categories, String logTag) { return 0; }
    public final int match(ContentResolver resolver, Intent intent, boolean resolve, String logTag) { return 0; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}

    public static final Parcelable.Creator<IntentFilter> CREATOR = new Parcelable.Creator<IntentFilter>() {
        public IntentFilter createFromParcel(Parcel source) { return new IntentFilter(); }
        public IntentFilter[] newArray(int size) { return new IntentFilter[size]; }
    };
}
