package android.bluetooth;

/** BLE scan settings. */
public final class ScanSettings {
    public static final int SCAN_MODE_LOW_POWER = 0;
    public static final int SCAN_MODE_BALANCED = 1;
    public static final int SCAN_MODE_LOW_LATENCY = 2;
    public static final int SCAN_MODE_AMBIENT_DISCOVERY = 3;
    public static final int SCAN_MODE_SCREEN_OFF = 4;
    public static final int SCAN_MODE_SCREEN_OFF_BALANCED = 5;
    public static final int CALLBACK_TYPE_ALL_MATCHES = 1;
    public static final int CALLBACK_TYPE_FIRST_MATCH = 2;
    public static final int CALLBACK_TYPE_MATCH_LOST = 4;
    public static final int SCAN_RESULT_TYPE_FULL = 0;
    public static final int SCAN_RESULT_TYPE_ABBREVIATED = 1;
    public static final int MATCH_MODE_AGGRESSIVE = 1;
    public static final int MATCH_MODE_STICKY = 2;
    public static final int MATCH_NUM_ONE_ADVERTISEMENT = 1;
    public static final int MATCH_NUM_FEW_ADVERTISEMENT = 2;
    public static final int MATCH_NUM_MAX_ADVERTISEMENT = 3;
    public static final int PHY_LE_ALL_SUPPORTED = 255;

    public static final class Builder {
        public Builder setScanMode(int scanMode) { return this; }
        public Builder setCallbackType(int callbackType) { return this; }
        public Builder setScanResultType(int scanResultType) { return this; }
        public Builder setReportDelay(long reportDelayMillis) { return this; }
        public Builder setMatchMode(int matchMode) { return this; }
        public Builder setNumOfMatches(int numOfMatches) { return this; }
        public Builder setLegacy(boolean legacy) { return this; }
        public Builder setPhy(int phy) { return this; }
        public ScanSettings build() { return new ScanSettings(); }
    }
}
