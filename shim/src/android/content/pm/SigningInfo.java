package android.content.pm;

/** SigningInfo stub. */
public class SigningInfo implements android.os.Parcelable {
    public SigningInfo() {}
    public SigningInfo(SigningInfo orig) {}
    public boolean hasMultipleSigners() { return false; }
    public Signature[] getApkContentsSigners() { return new Signature[0]; }
    public Signature[] getSigningCertificateHistory() { return new Signature[0]; }
    public boolean hasPastSigningCertificates() { return false; }
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) {}
}
