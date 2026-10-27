package android.graphics.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/** Icon stub. */
public final class Icon implements Parcelable {
    public static final int TYPE_BITMAP = 1;
    public static final int TYPE_RESOURCE = 2;
    public static final int TYPE_DATA = 3;
    public static final int TYPE_URI = 4;
    public static final int TYPE_URI_ADAPTIVE_BITMAP = 5;

    private final int type;
    private final int resId;
    private final String resPackage;
    private final Bitmap bitmap;
    private final Uri uri;

    private Icon(int type, int resId, String resPackage, Bitmap bitmap, Uri uri) {
        this.type = type; this.resId = resId; this.resPackage = resPackage; this.bitmap = bitmap; this.uri = uri;
    }

    public static Icon createWithResource(Context context, int resId) { return new Icon(TYPE_RESOURCE, resId, context != null ? context.getPackageName() : null, null, null); }
    public static Icon createWithResource(String resPackage, int resId) { return new Icon(TYPE_RESOURCE, resId, resPackage, null, null); }
    public static Icon createWithBitmap(Bitmap bits) { return new Icon(TYPE_BITMAP, 0, null, bits, null); }
    public static Icon createWithContentUri(String uri) { return new Icon(TYPE_URI, 0, null, null, Uri.parse(uri)); }
    public static Icon createWithContentUri(Uri uri) { return new Icon(TYPE_URI, 0, null, null, uri); }
    public static Icon createWithFilePath(String path) { return new Icon(TYPE_URI, 0, null, null, Uri.parse("file://" + path)); }
    public static Icon createWithData(byte[] data, int offset, int length) { return new Icon(TYPE_DATA, 0, null, null, null); }
    public static Icon createWithAdaptiveBitmap(Bitmap bits) { return new Icon(TYPE_BITMAP, 0, null, bits, null); }

    public int getType() { return type; }
    public int getResId() { return resId; }
    public String getResPackage() { return resPackage; }
    public Bitmap getBitmap() { return bitmap; }
    public Uri getUri() { return uri; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}

    public static final Parcelable.Creator<Icon> CREATOR = new Parcelable.Creator<Icon>() {
        public Icon createFromParcel(Parcel source) { return null; }
        public Icon[] newArray(int size) { return new Icon[size]; }
    };
}
