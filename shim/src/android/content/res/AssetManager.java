package android.content.res;

import desktop.DesktopEnv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/** AssetManager over the extracted APK assets/ directory. */
public class AssetManager {
    public InputStream open(String fileName) throws IOException {
        return new FileInputStream(new File(DesktopEnv.assetsDir, fileName));
    }
    public InputStream open(String fileName, int accessMode) throws IOException { return open(fileName); }
    public String[] list(String path) throws IOException {
        File f = new File(DesktopEnv.assetsDir, path);
        String[] list = f.list();
        return list != null ? list : new String[0];
    }
    public AssetFileDescriptor openFd(String fileName) throws IOException { return null; }
    public AssetFileDescriptor openNonAssetFd(String fileName) throws IOException { return null; }
    public AssetFileDescriptor openNonAssetFd(int cookie, String fileName) throws IOException { return null; }
    public void close() {}
    public void setLocale(String locale) {}
    public String[] getLocales() { return new String[0]; }
}
