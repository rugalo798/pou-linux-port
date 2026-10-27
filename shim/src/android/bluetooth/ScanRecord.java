package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

/** BLE scan record. */
public final class ScanRecord {
    public java.util.List<android.util.SparseArray<byte[]>> getManufacturerSpecificData() { return new java.util.ArrayList<>(); }
    public byte[] getManufacturerSpecificData(int manufacturerId) { return null; }
    public java.util.List<java.util.UUID> getServiceUuids() { return new java.util.ArrayList<>(); }
    public java.util.List<byte[]> getServiceData() { return new java.util.ArrayList<>(); }
    public String getDeviceName() { return null; }
    public int getAdvertiseFlags() { return 0; }
    public int getTxPowerLevel() { return 0; }
    public byte[] getBytes() { return new byte[0]; }
}
