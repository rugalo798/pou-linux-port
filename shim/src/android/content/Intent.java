package android.content;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/** Intent with a simple extras map. */
public class Intent implements Cloneable, Parcelable {
    public static final String ACTION_MAIN = "android.intent.action.MAIN";
    public static final String ACTION_VIEW = "android.intent.action.VIEW";
    public static final String ACTION_SEND = "android.intent.action.SEND";
    public static final String ACTION_SENDTO = "android.intent.action.SENDTO";
    public static final String ACTION_SEND_MULTIPLE = "android.intent.action.SEND_MULTIPLE";
    public static final String ACTION_EDIT = "android.intent.action.EDIT";
    public static final String ACTION_PICK = "android.intent.action.PICK";
    public static final String ACTION_CHOOSER = "android.intent.action.CHOOSER";
    public static final String ACTION_GET_CONTENT = "android.intent.action.GET_CONTENT";
    public static final String ACTION_OPEN_DOCUMENT = "android.intent.action.OPEN_DOCUMENT";
    public static final String ACTION_CREATE_DOCUMENT = "android.intent.action.CREATE_DOCUMENT";
    public static final String ACTION_DELETE = "android.intent.action.DELETE";
    public static final String ACTION_INSERT = "android.intent.action.INSERT";
    public static final String ACTION_SEARCH = "android.intent.action.SEARCH";
    public static final String ACTION_WEB_SEARCH = "android.intent.action.WEB_SEARCH";
    public static final String ACTION_DIAL = "android.intent.action.DIAL";
    public static final String ACTION_CALL = "android.intent.action.CALL";
    public static final String ACTION_IMAGE_CAPTURE = "android.media.action.IMAGE_CAPTURE";
    public static final String ACTION_VIDEO_CAPTURE = "android.media.action.VIDEO_CAPTURE";
    public static final String CATEGORY_DEFAULT = "android.intent.category.DEFAULT";
    public static final String CATEGORY_LAUNCHER = "android.intent.category.LAUNCHER";
    public static final String CATEGORY_BROWSABLE = "android.intent.category.BROWSABLE";
    public static final String CATEGORY_HOME = "android.intent.category.HOME";
    public static final String CATEGORY_PREFERENCE = "android.intent.category.PREFERENCE";
    public static final String CATEGORY_OPENABLE = "android.intent.category.OPENABLE";
    public static final String EXTRA_TEXT = "android.intent.extra.TEXT";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_SUBJECT = "android.intent.extra.SUBJECT";
    public static final String EXTRA_STREAM = "android.intent.extra.STREAM";
    public static final String EXTRA_EMAIL = "android.intent.extra.EMAIL";
    public static final String EXTRA_CC = "android.intent.extra.CC";
    public static final String EXTRA_BCC = "android.intent.extra.BCC";
    public static final String EXTRA_TITLE = "android.intent.extra.TITLE";
    public static final String EXTRA_INTENT = "android.intent.extra.INTENT";
    public static final String EXTRA_CHOSEN_COMPONENT = "android.intent.extra.CHOSEN_COMPONENT";
    public static final String EXTRA_CHOSEN_COMPONENT_INTENT_SENDER = "android.intent.extra.CHOSEN_COMPONENT_INTENT_SENDER";
    public static final int FLAG_GRANT_READ_URI_PERMISSION = 1;
    public static final int FLAG_GRANT_WRITE_URI_PERMISSION = 2;
    public static final int FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 64;
    public static final int FLAG_GRANT_PREFIX_URI_PERMISSION = 128;
    public static final int FLAG_ACTIVITY_NEW_TASK = 0x10000000;
    public static final int FLAG_ACTIVITY_SINGLE_TOP = 0x20000000;
    public static final int FLAG_ACTIVITY_CLEAR_TOP = 0x04000000;
    public static final int FLAG_ACTIVITY_CLEAR_TASK = 0x00008000;
    public static final int FLAG_ACTIVITY_NO_HISTORY = 0x40000000;
    public static final int FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS = 0x00800000;
    public static final int FLAG_ACTIVITY_MULTIPLE_TASK = 0x08000000;
    public static final int FLAG_ACTIVITY_FORWARD_RESULT = 0x02000000;
    public static final int FLAG_ACTIVITY_PREVIOUS_IS_TOP = 0x01000000;
    public static final int FLAG_ACTIVITY_RESET_TASK_IF_NEEDED = 0x00200000;
    public static final int FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY = 0x00100000;
    public static final int FLAG_ACTIVITY_NO_ANIMATION = 0x00010000;
    public static final int FLAG_ACTIVITY_BROUGHT_TO_FRONT = 0x00400000;
    public static final int FLAG_ACTIVITY_RETAIN_IN_RECENTS = 0x00002000;
    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 0x00004000;
    public static final int FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET = 0x00040000;
    public static final int FLAG_RECEIVER_REGISTERED_ONLY = 0x40000000;
    public static final int FLAG_RECEIVER_REPLACE_PENDING = 0x20000000;
    public static final int FLAG_RECEIVER_FOREGROUND = 0x10000000;
    public static final int FLAG_RECEIVER_NO_ABORT = 0x08000000;
    public static final int URI_INTENT_SCHEME = 1;
    public static final String SCHEME_ANDROID_APP = "android-app";

