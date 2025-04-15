import java.net.DatagramPacket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorUDP {
    private static final int THREAD_POOL_SIZE = 10;

    private ComunicadorUDP udpManager;
    private final ExecutorService pool;
    private final ManejadorEventos manejadorEventos;

    public ServidorUDP(int puerto, ControladorOficina controlador) throws Exception {
        this.udpManager = new UDPManager(puerto);
        this.pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        this.manejadorEventos = new ManejadorEventos(controlador);
    
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nApagando servidor UDP...");
            udpManager.cerrar();
            pool.shutdown(); 
            System.out.println("Recursos liberados. Servidor cerrado.");
        }));
    }

    public void iniciar() {
        System.out.println("Servidor UDP escuchando en el puerto " + ((UDPManager) udpManager).getSocket().getLocalPort());
        byte[] buffer = new byte[1024];

        while (true) {
            try {
                DatagramPacket paquete = udpManager.recibirPaquete(buffer);
                DatagramPacket copia = new DatagramPacket(
                    paquete.getData().clone(), paquete.getLength(),
                    paquete.getAddress(), paquete.getPort()
                );
                pool.execute(() -> manejadorEventos.procesar(copia));
            } catch (Exception e) {
                System.out.println("Error al recibir paquete UDP.");
                e.printStackTrace();
            }
        }
    }
}