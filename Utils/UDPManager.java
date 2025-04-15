import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPManager implements IComunicadorUDP {
    private DatagramSocket socket;

    public UDPManager() throws Exception {
        this.socket = new DatagramSocket(); 
    }

    public UDPManager(int port) throws Exception {
        this.socket = new DatagramSocket(port); 
    }

    @Override
    public void enviarMensaje(String mensaje, InetAddress ip, int port) throws Exception {
        byte[] datos = mensaje.getBytes();
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, ip, port);
        socket.send(paquete);
    }

    @Override
    public DatagramPacket recibirPaquete(byte[] buffer) throws Exception {
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquete);
        return paquete;
    }

    @Override
    public void cerrar() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}
