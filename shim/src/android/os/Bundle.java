package android.os;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** Bundle as a string-keyed map. */
public class Bundle implements Cloneable {
    private final Map<String, Object> map = new HashMap<>();

    public Bundle() {}
    public Bundle(Bundle b) { if (b != null) map.putAll(b.map); }
    public Bundle(ClassLoader loader) {}

    public boolean containsKey(String key) { return map.containsKey(key); }
    public Object get(String key) { return map.get(key); }
    public void putAll(Bundle b) { if (b != null) map.putAll(b.map); }
    public void remove(String key) { map.remove(key); }
    public void clear() { map.clear(); }
    public Set<String> keySet() { return map.keySet(); }
    public int size() { return map.size(); }
    public boolean isEmpty() { return map.isEmpty(); }
    public void setClassLoader(ClassLoader loader) {}
    public ClassLoader getClassLoader() { return getClass().getClassLoader(); }

    public void putString(String key, String value) { map.put(key, value); }
    public String getString(String key) { Object v = map.get(key); return v instanceof String ? (String) v : null; }
    public String getString(String key, String defaultValue) { String v = getString(key); return v != null ? v : defaultValue; }
    public void putCharSequence(String key, CharSequence value) { map.put(key, value); }
    public CharSequence getCharSequence(String key) { Object v = map.get(key); return v instanceof CharSequence ? (CharSequence) v : null; }
    public CharSequence getCharSequence(String key, CharSequence defaultValue) { CharSequence v = getCharSequence(key); return v != null ? v : defaultValue; }

    public void putInt(String key, int value) { map.put(key, value); }
    public int getInt(String key) { return getInt(key, 0); }
    public int getInt(String key, int defaultValue) { Object v = map.get(key); return v instanceof Integer ? (Integer) v : defaultValue; }
    public void putLong(String key, long value) { map.put(key, value); }
    public long getLong(String key) { return getLong(key, 0L); }
    public long getLong(String key, long defaultValue) { Object v = map.get(key); return v instanceof Long ? (Long) v : defaultValue; }
    public void putBoolean(String key, boolean value) { map.put(key, value); }
    public boolean getBoolean(String key) { return getBoolean(key, false); }
    public boolean getBoolean(String key, boolean defaultValue) { Object v = map.get(key); return v instanceof Boolean ? (Boolean) v : defaultValue; }
    public void putFloat(String key, float value) { map.put(key, value); }
    public float getFloat(String key) { return getFloat(key, 0f); }
    public float getFloat(String key, float defaultValue) { Object v = map.get(key); return v instanceof Float ? (Float) v : defaultValue; }
    public void putDouble(String key, double value) { map.put(key, value); }
    public double getDouble(String key) { return getDouble(key, 0d); }
    public double getDouble(String key, double defaultValue) { Object v = map.get(key); return v instanceof Double ? (Double) v : defaultValue; }
    public void putShort(String key, short value) { map.put(key, value); }
    public short getShort(String key) { return getShort(key, (short) 0); }
    public short getShort(String key, short defaultValue) { Object v = map.get(key); return v instanceof Short ? (Short) v : defaultValue; }
    public void putByte(String key, byte value) { map.put(key, value); }
    public byte getByte(String key) { return getByte(key, (byte) 0); }
    public byte getByte(String key, byte defaultValue) { Object v = map.get(key); return v instanceof Byte ? (Byte) v : defaultValue; }
    public void putChar(String key, char value) { map.put(key, value); }
    public char getChar(String key) { return getChar(key, (char) 0); }
    public char getChar(String key, char defaultValue) { Object v = map.get(key); return v instanceof Character ? (Character) v : defaultValue; }

