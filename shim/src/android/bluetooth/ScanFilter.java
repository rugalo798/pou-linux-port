package android.bluetooth;

/** BLE scan filter. */
public final class ScanFilter {
    public static final class Builder {
        public Builder setDeviceName(String deviceName) { return this; }
        public Builder setDeviceAddress(String deviceAddress) { return this; }
        public Builder setServiceUuid(android.os.ParcelUuid serviceUuid) { return this; }
        public Builder setServiceUuid(android.os.ParcelUuid serviceUuid, android.os.ParcelUuid uuidMask) { return this; }
        public Builder setServiceData(android.os.ParcelUuid serviceDataUuid, byte[] serviceData) { return this; }
        public Builder setManufacturerData(int manufacturerId, byte[] manufacturerData) { return this; }
        public ScanFilter build() { return new ScanFilter(); }
    }
}
