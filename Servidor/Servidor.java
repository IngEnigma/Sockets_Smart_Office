import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    private static final int PUERTO = 12345;
    private static final String PREFIJO_EVENTO = "Evento: ";

    public static void main(String[] args) {
        ControladorOficina controlador = new ControladorOficina();

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO);

            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);

                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                EventoSensor evento = parseEvento(mensaje);
                if (evento != null) {
                    controlador.procesarEvento(evento);
                } else {
                    System.out.println("Evento no procesado, formato incorrecto.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor UDP.");
            e.printStackTrace();
        }
    }

    private static EventoSensor parseEvento(String mensaje) {
        try {
            String mensajeSinPrefijo = mensaje.replace(PREFIJO_EVENTO, "");
            String[] partes = mensajeSinPrefijo.split(", Valor=");
            if (partes.length == 2) {
                return crearEvento(partes);
            } else {
                System.out.println("Formato incorrecto del mensaje: " + mensajeSinPrefijo);
            }
        } catch (Exception e) {
            System.out.println("Error al procesar evento.");
            e.printStackTrace();
        }
        return null;
    }

    private static EventoSensor crearEvento(String[] partes) {
        try {
            String tipo = partes[0].replace("EventoSensor Sensor='", "").replace("'", "");
            double valor = Double.parseDouble(partes[1].replace("}", ""));
            return new EventoSensor(tipo, valor);
        } catch (Exception e) {
            System.out.println("Error al crear el evento.");
            e.printStackTrace();
        }
        return null;
    }
}
