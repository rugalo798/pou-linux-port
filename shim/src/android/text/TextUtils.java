package android.text;

/** Minimal android.text.TextUtils implementation used by the game. */
public final class TextUtils {
    private TextUtils() {}

    public enum TruncateAt { START, MIDDLE, END, MARQUEE }

    public static final android.os.Parcelable.Creator<CharSequence> CHAR_SEQUENCE_CREATOR =
            new android.os.Parcelable.Creator<CharSequence>() {
                @Override public CharSequence createFromParcel(android.os.Parcel source) {
                    return source.readString();
                }
                @Override public CharSequence[] newArray(int size) {
                    return new CharSequence[size];
                }
            };

    public static void writeToParcel(CharSequence cs, android.os.Parcel dest, int parcelableFlags) {
        dest.writeString(cs == null ? null : cs.toString());
    }

    public static String substring(CharSequence source, int start, int end) {
        return source.subSequence(start, end).toString();
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        if (a == null || b == null || a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }

    public static String join(CharSequence delimiter, Object[] tokens) {
        if (tokens == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            if (i > 0) sb.append(delimiter);
            sb.append(tokens[i]);
        }
        return sb.toString();
    }

    public static String join(CharSequence delimiter, Iterable<?> tokens) {
        if (tokens == null) return "";
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Object token : tokens) {
            if (!first) sb.append(delimiter);
            sb.append(token);
            first = false;
        }
        return sb.toString();
    }

    public static String[] split(String text, String expression) {
        if (text == null || text.length() == 0) return new String[0];
        return text.split(expression);
    }

    public static String[] split(String text, java.util.regex.Pattern pattern) {
        if (text == null || text.length() == 0) return new String[0];
        return pattern.split(text);
    }

    public static boolean isDigitsOnly(CharSequence str) {
        if (str == null) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    public static int getTrimmedLength(CharSequence s) {
        if (s == null) return 0;
        int start = 0, end = s.length();
        while (start < end && Character.isWhitespace(s.charAt(start))) start++;
        while (end > start && Character.isWhitespace(s.charAt(end - 1))) end--;
        return end - start;
    }

    public static String concat(CharSequence... text) {
        if (text == null) return "";
        StringBuilder sb = new StringBuilder();
        for (CharSequence part : text) sb.append(part);
        return sb.toString();
    }
}
