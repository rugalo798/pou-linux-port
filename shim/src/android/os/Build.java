package android.os;

/** Build info for the desktop runtime. */
public class Build {
    public static final String UNKNOWN = "unknown";
    public static final String MODEL = "Linux Desktop";
    public static final String MANUFACTURER = "PouPort";
    public static final String DEVICE = "desktop";
    public static final String PRODUCT = "pou";
    public static final String BRAND = "pou";
    public static final String BOARD = "desktop";
    public static final String HARDWARE = "desktop";
    public static final String FINGERPRINT = "pou/desktop/desktop:15/1/1:user/release-keys";
    public static final String TAGS = "release-keys";
    public static final String TYPE = "user";
    public static final String ID = "1";
    public static final String DISPLAY = "1";
    public static final String BOOTLOADER = "unknown";
    public static final String HOST = "linux";
    public static final String USER = "pou";
    public static final String TIME = "0";
    public static final String SERIAL = "unknown";
    public static final String RADIO = "unknown";
    public static final String CPU_ABI = "x86_64";
    public static final String CPU_ABI2 = "";
    public static final String[] SUPPORTED_ABIS = {"x86_64"};
    public static final String[] SUPPORTED_32_BIT_ABIS = new String[0];
    public static final String[] SUPPORTED_64_BIT_ABIS = {"x86_64"};
    public static final String getSerial() { return SERIAL; }
    public static String getRadioVersion() { return RADIO; }

    public static class VERSION {
        public static final int SDK_INT = 35;
        public static final String RELEASE = "15";
        public static final String CODENAME = "REL";
        public static final String INCREMENTAL = "1";
        public static final String SECURITY_PATCH = "2026-01-01";
        public static final int PREVIEW_SDK_INT = 0;
        public static final String BASE_OS = "";
    }
    public static class VERSION_CODES {
        public static final int BASE = 1, BASE_1_1 = 2, CUPCAKE = 3, DONUT = 4, ECLAIR = 5, ECLAIR_0_1 = 6, ECLAIR_MR1 = 7, FROYO = 8, GINGERBREAD = 9, GINGERBREAD_MR1 = 10, HONEYCOMB = 11, HONEYCOMB_MR1 = 12, HONEYCOMB_MR2 = 13, ICE_CREAM_SANDWICH = 14, ICE_CREAM_SANDWICH_MR1 = 15, JELLY_BEAN = 16, JELLY_BEAN_MR1 = 17, JELLY_BEAN_MR2 = 18, KITKAT = 19, KITKAT_WATCH = 20, LOLLIPOP = 21, LOLLIPOP_MR1 = 22, M = 23, N = 24, N_MR1 = 25, O = 26, O_MR1 = 27, P = 28, Q = 29, R = 30, S = 31, S_V2 = 32, TIRAMISU = 33, UPSIDE_DOWN_CAKE = 34, VANILLA_ICE_CREAM = 35, BAKLAVA = 36;
    }
}
