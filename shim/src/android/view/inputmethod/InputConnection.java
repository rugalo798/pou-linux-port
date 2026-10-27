package android.view.inputmethod;

/** Input connection. */
public interface InputConnection {
    int GET_TEXT_WITH_STYLES = 1;
    int GET_EXTRACTED_TEXT_MONITOR = 1;
    int GET_CURSOR_UPDATE_MONITOR = 1;
    int GET_CURSOR_UPDATE_IMMEDIATE = 2;
    int CURSOR_UPDATE_IMMEDIATE = 1;
    int CURSOR_UPDATE_MONITOR = 2;
    int HANDWRITING_GESTURE_RESULT_UNSPECIFIED = 0;
    int HANDWRITING_GESTURE_RESULT_UNRECOGNIZED = 1;
    int HANDWRITING_GESTURE_RESULT_RECOGNIZED = 2;
    int HANDWRITING_GESTURE_RESULT_NOT_RECOGNIZED = 3;
    int HANDWRITING_GESTURE_RESULT_TOO_SLOW = 4;

    default CharSequence getTextBeforeCursor(int n, int flags) { return null; }
    default CharSequence getTextAfterCursor(int n, int flags) { return null; }
    default CharSequence getSelectedText(int flags) { return null; }
    default int getCursorCapsMode(int reqModes) { return 0; }
    default boolean commitText(CharSequence text, int newCursorPosition) { return true; }
    default boolean setComposingText(CharSequence text, int newCursorPosition) { return true; }
    default boolean setComposingRegion(int start, int end) { return true; }
    default boolean finishComposingText() { return true; }
    default boolean deleteSurroundingText(int beforeLength, int afterLength) { return true; }
    default boolean deleteSurroundingTextInCodePoints(int beforeLength, int afterLength) { return true; }
    default boolean sendKeyEvent(android.view.KeyEvent event) { return true; }
    default boolean performEditorAction(int editorAction) { return true; }
    default boolean performContextMenuAction(int id) { return true; }
    default boolean beginBatchEdit() { return true; }
    default boolean endBatchEdit() { return true; }
    default boolean clearMetaKeyStates(int states) { return true; }
    default boolean reportFullscreenMode(boolean enabled) { return true; }
    default boolean setSelection(int start, int end) { return true; }
    default boolean requestCursorUpdates(int cursorUpdateMode) { return false; }
    default void closeConnection() {}
    default android.os.Handler getHandler() { return null; }
    default CharSequence getSurroundingText(int beforeLength, int afterLength, int flags) { return null; }
    default Object getExtractedText(Object request, int flags) { return null; }
    default boolean commitCompletion(Object text) { return true; }
    default boolean commitCorrection(Object correctionInfo) { return true; }
    default boolean commitContent(Object inputContentInfo, int flags, android.os.Bundle opts) { return false; }
    default boolean setComposingTextWithStyle(CharSequence text, int newCursorPosition) { return setComposingText(text, newCursorPosition); }
}
