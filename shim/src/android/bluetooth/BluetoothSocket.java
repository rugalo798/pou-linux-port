package android.bluetooth;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** Bluetooth socket stub. */
public final class BluetoothSocket implements Closeable {
    private final String address;

    public BluetoothSocket(String address) { this.address = address; }
    public void connect() throws IOException { throw new IOException("Bluetooth not available"); }
    public void close() throws IOException {}
    public InputStream getInputStream() throws IOException { throw new IOException("Bluetooth not available"); }
    public OutputStream getOutputStream() throws IOException { throw new IOException("Bluetooth not available"); }
    public BluetoothDevice getRemoteDevice() { return new BluetoothDevice(address); }
    public boolean isConnected() { return false; }
    public int getMaxReceivePacketSize() { return 0; }
    public int getMaxTransmitPacketSize() { return 0; }
    public String toString() { return "BluetoothSocket(" + address + ")"; }
}
