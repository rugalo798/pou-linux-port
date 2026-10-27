package android.view.inputmethod;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/** Editor info. */
public class EditorInfo implements Parcelable {
    public static final int IME_MASK_ACTION = 0x000000ff;
    public static final int IME_ACTION_UNSPECIFIED = 0x00000000;
    public static final int IME_ACTION_NONE = 0x00000001;
    public static final int IME_ACTION_GO = 0x00000002;
    public static final int IME_ACTION_SEARCH = 0x00000003;
    public static final int IME_ACTION_SEND = 0x00000004;
    public static final int IME_ACTION_NEXT = 0x00000005;
    public static final int IME_ACTION_DONE = 0x00000006;
    public static final int IME_ACTION_PREVIOUS = 0x00000007;
    public static final int IME_FLAG_NO_EXTRACT_UI = 0x10000000;
    public static final int IME_FLAG_NO_ACCESSORY_ACTION = 0x20000000;
    public static final int IME_FLAG_NO_ENTER_ACTION = 0x40000000;
    public static final int IME_FLAG_NAVIGATE_NEXT = 0x08000000;
    public static final int IME_FLAG_NAVIGATE_PREVIOUS = 0x04000000;
    public static final int IME_FLAG_FORCE_ASCII = 0x80000000;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 0x01000000;
    public static final int IME_NULL = 0;

    public int inputType;
    public int imeOptions;
    public String actionLabel;
    public int actionId;
    public int initialSelStart;
    public int initialSelEnd;
    public int initialCapsMode;
    public CharSequence hintText;
    public CharSequence label;
    public String packageName;
    public String fieldName;
    public String fieldId;
    public Bundle extras;

    public EditorInfo() {}

    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}

    public static final Parcelable.Creator<EditorInfo> CREATOR = new Parcelable.Creator<EditorInfo>() {
        public EditorInfo createFromParcel(Parcel source) { return new EditorInfo(); }
        public EditorInfo[] newArray(int size) { return new EditorInfo[size]; }
    };
}
