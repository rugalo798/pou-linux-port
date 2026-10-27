package android.view.inputmethod;

import android.os.Parcel;
import android.os.Parcelable;

/** Input method subtype stub. */
public final class InputMethodSubtype implements Parcelable {
    public static final int SUBTYPE_ID_NONE = 0;
    private final int id;
    private final int nameId;
    private final String locale;
    private final String mode;
    private final String extraValue;
    private final boolean auxiliary;
    private final boolean overridesImplicitlyEnabledSubtype;
    private final int subtypeId;

    public InputMethodSubtype(int id, int nameId, String locale, String mode, String extraValue, boolean isAuxiliary, boolean overridesImplicitlyEnabledSubtype, int subtypeId) {
        this.id = id; this.nameId = nameId; this.locale = locale; this.mode = mode;
        this.extraValue = extraValue; this.auxiliary = isAuxiliary;
        this.overridesImplicitlyEnabledSubtype = overridesImplicitlyEnabledSubtype; this.subtypeId = subtypeId;
    }
    public InputMethodSubtype(int id, int nameId, String locale, String mode, String extraValue, boolean isAuxiliary, boolean overridesImplicitlyEnabledSubtype) {
        this(id, nameId, locale, mode, extraValue, isAuxiliary, overridesImplicitlyEnabledSubtype, 0);
    }
    public int getIconResId() { return id; }
    public int getNameResId() { return nameId; }
    public String getLocale() { return locale; }
    public String getMode() { return mode; }
    public String getExtraValue() { return extraValue; }
    public boolean isAuxiliary() { return auxiliary; }
    public boolean overridesImplicitlyEnabledSubtype() { return overridesImplicitlyEnabledSubtype; }
    public boolean containsExtraValueKey(String key) { return false; }
    public String getExtraValueOf(String key) { return null; }
    public int hashCode() { return id; }
    public boolean equals(Object o) { return o instanceof InputMethodSubtype && ((InputMethodSubtype) o).id == id; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}
    public String toString() { return "InputMethodSubtype(" + locale + ")"; }

    public static final Parcelable.Creator<InputMethodSubtype> CREATOR = new Parcelable.Creator<InputMethodSubtype>() {
        public InputMethodSubtype createFromParcel(Parcel source) { return null; }
        public InputMethodSubtype[] newArray(int size) { return new InputMethodSubtype[size]; }
    };
}
