package android.bluetooth;

/** BLE scanner stub. */
public final class BluetoothLeScanner {
    public void startScan(ScanCallback callback) {}
    public void startScan(java.util.List<ScanFilter> filters, ScanSettings settings, ScanCallback callback) {}
    public void stopScan(ScanCallback callback) {}
    public boolean isScanning() { return false; }
}
