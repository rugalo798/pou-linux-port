package android.content.pm;

/** Signature. */
public class Signature implements android.os.Parcelable {
    private final byte[] signature;
    public Signature(byte[] signature) { this.signature = signature; }
    public byte[] toByteArray() { return signature; }
    public int hashCode() { return java.util.Arrays.hashCode(signature); }
    public boolean equals(Object obj) { return obj instanceof Signature && java.util.Arrays.equals(signature, ((Signature) obj).signature); }
    public int describeContents() { return 0; }
    public void writeToParcel(android.os.Parcel dest, int flags) {}
}
