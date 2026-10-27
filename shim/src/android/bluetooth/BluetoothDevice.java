package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.util.UUID;

/** Bluetooth device stub. */
public final class BluetoothDevice implements Parcelable {
    public static final String ACTION_FOUND = "android.bluetooth.device.action.FOUND";
    public static final String ACTION_BOND_STATE_CHANGED = "android.bluetooth.device.action.BOND_STATE_CHANGED";
    public static final String ACTION_PAIRING_REQUEST = "android.bluetooth.device.action.PAIRING_REQUEST";
    public static final String ACTION_UUID = "android.bluetooth.device.action.UUID";
    public static final String ACTION_NAME_CHANGED = "android.bluetooth.device.action.NAME_CHANGED";
    public static final String ACTION_ACL_CONNECTED = "android.bluetooth.device.action.ACL_CONNECTED";
    public static final String ACTION_ACL_DISCONNECTED = "android.bluetooth.device.action.ACL_DISCONNECTED";
    public static final String ACTION_ACL_DISCONNECT_REQUESTED = "android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED";
    public static final String EXTRA_DEVICE = "android.bluetooth.device.extra.DEVICE";
    public static final String EXTRA_NAME = "android.bluetooth.device.extra.NAME";
    public static final String EXTRA_RSSI = "android.bluetooth.device.extra.RSSI";
    public static final String EXTRA_BOND_STATE = "android.bluetooth.device.extra.BOND_STATE";
    public static final String EXTRA_PREVIOUS_BOND_STATE = "android.bluetooth.device.extra.PREVIOUS_BOND_STATE";
    public static final String EXTRA_PAIRING_VARIANT = "android.bluetooth.device.extra.PAIRING_VARIANT";
    public static final String EXTRA_PAIRING_KEY = "android.bluetooth.device.extra.PAIRING_KEY";
    public static final int BOND_NONE = 10;
    public static final int BOND_BONDING = 11;
    public static final int BOND_BONDED = 12;
    public static final int ERROR = Integer.MIN_VALUE;
    public static final int DEVICE_TYPE_UNKNOWN = 0;
    public static final int DEVICE_TYPE_CLASSIC = 1;
    public static final int DEVICE_TYPE_LE = 2;
    public static final int DEVICE_TYPE_DUAL = 3;
    public static final int PAIRING_VARIANT_PIN = 0;
    public static final int PAIRING_VARIANT_PASSKEY = 1;
    public static final int PAIRING_VARIANT_PASSKEY_CONFIRMATION = 2;
    public static final int PAIRING_VARIANT_CONSENT = 3;
    public static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
    public static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
    public static final int PAIRING_VARIANT_OOB_CONSENT = 6;

    private final String address;
    private final String name;

    public BluetoothDevice(String address) { this.address = address; this.name = address; }
    public String getAddress() { return address; }
    public String getName() { return name; }
    public int getBondState() { return BOND_NONE; }
    public int getType() { return DEVICE_TYPE_UNKNOWN; }
    public BluetoothClass getBluetoothClass() { return null; }
    public BluetoothSocket createRfcommSocketToServiceRecord(UUID uuid) throws IOException { return null; }
    public BluetoothSocket createInsecureRfcommSocketToServiceRecord(UUID uuid) throws IOException { return null; }
    public BluetoothSocket createL2capSocketToServiceRecord(UUID uuid) throws IOException { return null; }
    public boolean fetchUuidsWithSdp() { return false; }
    public Parcelable[] getUuids() { return new Parcelable[0]; }
    public boolean setPin(byte[] pin) { return false; }
    public boolean setPairingConfirmation(boolean confirm) { return false; }
    public void cancelPairingUserInput() {}
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public String toString() { return address; }

    public static final Parcelable.Creator<BluetoothDevice> CREATOR = new Parcelable.Creator<BluetoothDevice>() {
        public BluetoothDevice createFromParcel(Parcel source) { return new BluetoothDevice(source.readString()); }
        public BluetoothDevice[] newArray(int size) { return new BluetoothDevice[size]; }
    };
}
