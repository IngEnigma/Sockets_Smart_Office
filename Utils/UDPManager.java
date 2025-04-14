import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPManager {
    private DatagramSocket socket;

    public UDPManager() throws Exception {
        this.socket = new DatagramSocket(); 
    }

    public UDPManager(int port) throws Exception {
        this.socket = new DatagramSocket(port); 
    }

    public void enviarMensaje(String mensaje, InetAddress ip, int port) throws Exception {
        byte[] datos = mensaje.getBytes();
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, ip, port);
        socket.send(paquete);
    }

    public String recibirMensaje(byte[] buffer) throws Exception {
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        return new String(paquete.getData(), 0, paquete.getLength());
    }

    public DatagramPacket recibirPaquete(byte[] buffer) throws Exception {
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        return paquete;
    }

    public void cerrar() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}