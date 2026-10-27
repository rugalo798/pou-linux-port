package android.content.res;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/** AssetFileDescriptor stub. */
public class AssetFileDescriptor implements java.io.Closeable {
    private final FileDescriptor fd;
    private final long startOffset;
    private final long length;

    public AssetFileDescriptor(FileDescriptor fd, long startOffset, long length) {
        this.fd = fd; this.startOffset = startOffset; this.length = length;
    }
    public FileDescriptor getFileDescriptor() { return fd; }
    public long getStartOffset() { return startOffset; }
    public long getLength() { return length; }
    public long getDeclaredLength() { return length; }
    public FileInputStream createInputStream() throws IOException { return new FileInputStream(fd); }
    public FileInputStream createInputStream(long offset, long length) throws IOException { return new FileInputStream(fd); }
    public void close() throws IOException {}
    public android.os.ParcelFileDescriptor getParcelFileDescriptor() { return null; }
}