    private String action;
    private Uri data;
    private String type;
    private String packageName;
    private ComponentName component;
    private int flags;
    private final Bundle extras = new Bundle();
    private final Set<String> categories = new HashSet<>();
    private String identifier;

    public Intent() {}
    public Intent(String action) { this.action = action; }
    public Intent(String action, Uri uri) { this.action = action; this.data = uri; }
    public Intent(Context packageContext, Class<?> cls) { this.component = new ComponentName(packageContext, cls); }
    public Intent(String action, Uri uri, Context packageContext, Class<?> cls) {
        this(action, uri); this.component = new ComponentName(packageContext, cls);
    }
    public Intent(Intent o) {
        action = o.action; data = o.data; type = o.type; packageName = o.packageName;
        component = o.component; flags = o.flags; extras.putAll(o.extras);
        categories.addAll(o.categories); identifier = o.identifier;
    }

    public String getAction() { return action; }
    public Intent setAction(String action) { this.action = action; return this; }
    public Uri getData() { return data; }
    public Intent setData(Uri data) { this.data = data; return this; }
    public String getType() { return type; }
    public Intent setType(String type) { this.type = type; return this; }
    public Intent setDataAndType(Uri data, String type) { this.data = data; this.type = type; return this; }
    public Intent addCategory(String category) { categories.add(category); return this; }
    public void removeCategory(String category) { categories.remove(category); }
    public Set<String> getCategories() { return categories; }
    public boolean hasCategory(String category) { return categories.contains(category); }
    public String getPackage() { return packageName; }
    public Intent setPackage(String packageName) { this.packageName = packageName; return this; }
    public ComponentName getComponent() { return component; }
    public Intent setComponent(ComponentName component) { this.component = component; return this; }
    public Intent setClass(Context packageContext, Class<?> cls) { this.component = new ComponentName(packageContext, cls); return this; }
    public Intent setClassName(Context packageContext, String className) { this.component = new ComponentName(packageContext, className); return this; }
    public Intent setClassName(String packageName, String className) { this.component = new ComponentName(packageName, className); return this; }
    public int getFlags() { return flags; }
    public Intent setFlags(int flags) { this.flags = flags; return this; }
    public Intent addFlags(int flags) { this.flags |= flags; return this; }
    public void removeFlags(int flags) { this.flags &= ~flags; }
    public String getIdentifier() { return identifier; }
    public Intent setIdentifier(String identifier) { this.identifier = identifier; return this; }

