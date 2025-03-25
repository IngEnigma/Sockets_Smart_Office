import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 12345;
    private static final String PREFIJO_EVENTO = "Evento: ";
    private static final String FORMATO_INCORRECTO = "Formato incorrecto del mensaje: ";

    public static void main(String[] args) {
        ControladorOficina controlador = new ControladorOficina();

        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Conectado al servidor. Esperando eventos...");

            procesarMensajes(in, controlador);

        } catch (IOException e) {
            System.out.println("Error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }

    private static void procesarMensajes(BufferedReader in, ControladorOficina controlador) throws IOException {
        String mensaje;
        while ((mensaje = in.readLine()) != null) {
            System.out.println("Notificación recibida: " + mensaje);
            EventoSensor evento = parseEvento(mensaje);
            if (evento != null) {
                System.out.println("Evento procesado: " + evento);
                controlador.procesarEvento(evento);
            }
        }
    }

    private static EventoSensor parseEvento(String mensaje) {
        try {
            String mensajeSinPrefijo = mensaje.replace(PREFIJO_EVENTO, "");
            System.out.println("Procesando mensaje: " + mensajeSinPrefijo);
            String[] partes = mensajeSinPrefijo.split(", Valor=");
            if (partes.length == 2) {
                return crearEvento(partes);
            } else {
                System.out.println(FORMATO_INCORRECTO + mensajeSinPrefijo);
            }
        } catch (Exception e) {
            System.out.println("Error al procesar evento: " + mensaje);
            e.printStackTrace();
        }
        return null;
    }

    private static EventoSensor crearEvento(String[] partes) {
        String tipo = partes[0].replace("EventoSensor Sensor='", "").replace("'", "");
        double valor = Double.parseDouble(partes[1].replace("}", ""));
        System.out.println("Evento validado: Tipo=" + tipo + ", Valor=" + valor);
        return new EventoSensor(tipo, valor);
    }
}
