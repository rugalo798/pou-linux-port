package android.os;

/** Remote exception. */
public class RemoteException extends Exception {
    public RemoteException() {}
    public RemoteException(String message) { super(message); }
}
