package android.content;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/** ClipData stub. */
public class ClipData implements Parcelable {
    public static class Item {
        private final CharSequence text;
        private final String htmlText;
        private final Uri uri;
        private final Intent intent;
        public Item(CharSequence text) { this.text = text; this.htmlText = null; this.uri = null; this.intent = null; }
        public Item(CharSequence text, String htmlText) { this.text = text; this.htmlText = htmlText; this.uri = null; this.intent = null; }
        public Item(Uri uri) { this.text = null; this.htmlText = null; this.uri = uri; this.intent = null; }
        public Item(Intent intent) { this.text = null; this.htmlText = null; this.uri = null; this.intent = intent; }
        public CharSequence getText() { return text; }
        public String getHtmlText() { return htmlText; }
        public Uri getUri() { return uri; }
        public Intent getIntent() { return intent; }
        public CharSequence coerceToText(Context context) { return text != null ? text : uri != null ? uri.toString() : ""; }
        public CharSequence coerceToStyledText(Context context) { return coerceToText(context); }
        public String coerceToHtmlText(Context context) { return htmlText; }
        public int describeContents() { return 0; }
        public void writeToParcel(Parcel dest, int flags) {}
    }
    public static class Description {
        private final CharSequence label;
        private final int icon;
        public Description(CharSequence label, int icon) { this.label = label; this.icon = icon; }
        public CharSequence getLabel() { return label; }
        public int getIcon() { return icon; }
        public android.os.Bundle getExtras() { return null; }
    }

    private final Description description;
    private final List<Item> items = new ArrayList<>();

    public ClipData(CharSequence label, String[] mimeTypes, Item item) {
        this.description = new Description(label, 0);
        if (item != null) items.add(item);
    }
    private ClipData(Description description, List<Item> items) {
        this.description = description;
        this.items.addAll(items);
    }

    public static ClipData newPlainText(CharSequence label, CharSequence text) {
        return new ClipData(label, new String[]{"text/plain"}, new Item(text));
    }
    public static ClipData newHtmlText(CharSequence label, CharSequence text, String htmlText) {
        return new ClipData(label, new String[]{"text/html"}, new Item(text, htmlText));
    }
    public static ClipData newIntent(CharSequence label, Intent intent) {
        return new ClipData(label, new String[]{"text/uri-list"}, new Item(intent));
    }
    public static ClipData newUri(ContentResolver resolver, CharSequence label, Uri uri) {
        return new ClipData(label, new String[]{"text/uri-list"}, new Item(uri));
    }
    public static ClipData newRawUri(CharSequence label, Uri uri) {
        return new ClipData(label, new String[]{"text/uri-list"}, new Item(uri));
    }

    public int getItemCount() { return items.size(); }
    public Item getItemAt(int index) { return index >= 0 && index < items.size() ? items.get(index) : null; }
    public void addItem(Item item) { if (item != null) items.add(item); }
    public Description getDescription() { return description; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}

    public static final Parcelable.Creator<ClipData> CREATOR = new Parcelable.Creator<ClipData>() {
        public ClipData createFromParcel(Parcel source) { return null; }
        public ClipData[] newArray(int size) { return new ClipData[size]; }
    };
}
