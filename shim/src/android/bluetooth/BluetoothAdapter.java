package android.bluetooth;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** Bluetooth adapter stub (no hardware). */
public final class BluetoothAdapter {
    public static final String ACTION_STATE_CHANGED = "android.bluetooth.adapter.action.STATE_CHANGED";
    public static final String ACTION_REQUEST_ENABLE = "android.bluetooth.adapter.action.REQUEST_ENABLE";
    public static final String ACTION_REQUEST_DISCOVERABLE = "android.bluetooth.adapter.action.REQUEST_DISCOVERABLE";
    public static final String ACTION_SCAN_MODE_CHANGED = "android.bluetooth.adapter.action.SCAN_MODE_CHANGED";
    public static final String ACTION_LOCAL_NAME_CHANGED = "android.bluetooth.adapter.action.LOCAL_NAME_CHANGED";
    public static final String ACTION_DISCOVERY_STARTED = "android.bluetooth.adapter.action.DISCOVERY_STARTED";
    public static final String ACTION_DISCOVERY_FINISHED = "android.bluetooth.adapter.action.DISCOVERY_FINISHED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED";
    public static final String EXTRA_STATE = "android.bluetooth.adapter.extra.STATE";
    public static final String EXTRA_PREVIOUS_STATE = "android.bluetooth.adapter.extra.PREVIOUS_STATE";
    public static final String EXTRA_DISCOVERABLE_DURATION = "android.bluetooth.adapter.extra.DISCOVERABLE_DURATION";
    public static final String EXTRA_SCAN_MODE = "android.bluetooth.adapter.extra.SCAN_MODE";
    public static final String EXTRA_CONNECTION_STATE = "android.bluetooth.adapter.extra.CONNECTION_STATE";
    public static final int STATE_OFF = 10;
    public static final int STATE_TURNING_ON = 11;
    public static final int STATE_ON = 12;
    public static final int STATE_TURNING_OFF = 13;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 3;
    public static final int SCAN_MODE_NONE = 20;
    public static final int SCAN_MODE_CONNECTABLE = 21;
    public static final int SCAN_MODE_CONNECTABLE_DISCOVERABLE = 23;
    public static final int ERROR = Integer.MIN_VALUE;
    public static final int DEFAULT_DISCOVERABLE_TIMEOUT = 120;

    private BluetoothAdapter() {}

    public static BluetoothAdapter getDefaultAdapter() { return null; }
    public boolean isEnabled() { return false; }
    public boolean enable() { return false; }
    public boolean disable() { return false; }
    public String getName() { return "Pou Desktop"; }
    public boolean setName(String name) { return false; }
    public String getAddress() { return "00:00:00:00:00:00"; }
    public int getState() { return STATE_OFF; }
    public int getScanMode() { return SCAN_MODE_NONE; }
    public Set<BluetoothDevice> getBondedDevices() { return new HashSet<>(); }
    public boolean startDiscovery() { return false; }
    public boolean cancelDiscovery() { return false; }
    public boolean isDiscovering() { return false; }
    public BluetoothDevice getRemoteDevice(String address) { return null; }
    public BluetoothDevice getRemoteDevice(byte[] address) { return null; }
    public BluetoothServerSocket listenUsingRfcommWithServiceRecord(String name, UUID uuid) throws IOException { return null; }
    public BluetoothServerSocket listenUsingInsecureRfcommWithServiceRecord(String name, UUID uuid) throws IOException { return null; }
    public BluetoothServerSocket listenUsingL2capWithServiceRecord(String name, java.util.UUID uuid) throws IOException { return null; }
    public BluetoothLeScanner getBluetoothLeScanner() { return null; }
    public boolean getProfileProxy(android.content.Context context, BluetoothProfile.ServiceListener listener, int profile) { return false; }
    public void closeProfileProxy(int profile, BluetoothProfile proxy) {}
    public boolean setScanMode(int mode) { return false; }
    public boolean setScanMode(int mode, int duration) { return false; }
    public int getConnectionState() { return STATE_DISCONNECTED; }
    public String toString() { return "BluetoothAdapter(disabled)"; }
}
