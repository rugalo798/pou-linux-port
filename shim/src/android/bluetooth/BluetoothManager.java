package android.bluetooth;

import android.content.Context;

/** Bluetooth manager stub. */
public class BluetoothManager {
    private final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

    public BluetoothManager() {}

    public BluetoothAdapter getAdapter() { return adapter; }
    public java.util.List<BluetoothDevice> getConnectedDevices(int profile) { return new java.util.ArrayList<>(); }
    public java.util.List<BluetoothDevice> getDevicesMatchingConnectionStates(int profile, int[] states) { return new java.util.ArrayList<>(); }
    public int getConnectionState(BluetoothDevice device, int profile) { return 0; }
    public Object getBluetoothGatt() { return null; }
}
