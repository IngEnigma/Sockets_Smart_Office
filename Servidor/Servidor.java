import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
    private static final int PORT = 12345;
    private static ExecutorService poolClientes = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PORT)) {
            System.out.println("Servidor TCP iniciado en el puerto " + PORT);

            while (true) {
                System.out.println("Esperando una nueva conexión de cliente...");
                Socket socketCliente = servidor.accept();
                System.out.println("Nuevo dispositivo conectado: " + socketCliente.getInetAddress());

                try {
                    poolClientes.execute(new ClienteHandler(socketCliente));  
                    System.out.println("Hilo creado para manejar al cliente: " + socketCliente.getInetAddress());
                } catch (Exception e) {
                    System.out.println("Error al manejar el cliente: " + socketCliente.getInetAddress());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error en la creación del ServerSocket");
            e.printStackTrace();
        }
    }
}
