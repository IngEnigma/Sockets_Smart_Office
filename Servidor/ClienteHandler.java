import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClienteHandler implements Runnable {
    private final Socket socket;
    private PrintWriter out;
    private static final List<PrintWriter> clientes = new CopyOnWriteArrayList<>();

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            this.out = out;
            clientes.add(out);
            System.out.println("Nuevo cliente conectado: " + socket.getInetAddress());

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje recibido de " + socket.getInetAddress() + ": " + inputLine);
                retransmitirMensaje(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Error en la comunicación con el cliente: " + socket.getInetAddress());
            e.printStackTrace();
        } finally {
            desconectarCliente();
        }
    }

    private void desconectarCliente() {
        if (out != null) {
            clientes.remove(out);
            System.out.println("Cliente desconectado: " + socket.getInetAddress());
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar la conexión con el cliente.");
            e.printStackTrace();
        }
    }

    public static void retransmitirMensaje(String mensaje) {
        System.out.println("Retransmitiendo mensaje a los clientes: " + mensaje);
        for (PrintWriter cliente : clientes) {
            try {
                cliente.println("Evento: " + mensaje);
            } catch (Exception e) {
                System.out.println("Error al enviar mensaje a un cliente.");
                e.printStackTrace();
            }
        }
    }
}
