package android.view.inputmethod;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/** Input method manager stub. */
public final class InputMethodManager {
    public static final int SHOW_IMPLICIT = 1;
    public static final int SHOW_FORCED = 2;
    public static final int HIDE_IMPLICIT_ONLY = 1;
    public static final int HIDE_NOT_ALWAYS = 2;
    public static final int RESULT_UNCHANGED_SHOWN = 0;
    public static final int RESULT_UNCHANGED_HIDDEN = 1;
    public static final int RESULT_SHOWN = 2;
    public static final int RESULT_HIDDEN = 3;

    public InputMethodManager() {}

    public static InputMethodManager getInstance(Context context) { return new InputMethodManager(); }

    public boolean showSoftInput(View view, int flags) { return false; }
    public boolean showSoftInput(View view, int flags, ResultReceiver resultReceiver) { return false; }
    public boolean hideSoftInputFromWindow(IBinder windowToken, int flags) { return false; }
    public boolean hideSoftInputFromWindow(IBinder windowToken, int flags, ResultReceiver resultReceiver) { return false; }
    public void toggleSoftInput(int showFlags, int hideFlags) {}
    public void toggleSoftInputFromWindow(IBinder windowToken, int showFlags, int hideFlags) {}
    public boolean isActive() { return false; }
    public boolean isActive(View view) { return false; }
    public boolean isAcceptingText() { return false; }
    public boolean isInputMethodSuppressingSpellChecker() { return false; }
    public void restartInput(View view) {}
    public void updateSelection(View view, int selStart, int selEnd, int candidatesStart, int candidatesEnd) {}
    public void showStatusIcon(IBinder imeToken, String packageName, int iconId) {}
    public void hideStatusIcon(IBinder imeToken) {}
    public List<InputMethodInfo> getInputMethodList() { return new ArrayList<>(); }
    public List<InputMethodInfo> getEnabledInputMethodList() { return new ArrayList<>(); }
    public List<InputMethodSubtype> getEnabledInputMethodSubtypeList(InputMethodInfo imi, boolean allowsImplicitlySelectedSubtypes) { return new ArrayList<>(); }
    public InputMethodInfo getCurrentInputMethodInfo() { return null; }
    public boolean switchToNextInputMethod(boolean onlyCurrentIme) { return false; }
    public boolean shouldOfferSwitchingToNextInputMethod() { return false; }
    public void setInputMethod(IBinder token, String id) {}
    public void setInputMethodAndSubtype(IBinder token, String id, InputMethodSubtype subtype) {}
    public void sendAppPrivateCommand(View view, String action, Bundle data) {}
    public InputMethodSubtype getCurrentInputMethodSubtype() { return null; }
    public void notifyUserAction() {}
    public void setInputMethodAndSubtype(IBinder token, String id, int subtypeId) {}
    public boolean isCursorAnchorInfoEnabled() { return false; }
    public void setCursorAnchorInfoEnabled(boolean enabled) {}
    public void setUpdateCursorAnchorInfoMode(int flags) {}
    public void viewClicked(View view) {}
    public void showSoftInputFromInputMethod(IBinder token, int flags) {}
    public void hideSoftInputFromInputMethod(IBinder token, int flags) {}
    public void dispatchKeyEventFromInputMethod(View targetView, android.view.KeyEvent event) {}
}
