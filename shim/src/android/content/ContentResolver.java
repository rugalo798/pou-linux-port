package android.content;

import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

/** ContentResolver stub. */
public class ContentResolver {
    public static final String SCHEME_ANDROID_RESOURCE = "android.resource";
    public static final String SCHEME_CONTENT = "content";
    public static final String SCHEME_FILE = "file";
    public static final String CURSOR_ITEM_BASE_TYPE = "vnd.android.cursor.item";
    public static final String CURSOR_DIR_BASE_TYPE = "vnd.android.cursor.dir";

    public InputStream openInputStream(Uri uri) throws FileNotFoundException { return null; }
    public OutputStream openOutputStream(Uri uri) throws FileNotFoundException { return null; }
    public OutputStream openOutputStream(Uri uri, String mode) throws FileNotFoundException { return null; }
    public ParcelFileDescriptor openFileDescriptor(Uri uri, String mode) throws FileNotFoundException { return null; }
    public android.content.res.AssetFileDescriptor openAssetFileDescriptor(Uri uri, String mode) throws FileNotFoundException { return null; }
    public android.content.res.AssetFileDescriptor openTypedAssetFileDescriptor(Uri uri, String mimeType, Bundle opts) throws FileNotFoundException { return null; }
    public String getType(Uri uri) { return null; }
    public Object query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) { return null; }
    public Uri insert(Uri uri, Object values) { return null; }
    public int delete(Uri uri, String selection, String[] selectionArgs) { return 0; }
    public int update(Uri uri, Object values, String selection, String[] selectionArgs) { return 0; }
    public void registerContentObserver(Uri uri, boolean notifyForDescendants, Object observer) {}
    public void unregisterContentObserver(Object observer) {}
    public void notifyChange(Uri uri, Object observer) {}
    public void notifyChange(Uri uri, Object observer, boolean syncToNetwork) {}
    public void takePersistableUriPermission(Uri uri, int modeFlags) {}
    public void releasePersistableUriPermission(Uri uri, int modeFlags) {}
    public java.util.List<Uri> getPersistedUriPermissions() { return new java.util.ArrayList<>(); }
    public Bundle call(Uri uri, String method, String arg, Bundle extras) { return null; }
    public String[] getStreamTypes(Uri uri, String mimeTypeFilter) { return null; }
    public Object acquireContentProviderClient(Uri uri) { return null; }
}
