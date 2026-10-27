package android.util;

/** SparseBooleanArray. */
public class SparseBooleanArray implements Cloneable {
    private int[] keys = new int[0];
    private boolean[] values = new boolean[0];
    private int size;

    public SparseBooleanArray() {}
    public SparseBooleanArray(int initialCapacity) {}

    public boolean get(int key) { return get(key, false); }
    public boolean get(int key, boolean valueIfKeyNotFound) {
        int i = indexOfKey(key);
        return i >= 0 ? values[i] : valueIfKeyNotFound;
    }
    public void put(int key, boolean value) {
        int i = indexOfKey(key);
        if (i >= 0) { values[i] = value; return; }
        int[] nk = new int[size + 1];
        boolean[] nv = new boolean[size + 1];
        int pos = 0;
        while (pos < size && keys[pos] < key) { nk[pos] = keys[pos]; nv[pos] = values[pos]; pos++; }
        nk[pos] = key; nv[pos] = value;
        for (int j = pos; j < size; j++) { nk[j + 1] = keys[j]; nv[j + 1] = values[j]; }
        keys = nk; values = nv; size++;
    }
    public void append(int key, boolean value) { put(key, value); }
    public void delete(int key) {
        int i = indexOfKey(key);
        if (i < 0) return;
        int[] nk = new int[size - 1];
        boolean[] nv = new boolean[size - 1];
        for (int j = 0, k = 0; j < size; j++) {
            if (j == i) continue;
            nk[k] = keys[j]; nv[k] = values[j]; k++;
        }
        keys = nk; values = nv; size--;
    }
    public void removeAt(int index) { if (index >= 0 && index < size) delete(keys[index]); }
    public void clear() { keys = new int[0]; values = new boolean[0]; size = 0; }
    public int size() { return size; }
    public int keyAt(int index) { return keys[index]; }
    public boolean valueAt(int index) { return values[index]; }
    public void setValueAt(int index, boolean value) { values[index] = value; }
    public int indexOfKey(int key) {
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int midVal = keys[mid];
            if (midVal < key) lo = mid + 1;
            else if (midVal > key) hi = mid - 1;
            else return mid;
        }
        return -1;
    }
    public int indexOfValue(boolean value) {
        for (int i = 0; i < size; i++) if (values[i] == value) return i;
        return -1;
    }
    @Override public SparseBooleanArray clone() {
        SparseBooleanArray c = new SparseBooleanArray();
        c.keys = keys.clone(); c.values = values.clone(); c.size = size;
        return c;
    }
    public String toString() { return "SparseBooleanArray(size=" + size + ")"; }
}
