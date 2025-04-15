import java.net.DatagramPacket;
import java.net.InetAddress;

public interface ComunicadorUDP {
    void enviarMensaje(String mensaje, InetAddress ip, int port) throws Exception;
    DatagramPacket recibirPaquete(byte[] buffer) throws Exception;
    
    void cerrar();
}
