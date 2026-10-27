package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

/** BLE scan result. */
public final class ScanResult implements Parcelable {
    public BluetoothDevice getDevice() { return null; }
    public ScanRecord getScanRecord() { return null; }
    public int getRssi() { return 0; }
    public long getTimestampNanos() { return 0; }
    public boolean isConnectable() { return false; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public static final Parcelable.Creator<ScanResult> CREATOR = new Parcelable.Creator<ScanResult>() {
        public ScanResult createFromParcel(Parcel source) { return null; }
        public ScanResult[] newArray(int size) { return new ScanResult[size]; }
    };
}
