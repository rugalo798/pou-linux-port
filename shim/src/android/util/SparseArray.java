package android.util;

/** SparseArray. */
public class SparseArray<E> implements Cloneable {
    private int[] keys = new int[0];
    private Object[] values = new Object[0];
    private int size;

    public SparseArray() {}
    public SparseArray(int initialCapacity) {}

    public E get(int key) { return get(key, null); }
    @SuppressWarnings("unchecked")
    public E get(int key, E valueIfKeyNotFound) {
        int i = indexOfKey(key);
        return i >= 0 ? (E) values[i] : valueIfKeyNotFound;
    }
    public void put(int key, E value) {
        int i = indexOfKey(key);
        if (i >= 0) { values[i] = value; return; }
        int[] nk = new int[size + 1];
        Object[] nv = new Object[size + 1];
        int pos = 0;
        while (pos < size && keys[pos] < key) { nk[pos] = keys[pos]; nv[pos] = values[pos]; pos++; }
        nk[pos] = key; nv[pos] = value;
        for (int j = pos; j < size; j++) { nk[j + 1] = keys[j]; nv[j + 1] = values[j]; }
        keys = nk; values = nv; size++;
    }
    public void append(int key, E value) { put(key, value); }
    public void delete(int key) {
        int i = indexOfKey(key);
        if (i < 0) return;
        int[] nk = new int[size - 1];
        Object[] nv = new Object[size - 1];
        for (int j = 0, k = 0; j < size; j++) {
            if (j == i) continue;
            nk[k] = keys[j]; nv[k] = values[j]; k++;
        }
        keys = nk; values = nv; size--;
    }
    public void remove(int key) { delete(key); }
    public void removeAt(int index) { if (index >= 0 && index < size) delete(keys[index]); }
    public void clear() { keys = new int[0]; values = new Object[0]; size = 0; }
    public int size() { return size; }
    public int keyAt(int index) { return keys[index]; }
    @SuppressWarnings("unchecked")
    public E valueAt(int index) { return (E) values[index]; }
    public void setValueAt(int index, E value) { values[index] = value; }
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
    public int indexOfValue(E value) {
        for (int i = 0; i < size; i++) if (values[i] == value) return i;
        return -1;
    }
    @Override public SparseArray<E> clone() {
        SparseArray<E> c = new SparseArray<>();
        c.keys = keys.clone(); c.values = values.clone(); c.size = size;
        return c;
    }
    public String toString() { return "SparseArray(size=" + size + ")"; }
}
