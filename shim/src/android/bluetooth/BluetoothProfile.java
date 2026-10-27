package android.bluetooth;

/** Bluetooth profile base. */
public interface BluetoothProfile {
    int STATE_DISCONNECTED = 0;
    int STATE_CONNECTING = 1;
    int STATE_CONNECTED = 2;
    int STATE_DISCONNECTING = 3;
    int HEADSET = 1;
    int A2DP = 2;
    int HEALTH = 3;
    int HID_HOST = 4;
    int PAN = 5;
    int PBAP = 6;
    int GATT = 7;
    int GATT_SERVER = 8;
    int MAP = 9;
    int SAP = 10;
    int AVRCP = 11;
    int HEARING_AID = 21;

    java.util.List<BluetoothDevice> getConnectedDevices();
    java.util.List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states);
    int getConnectionState(BluetoothDevice device);

    interface ServiceListener {
        void onServiceConnected(int profile, BluetoothProfile proxy);
        void onServiceDisconnected(int profile);
    }
}
