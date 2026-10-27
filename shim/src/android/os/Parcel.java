package android.os;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Minimal Parcel. */
public final class Parcel {
    private final java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
    private int position;

    private Parcel() {}
    public static Parcel obtain() { return new Parcel(); }
    public void recycle() {}
    public int dataSize() { return out.size(); }
    public int dataPosition() { return position; }
    public void setDataPosition(int pos) { position = pos; }
    public void writeString(String val) { writeInt(val == null ? -1 : val.length()); if (val != null) for (char c : val.toCharArray()) writeInt(c); }
    public String readString() { int len = readInt(); if (len < 0) return null; StringBuilder sb = new StringBuilder(len); for (int i = 0; i < len; i++) sb.append((char) readInt()); return sb.toString(); }
    public void writeInt(int val) { out.write(val); out.write(val >> 8); out.write(val >> 16); out.write(val >> 24); }
    public int readInt() { return 0; }
    public void writeLong(long val) { writeInt((int) val); writeInt((int) (val >> 32)); }
    public long readLong() { return 0; }
    public void writeFloat(float val) { writeInt(Float.floatToIntBits(val)); }
    public float readFloat() { return 0; }
    public void writeDouble(double val) { writeLong(Double.doubleToLongBits(val)); }
    public double readDouble() { return 0; }
    public void writeByte(byte val) { out.write(val); }
    public byte readByte() { return 0; }
    public void writeByteArray(byte[] b) { writeInt(b == null ? -1 : b.length); if (b != null) out.write(b, 0, b.length); }
    public byte[] createByteArray() { return null; }
    public void writeStringArray(String[] val) {}
    public String[] createStringArray() { return null; }
    public void writeIntArray(int[] val) {}
    public int[] createIntArray() { return null; }
    public void writeBundle(Bundle val) {}
    public Bundle readBundle() { return null; }
    public Bundle readBundle(ClassLoader loader) { return null; }
    public void writeParcelable(Parcelable p, int parcelableFlags) {}
    public <T extends Parcelable> T readParcelable(ClassLoader loader) { return null; }
    public void writeSerializable(Serializable s) {}
    public Serializable readSerializable() { return null; }
    public void writeList(List val) {}
    public ArrayList readArrayList(ClassLoader loader) { return null; }
    public void writeMap(Map val) {}
    public HashMap readHashMap(ClassLoader loader) { return null; }
    public void writeSparseArray(Object val) {}
    public Object readSparseArray(ClassLoader loader) { return null; }
    public void writeStrongBinder(IBinder val) {}
    public IBinder readStrongBinder() { return null; }
    public void writeInterfaceToken(String interfaceName) {}
    public void enforceInterface(String interfaceName) {}
    public void appendFrom(Parcel parcel, int offset, int length) {}
    public void writeException(Exception e) {}
    public void readException() {}
    public byte[] marshall() { return out.toByteArray(); }
    public void unmarshall(byte[] data, int offset, int length) {}
    public static final Parcelable.Creator<Parcel> CREATOR = new Parcelable.Creator<Parcel>() {
        public Parcel createFromParcel(Parcel source) { return new Parcel(); }
        public Parcel[] newArray(int size) { return new Parcel[size]; }
    };
}
