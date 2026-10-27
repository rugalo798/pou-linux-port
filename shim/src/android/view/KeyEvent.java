package android.view;

/** KeyEvent constants. */
public class KeyEvent {
    public static final int ACTION_DOWN = 0;
    public static final int ACTION_UP = 1;
    public static final int KEYCODE_UNKNOWN = 0;
    public static final int KEYCODE_HOME = 3;
    public static final int KEYCODE_BACK = 4;
    public static final int KEYCODE_CALL = 5;
    public static final int KEYCODE_ENDCALL = 6;
    public static final int KEYCODE_0 = 7;
    public static final int KEYCODE_1 = 8;
    public static final int KEYCODE_2 = 9;
    public static final int KEYCODE_3 = 10;
    public static final int KEYCODE_4 = 11;
    public static final int KEYCODE_5 = 12;
    public static final int KEYCODE_6 = 13;
    public static final int KEYCODE_7 = 14;
    public static final int KEYCODE_8 = 15;
    public static final int KEYCODE_9 = 16;
    public static final int KEYCODE_STAR = 17;
    public static final int KEYCODE_POUND = 18;
    public static final int KEYCODE_DPAD_UP = 19;
    public static final int KEYCODE_DPAD_DOWN = 20;
    public static final int KEYCODE_DPAD_LEFT = 21;
    public static final int KEYCODE_DPAD_RIGHT = 22;
    public static final int KEYCODE_DPAD_CENTER = 23;
    public static final int KEYCODE_VOLUME_UP = 24;
    public static final int KEYCODE_VOLUME_DOWN = 25;
    public static final int KEYCODE_POWER = 26;
    public static final int KEYCODE_CAMERA = 27;
    public static final int KEYCODE_CLEAR = 28;
    public static final int KEYCODE_A = 29;
    public static final int KEYCODE_B = 30;
    public static final int KEYCODE_C = 31;
    public static final int KEYCODE_D = 32;
    public static final int KEYCODE_E = 33;
    public static final int KEYCODE_F = 34;
    public static final int KEYCODE_G = 35;
    public static final int KEYCODE_H = 36;
    public static final int KEYCODE_I = 37;
    public static final int KEYCODE_J = 38;
    public static final int KEYCODE_K = 39;
    public static final int KEYCODE_L = 40;
    public static final int KEYCODE_M = 41;
    public static final int KEYCODE_N = 42;
    public static final int KEYCODE_O = 43;
    public static final int KEYCODE_P = 44;
    public static final int KEYCODE_Q = 45;
    public static final int KEYCODE_R = 46;
    public static final int KEYCODE_S = 47;
    public static final int KEYCODE_T = 48;
    public static final int KEYCODE_U = 49;
    public static final int KEYCODE_V = 50;
    public static final int KEYCODE_W = 51;
    public static final int KEYCODE_X = 52;
    public static final int KEYCODE_Y = 53;
    public static final int KEYCODE_Z = 54;
    public static final int KEYCODE_COMMA = 55;
    public static final int KEYCODE_PERIOD = 56;
    public static final int KEYCODE_ALT_LEFT = 57;
    public static final int KEYCODE_ALT_RIGHT = 58;
    public static final int KEYCODE_SHIFT_LEFT = 59;
    public static final int KEYCODE_SHIFT_RIGHT = 60;
    public static final int KEYCODE_TAB = 61;
    public static final int KEYCODE_SPACE = 62;
    public static final int KEYCODE_ENTER = 66;
    public static final int KEYCODE_DEL = 67;
    public static final int KEYCODE_MENU = 82;
    public static final int KEYCODE_ESCAPE = 111;
    public static final int KEYCODE_FORWARD_DEL = 112;
    public static final int KEYCODE_CTRL_LEFT = 113;
    public static final int KEYCODE_CTRL_RIGHT = 114;
    public static final int KEYCODE_CAPS_LOCK = 115;
    public static final int KEYCODE_META_LEFT = 117;
    public static final int KEYCODE_META_RIGHT = 118;
    public static final int KEYCODE_MOVE_HOME = 122;
    public static final int KEYCODE_MOVE_END = 123;
    public static final int KEYCODE_INSERT = 124;
    public static final int KEYCODE_F1 = 131;
    public static final int KEYCODE_F2 = 132;
    public static final int KEYCODE_F3 = 133;
    public static final int KEYCODE_F4 = 134;
    public static final int KEYCODE_F5 = 135;
    public static final int KEYCODE_F6 = 136;
    public static final int KEYCODE_F7 = 137;
    public static final int KEYCODE_F8 = 138;
    public static final int KEYCODE_F9 = 139;
    public static final int KEYCODE_F10 = 140;
    public static final int KEYCODE_F11 = 141;
    public static final int KEYCODE_F12 = 142;

    private final int action, keyCode, repeatCount;
    private final long downTime, eventTime;

    public KeyEvent(int action, int code) { this.action = action; this.keyCode = code; this.repeatCount = 0; this.downTime = 0; this.eventTime = 0; }
    public KeyEvent(long downTime, long eventTime, int action, int code, int repeat) {
        this.downTime = downTime; this.eventTime = eventTime; this.action = action; this.keyCode = code; this.repeatCount = repeat;
    }
    public KeyEvent(long downTime, long eventTime, int action, int code, int repeat, int metaState) { this(downTime, eventTime, action, code, repeat); }
    public KeyEvent(long time, long eventTime, int action, int code, int repeat, int metaState, int deviceId, int scanCode) { this(time, eventTime, action, code, repeat); }
    public KeyEvent(long time, long eventTime, int action, int code, int repeat, int metaState, int deviceId, int scanCode, int flags) { this(time, eventTime, action, code, repeat); }
    public KeyEvent(long time, long eventTime, int action, int code, int repeat, int metaState, int deviceId, int scanCode, int flags, int source) { this(time, eventTime, action, code, repeat); }

    public int getAction() { return action; }
    public int getKeyCode() { return keyCode; }
    public int getRepeatCount() { return repeatCount; }
    public long getEventTime() { return eventTime; }
    public long getDownTime() { return downTime; }
    public int getUnicodeChar() { return 0; }
    public int getUnicodeChar(int metaState) { return 0; }
    public boolean isCtrlPressed() { return false; }
    public boolean isAltPressed() { return false; }
    public boolean isShiftPressed() { return false; }
    public boolean isMetaPressed() { return false; }
    public int getMetaState() { return 0; }
    public int getScanCode() { return 0; }
    public int getSource() { return 0; }
    public int getDeviceId() { return 0; }
    public int getFlags() { return 0; }
    public char getDisplayLabel() { return 0; }
    public boolean isPrintingKey() { return false; }
    public boolean isSystem() { return false; }
    public static boolean isModifierKey(int keyCode) { return false; }
    public static int getModifierMetaStateMask() { return 0; }
    public static int getMaxKeyCode() { return 300; }
    public static String keyCodeToString(int keyCode) { return "KEYCODE_" + keyCode; }
    public static int keyCodeFromString(String symbolicName) { return 0; }
}
