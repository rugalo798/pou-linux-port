package android.content.pm;

import android.content.IntentSender;

/** PackageInstaller stub. */
public class PackageInstaller {
    public static final int STATUS_PENDING_USER_ACTION = 1;
    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_FAILURE = -1;
    public static final int STATUS_FAILURE_ABORTED = -2;
    public static final int STATUS_FAILURE_BLOCKED = -3;
    public static final int STATUS_FAILURE_CONFLICT = -4;
    public static final int STATUS_FAILURE_INCOMPATIBLE = -5;
    public static final int STATUS_FAILURE_INVALID = -6;
    public static final int STATUS_FAILURE_STORAGE = -7;
    public static final int STATUS_FAILURE_INCOMPAT_CHECK = -8;
    public static final int STATUS_FAILURE_SECURITY = -9;
    public static final int STATUS_FAILURE_BLOCKED_BY_ADMIN = -10;
    public static final int STATUS_FAILURE_DISABLED = -11;
    public static final int STATUS_FAILURE_ABORTED_BY_USER = -12;
    public static final int STATUS_FAILURE_INVALID_APK = -13;
    public static final int STATUS_FAILURE_INSTALLER = -14;
    public static final int STATUS_FAILURE_INVALID_PACKAGE = -15;
    public static final int STATUS_FAILURE_INVALID_APK_SIGNATURE = -16;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED = -17;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED_NOT_APK = -18;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED_BAD_MANIFEST = -19;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED_UNEXPECTED_EXCEPTION = -20;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED_NO_CERTIFICATES = -21;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES = -22;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING = -23;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED_BAD_PACKAGE_NAME = -24;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED_MANIFEST_MALFORMED = -25;
    public static final int STATUS_FAILURE_INSTALL_PARSE_FAILED_MANIFEST_EMPTY = -26;
    public static final int STATUS_FAILURE_INTERNAL_ERROR = -27;
    public static class SessionParams {
        public static final int MODE_FULL_INSTALL = 1;
        public static final int MODE_INHERIT_EXISTING = 2;
        public int mode;
        public long sizeBytes;
        public String appPackageName;
        public String appLabel;
        public int installLocation;
        public int installReason;
        public android.net.Uri referrerUri;
        public android.net.Uri originatingUri;
        public String packageSource;
        public SessionParams(int mode) { this.mode = mode; }
    }
    public static class Session {
        public int getSessionId() { return 0; }
        public void close() {}
        public void commit(java.io.OutputStream out) {}
        public void abandon() {}
    }
    public int createSession(SessionParams params) { return 0; }
    public Session openSession(int sessionId) { return null; }
    public void abandonSession(int sessionId) {}
    public void uninstall(String packageName, IntentSender statusReceiver) {}
    public void uninstall(VersionedPackage versionedPackage, IntentSender statusReceiver) {}
}
