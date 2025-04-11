import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class Cliente {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            GestorSensores gestorSensores = new GestorSensores();

            while (true) {
                List<EventoSensor> eventos = gestorSensores.leerDatosSensores();

                for (EventoSensor evento : eventos) {
                    String mensaje = "Evento: " + evento.toString();
                    byte[] datos = mensaje.getBytes();
                    InetAddress direccion = InetAddress.getByName(SERVIDOR);

                    DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccion, PUERTO);
                    socket.send(paquete);

                    System.out.println("Mensaje enviado al servidor UDP: " + mensaje);
                }

                Thread.sleep(10000); // 10 segundos
            }
        } catch (Exception e) {
            System.out.println("Error en el cliente UDP.");
            e.printStackTrace();
        }
    }
}
