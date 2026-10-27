package android.content.pm;

/** ApplicationInfo fields used by the game. */
public class ApplicationInfo extends PackageItemInfo implements android.os.Parcelable {
    public String className;
    public String processName;
    public String taskAffinity;
    public String permission;
    public String manageSpaceActivityName;
    public String backupAgentName;
    public String sourceDir;
    public String publicSourceDir;
    public String dataDir;
    public String nativeLibraryDir;
    public String credentialProtectedDataDir;
    public String deviceProtectedDataDir;
    public String primaryCpuAbi;
    public String secondaryCpuAbi;
    public String classLoaderName;
    public String appComponentFactory;
    public String seInfo;
    public String seInfoUser;
    public String[] splitNames;
    public String[] splitPublicSourceDirs;
    public String[] resourceDirs;
    public String[] sharedLibraryFiles;
    public String[] usesLibraryFiles;
    public int targetSdkVersion;
    public int minSdkVersion;
    public int compileSdkVersion;
    public int flags;
    public int uid;
    public int theme;
    public int category;
    public int installLocation;
    public int versionCode;
    public int networkSecurityConfigRes;
    public int fullBackupContent;
    public int dataExtractionRules;
    public int maxAspectRatio;
    public int minAspectRatio;
    public int hiddenApiEnforcementPolicy;
    public boolean supportsRtl;
    public boolean extractNativeLibs;
    public boolean usesNonSdkApi;
    public boolean isProfileableByShell;
    public boolean isProfileable;
    public boolean isVirtualPreload;
    public boolean isExtracted;
    public long longVersionCode;
    public android.util.DisplayMetrics displayMetrics;

    public boolean isEnabled() { return enabled; }
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) {}
    public String toString() { return "ApplicationInfo(" + packageName + ")"; }
}
