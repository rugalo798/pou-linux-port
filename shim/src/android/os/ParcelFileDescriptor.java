package android.os;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/** ParcelFileDescriptor stub. */
public class ParcelFileDescriptor implements Closeable, Parcelable {
    public static final int MODE_READ_ONLY = 0x10000000;
    public static final int MODE_WRITE_ONLY = 0x20000000;
    public static final int MODE_READ_WRITE = 0x30000000;
    public static final int MODE_CREATE = 0x08000000;
    public static final int MODE_TRUNCATE = 0x04000000;
    public static final int MODE_APPEND = 0x02000000;

    private final FileDescriptor fd;
    public ParcelFileDescriptor(FileDescriptor fd) { this.fd = fd; }
    public FileDescriptor getFileDescriptor() { return fd; }
    public static ParcelFileDescriptor open(File file, int mode) throws FileNotFoundException {
        try {
            return new ParcelFileDescriptor(new FileInputStream(file).getFD());
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
    public static ParcelFileDescriptor open(File file, int mode, Handler handler, OnCloseListener listener) throws FileNotFoundException { return open(file, mode); }
    public static ParcelFileDescriptor fromFd(int fd) { return new ParcelFileDescriptor(null); }
    public static ParcelFileDescriptor dup(FileDescriptor fd) { return new ParcelFileDescriptor(fd); }
    public void close() throws IOException {}
    public int getFd() { return 0; }
    public long getStatSize() { return 0; }
    public int describeContents() { return 0; }
    public void writeToParcel(Parcel dest, int flags) {}

    public interface OnCloseListener { void onClose(IOException e); }

    public static final Parcelable.Creator<ParcelFileDescriptor> CREATOR = new Parcelable.Creator<ParcelFileDescriptor>() {
        public ParcelFileDescriptor createFromParcel(Parcel source) { return new ParcelFileDescriptor(null); }
        public ParcelFileDescriptor[] newArray(int size) { return new ParcelFileDescriptor[size]; }
    };
}