    public void putSerializable(String key, Serializable value) { map.put(key, value); }
    public Serializable getSerializable(String key) { Object v = map.get(key); return v instanceof Serializable ? (Serializable) v : null; }
    public void putParcelable(String key, Parcelable value) { map.put(key, value); }
    public Parcelable getParcelable(String key) { Object v = map.get(key); return v instanceof Parcelable ? (Parcelable) v : null; }
    public void putParcelableArray(String key, Parcelable[] value) { map.put(key, value); }
    public Parcelable[] getParcelableArray(String key) { Object v = map.get(key); return v instanceof Parcelable[] ? (Parcelable[]) v : null; }
    public void putParcelableArrayList(String key, ArrayList<? extends Parcelable> value) { map.put(key, value); }
    public ArrayList<Parcelable> getParcelableArrayList(String key) { Object v = map.get(key); return v instanceof ArrayList ? (ArrayList<Parcelable>) v : null; }
    public void putSparseParcelableArray(String key, Object value) { map.put(key, value); }
    public void putStringArray(String key, String[] value) { map.put(key, value); }
    public String[] getStringArray(String key) { Object v = map.get(key); return v instanceof String[] ? (String[]) v : null; }
    public void putStringArrayList(String key, ArrayList<String> value) { map.put(key, value); }
    public ArrayList<String> getStringArrayList(String key) { Object v = map.get(key); return v instanceof ArrayList ? (ArrayList<String>) v : null; }
    public void putIntegerArrayList(String key, ArrayList<Integer> value) { map.put(key, value); }
    public ArrayList<Integer> getIntegerArrayList(String key) { Object v = map.get(key); return v instanceof ArrayList ? (ArrayList<Integer>) v : null; }
    public void putCharSequenceArrayList(String key, ArrayList<CharSequence> value) { map.put(key, value); }
    public ArrayList<CharSequence> getCharSequenceArrayList(String key) { Object v = map.get(key); return v instanceof ArrayList ? (ArrayList<CharSequence>) v : null; }
    public void putBinder(String key, IBinder value) { map.put(key, value); }
    public IBinder getBinder(String key) { Object v = map.get(key); return v instanceof IBinder ? (IBinder) v : null; }

    public void putBundle(String key, Bundle value) { map.put(key, value); }
    public Bundle getBundle(String key) { Object v = map.get(key); return v instanceof Bundle ? (Bundle) v : null; }
    public void putByteArray(String key, byte[] value) { map.put(key, value); }
    public byte[] getByteArray(String key) { Object v = map.get(key); return v instanceof byte[] ? (byte[]) v : null; }
    public void putIntArray(String key, int[] value) { map.put(key, value); }
    public int[] getIntArray(String key) { Object v = map.get(key); return v instanceof int[] ? (int[]) v : null; }
    public void putLongArray(String key, long[] value) { map.put(key, value); }
    public long[] getLongArray(String key) { Object v = map.get(key); return v instanceof long[] ? (long[]) v : null; }
    public void putFloatArray(String key, float[] value) { map.put(key, value); }
    public float[] getFloatArray(String key) { Object v = map.get(key); return v instanceof float[] ? (float[]) v : null; }
    public void putDoubleArray(String key, double[] value) { map.put(key, value); }
    public double[] getDoubleArray(String key) { Object v = map.get(key); return v instanceof double[] ? (double[]) v : null; }
    public void putBooleanArray(String key, boolean[] value) { map.put(key, value); }
    public boolean[] getBooleanArray(String key) { Object v = map.get(key); return v instanceof boolean[] ? (boolean[]) v : null; }
    public void putCharArray(String key, char[] value) { map.put(key, value); }
    public char[] getCharArray(String key) { Object v = map.get(key); return v instanceof char[] ? (char[]) v : null; }
    public void putShortArray(String key, short[] value) { map.put(key, value); }
    public short[] getShortArray(String key) { Object v = map.get(key); return v instanceof short[] ? (short[]) v : null; }

    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public void readFromParcel(Parcel parcel) {}
    public Object clone() { return new Bundle(this); }
    public String toString() { return "Bundle" + map; }
}
