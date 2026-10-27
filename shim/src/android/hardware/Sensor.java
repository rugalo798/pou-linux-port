package android.hardware;

/** Sensor metadata. */
public class Sensor {
    public static final int TYPE_ACCELEROMETER = 1;
    public static final int TYPE_MAGNETIC_FIELD = 2;
    public static final int TYPE_ORIENTATION = 3;
    public static final int TYPE_GYROSCOPE = 4;
    public static final int TYPE_LIGHT = 5;
    public static final int TYPE_PRESSURE = 6;
    public static final int TYPE_TEMPERATURE = 7;
    public static final int TYPE_PROXIMITY = 8;
    public static final int TYPE_GRAVITY = 9;
    public static final int TYPE_LINEAR_ACCELERATION = 10;
    public static final int TYPE_ROTATION_VECTOR = 11;
    public static final int TYPE_RELATIVE_HUMIDITY = 12;
    public static final int TYPE_AMBIENT_TEMPERATURE = 13;
    public static final int TYPE_MAGNETIC_FIELD_UNCALIBRATED = 14;
    public static final int TYPE_GAME_ROTATION_VECTOR = 15;
    public static final int TYPE_GYROSCOPE_UNCALIBRATED = 16;
    public static final int TYPE_SIGNIFICANT_MOTION = 17;
    public static final int TYPE_STEP_DETECTOR = 18;
    public static final int TYPE_STEP_COUNTER = 19;
    public static final int TYPE_GEOMAGNETIC_ROTATION_VECTOR = 20;
    public static final int TYPE_HEART_RATE = 21;
    public static final int TYPE_POSE_6DOF = 28;
    public static final int TYPE_STATIONARY_DETECT = 29;
    public static final int TYPE_MOTION_DETECT = 30;
    public static final int TYPE_HEART_BEAT = 31;
    public static final int TYPE_LOW_LATENCY_OFFBODY_DETECT = 34;
    public static final int TYPE_ACCELEROMETER_UNCALIBRATED = 35;
    public static final int TYPE_ALL = -1;

    private final int type;
    private final String name;
    private final String vendor;
    private final int version;
    private final float maxRange;
    private final float resolution;
    private final float power;
    private final int minDelay;
    private final int maxDelay;
    private final int reportingMode;
    private final boolean wakeUp;

    public Sensor(int type, String name) {
        this.type = type; this.name = name; this.vendor = "Pou Desktop"; this.version = 1;
        this.maxRange = 10f; this.resolution = 0.01f; this.power = 0.1f;
        this.minDelay = 0; this.maxDelay = 0; this.reportingMode = 0; this.wakeUp = false;
    }

    public int getType() { return type; }
    public String getName() { return name; }
    public String getVendor() { return vendor; }
    public int getVersion() { return version; }
    public float getMaximumRange() { return maxRange; }
    public float getResolution() { return resolution; }
    public float getPower() { return power; }
    public int getMinDelay() { return minDelay; }
    public int getMaxDelay() { return maxDelay; }
    public int getReportingMode() { return reportingMode; }
    public boolean isWakeUpSensor() { return wakeUp; }
    public String getStringType() { return "Sensor(" + type + ")"; }
    public int getId() { return 0; }
    public String toString() { return "Sensor(" + name + ")"; }
}
