import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimuladorSensores {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            GestorSensores gestor = new GestorSensores();

            while (true) {
                for (EventoSensor evento : gestor.leerDatosSensores()) {
                    out.println(evento.toString());
                    System.out.println("Evento enviado: " + evento);
                }
                Thread.sleep(10000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}