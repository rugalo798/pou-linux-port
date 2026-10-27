package android.bluetooth;

import java.io.Closeable;
import java.io.IOException;

/** Bluetooth server socket stub. */
public final class BluetoothServerSocket implements Closeable {
    private final int channel;

    public BluetoothServerSocket(int channel) { this.channel = channel; }
    public BluetoothSocket accept() throws IOException { throw new IOException("Bluetooth not available"); }
    public BluetoothSocket accept(int timeout) throws IOException { throw new IOException("Bluetooth not available"); }
    public void close() throws IOException {}
    public int getChannel() { return channel; }
    public String toString() { return "BluetoothServerSocket(" + channel + ")"; }
}