    public Intent putExtra(String name, boolean value) { extras.putBoolean(name, value); return this; }
    public Intent putExtra(String name, byte value) { extras.putByte(name, value); return this; }
    public Intent putExtra(String name, char value) { extras.putChar(name, value); return this; }
    public Intent putExtra(String name, short value) { extras.putShort(name, value); return this; }
    public Intent putExtra(String name, int value) { extras.putInt(name, value); return this; }
    public Intent putExtra(String name, long value) { extras.putLong(name, value); return this; }
    public Intent putExtra(String name, float value) { extras.putFloat(name, value); return this; }
    public Intent putExtra(String name, double value) { extras.putDouble(name, value); return this; }
    public Intent putExtra(String name, String value) { extras.putString(name, value); return this; }
    public Intent putExtra(String name, CharSequence value) { extras.putCharSequence(name, value); return this; }
    public Intent putExtra(String name, byte[] value) { extras.putByteArray(name, value); return this; }
    public Intent putExtra(String name, int[] value) { extras.putIntArray(name, value); return this; }
    public Intent putExtra(String name, long[] value) { extras.putLongArray(name, value); return this; }
    public Intent putExtra(String name, float[] value) { extras.putFloatArray(name, value); return this; }
    public Intent putExtra(String name, double[] value) { extras.putDoubleArray(name, value); return this; }
    public Intent putExtra(String name, String[] value) { extras.putStringArray(name, value); return this; }
    public Intent putExtra(String name, Serializable value) { extras.putSerializable(name, value); return this; }
    public Intent putExtra(String name, Bundle value) { extras.putBundle(name, value); return this; }
    public Intent putExtra(String name, Parcelable value) { extras.putParcelable(name, value); return this; }
    public Intent putExtra(String name, Parcelable[] value) { extras.putParcelableArray(name, value); return this; }
    public Intent putExtra(String name, ArrayList<String> value) { extras.putStringArrayList(name, value); return this; }
    public Intent putExtras(Intent src) { if (src != null) extras.putAll(src.extras); return this; }
    public Intent putExtras(Bundle extras) { if (extras != null) this.extras.putAll(extras); return this; }
    public Bundle getExtras() { return extras; }
    public Bundle getBundleExtra(String name) { return extras.getBundle(name); }
    public boolean hasExtra(String name) { return extras.containsKey(name); }
    public boolean getBooleanExtra(String name, boolean defaultValue) { return extras.getBoolean(name, defaultValue); }
    public byte getByteExtra(String name, byte defaultValue) { return extras.getByte(name, defaultValue); }
    public char getCharExtra(String name, char defaultValue) { return extras.getChar(name, defaultValue); }
    public short getShortExtra(String name, short defaultValue) { return extras.getShort(name, defaultValue); }
    public int getIntExtra(String name, int defaultValue) { return extras.getInt(name, defaultValue); }
    public long getLongExtra(String name, long defaultValue) { return extras.getLong(name, defaultValue); }
    public float getFloatExtra(String name, float defaultValue) { return extras.getFloat(name, defaultValue); }
    public double getDoubleExtra(String name, double defaultValue) { return extras.getDouble(name, defaultValue); }
    public String getStringExtra(String name) { return extras.getString(name); }
    public CharSequence getCharSequenceExtra(String name) { return extras.getCharSequence(name); }
    public byte[] getByteArrayExtra(String name) { return extras.getByteArray(name); }
    public int[] getIntArrayExtra(String name) { return extras.getIntArray(name); }
    public long[] getLongArrayExtra(String name) { return extras.getLongArray(name); }
    public float[] getFloatArrayExtra(String name) { return extras.getFloatArray(name); }
    public double[] getDoubleArrayExtra(String name) { return extras.getDoubleArray(name); }
    public String[] getStringArrayExtra(String name) { return extras.getStringArray(name); }
    public Serializable getSerializableExtra(String name) { return extras.getSerializable(name); }
    public Parcelable getParcelableExtra(String name) { return extras.getParcelable(name); }
    public Parcelable[] getParcelableArrayExtra(String name) { return extras.getParcelableArray(name); }
    public ArrayList<String> getStringArrayListExtra(String name) { return extras.getStringArrayList(name); }
    public void removeExtra(String name) { extras.remove(name); }
    public void replaceExtras(Bundle extras) { this.extras.clear(); if (extras != null) this.extras.putAll(extras); }

    public Intent clone() { return new Intent(this); }
    public Intent cloneFilter() { return new Intent(this); }
    public static Intent createChooser(Intent target, CharSequence title) {
        Intent i = new Intent(ACTION_CHOOSER);
        i.putExtra(EXTRA_INTENT, target);
        i.putExtra(EXTRA_TITLE, title);
        return i;
    }
    public static Intent makeMainActivity(ComponentName mainActivity) { return new Intent(ACTION_MAIN).setComponent(mainActivity); }
    public static Intent makeRestartActivityTask(ComponentName mainActivity) { return makeMainActivity(mainActivity); }
    public static Intent parseUri(String uri, int flags) { return null; }
    public String toUri(int flags) { return toString(); }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public String toString() { return "Intent(" + action + ")"; }

    public static final Parcelable.Creator<Intent> CREATOR = new Parcelable.Creator<Intent>() {
        public Intent createFromParcel(Parcel source) { return new Intent(); }
        public Intent[] newArray(int size) { return new Intent[size]; }
    };
}
