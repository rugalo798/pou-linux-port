package android.hardware;

/** Sensor manager stub. */
public class SensorManager {
    public static final int SENSOR_TYPE_ACCELEROMETER = 1;
    public static final int SENSOR_TYPE_MAGNETIC_FIELD = 2;
    public static final int SENSOR_TYPE_ORIENTATION = 3;
    public static final int SENSOR_TYPE_GYROSCOPE = 4;
    public static final int SENSOR_TYPE_LIGHT = 5;
    public static final int SENSOR_TYPE_PRESSURE = 6;
    public static final int SENSOR_TYPE_TEMPERATURE = 7;
    public static final int SENSOR_TYPE_PROXIMITY = 8;
    public static final int SENSOR_TYPE_GRAVITY = 9;
    public static final int SENSOR_TYPE_LINEAR_ACCELERATION = 10;
    public static final int SENSOR_TYPE_ROTATION_VECTOR = 11;
    public static final int SENSOR_TYPE_RELATIVE_HUMIDITY = 12;
    public static final int SENSOR_TYPE_AMBIENT_TEMPERATURE = 13;
    public static final int SENSOR_TYPE_MAGNETIC_FIELD_UNCALIBRATED = 14;
    public static final int SENSOR_TYPE_GAME_ROTATION_VECTOR = 15;
    public static final int SENSOR_TYPE_GYROSCOPE_UNCALIBRATED = 16;
    public static final int SENSOR_TYPE_SIGNIFICANT_MOTION = 17;
    public static final int SENSOR_TYPE_STEP_DETECTOR = 18;
    public static final int SENSOR_TYPE_STEP_COUNTER = 19;
    public static final int SENSOR_TYPE_GEOMAGNETIC_ROTATION_VECTOR = 20;
    public static final int SENSOR_TYPE_HEART_RATE = 21;
    public static final int SENSOR_TYPE_POSE_6DOF = 28;
    public static final int SENSOR_TYPE_STATIONARY_DETECT = 29;
    public static final int SENSOR_TYPE_MOTION_DETECT = 30;
    public static final int SENSOR_TYPE_HEART_BEAT = 31;
    public static final int SENSOR_TYPE_LOW_LATENCY_OFFBODY_DETECT = 34;
    public static final int SENSOR_TYPE_ACCELEROMETER_UNCALIBRATED = 35;
    public static final int SENSOR_TYPE_ALL = -1;
    public static final int SENSOR_DELAY_NORMAL = 3;
    public static final int SENSOR_DELAY_UI = 2;
    public static final int SENSOR_DELAY_GAME = 1;
    public static final int SENSOR_DELAY_FASTEST = 0;
    public static final int AXIS_X = 1;
    public static final int AXIS_Y = 2;
    public static final int AXIS_Z = 3;
    public static final int AXIS_MINUS_X = 129;
    public static final int AXIS_MINUS_Y = 130;
    public static final int AXIS_MINUS_Z = 131;
    public static final int REPORTING_MODE_CONTINUOUS = 0;
    public static final int REPORTING_MODE_ON_CHANGE = 1;
    public static final int REPORTING_MODE_ONE_SHOT = 2;
    public static final int REPORTING_MODE_SPECIAL_TRIGGER = 3;

    public Sensor getDefaultSensor(int type) { return null; }
    public Sensor getDefaultSensor(int type, boolean wakeUp) { return null; }
    public java.util.List<Sensor> getSensorList(int type) { return new java.util.ArrayList<>(); }
    public boolean registerListener(SensorEventListener listener, Sensor sensor, int samplingPeriodUs) { return false; }
    public boolean registerListener(SensorEventListener listener, Sensor sensor, int samplingPeriodUs, int maxReportLatencyUs) { return false; }
    public boolean registerListener(SensorEventListener listener, Sensor sensor, int samplingPeriodUs, android.os.Handler handler) { return false; }
    public boolean registerListener(SensorEventListener listener, Sensor sensor, int samplingPeriodUs, int maxReportLatencyUs, android.os.Handler handler) { return false; }
    public void unregisterListener(SensorEventListener listener) {}
    public void unregisterListener(SensorEventListener listener, Sensor sensor) {}
    public boolean flush(SensorEventListener listener) { return false; }
    public static float getOrientation(float[] R, float[] values) { return 0f; }
    public static boolean getRotationMatrix(float[] R, float[] I, float[] gravity, float[] geomagnetic) { return false; }
    public static void getQuaternionFromVector(float[] Q, float[] rv) {}
    public static void getRotationMatrixFromVector(float[] R, float[] rotationVector) {}
    public static void remapCoordinateSystem(float[] inR, int X, int Y, float[] outR) {}
}
