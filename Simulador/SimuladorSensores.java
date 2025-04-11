import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class SimuladorSensores {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            GestorSensores gestor = new GestorSensores();

            while (true) {
                List<EventoSensor> eventos = gestor.leerDatosSensores();

                for (EventoSensor evento : eventos) {
                    String mensaje = evento.toString();
                    byte[] datos = mensaje.getBytes();
                    InetAddress direccion = InetAddress.getByName(SERVER_IP);

                    DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccion, SERVER_PORT);
                    
                    try {
                        socket.send(paquete);
                        System.out.println("Evento enviado: " + mensaje);
                    } catch (Exception e) {
                        System.out.println("Error al enviar evento: " + mensaje);
                        e.printStackTrace();
                    }
                }

                Thread.sleep(10000);
            }
        } catch (Exception e) {
            System.out.println("Error en el simulador de sensores.");
            e.printStackTrace();
        }
    }
}
