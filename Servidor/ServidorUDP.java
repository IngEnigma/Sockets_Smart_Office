import java.net.DatagramPacket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorUDP {
    private static final Logger LOGGER = Logger.getLogger(ServidorUDP.class.getName());
    private static final int THREAD_POOL_SIZE = 10;

    private final ComunicadorUDP udpManager;
    private final ExecutorService pool;
    private final ManejadorEventos manejadorEventos;

    public ServidorUDP(int puerto, ControladorOficina controlador) throws Exception {
        this.udpManager = new UDPManager(puerto);
        this.pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        this.manejadorEventos = new ManejadorEventos(controlador);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Apagando servidor UDP...");
            udpManager.cerrar();
            pool.shutdown();
            LOGGER.info("Recursos liberados. Servidor cerrado.");
        }));
    }

    public void iniciar() {
        int puerto = ((UDPManager) udpManager).getSocket().getLocalPort();
        LOGGER.info("Servidor UDP escuchando en el puerto " + puerto);
        byte[] buffer = new byte[1024];

        while (true) {
            try {
                DatagramPacket paquete = udpManager.recibirPaquete(buffer);
                DatagramPacket copia = new DatagramPacket(
                    paquete.getData().clone(), paquete.getLength(),
                    paquete.getAddress(), paquete.getPort()
                );
                pool.execute(() -> {
                    try {
                        manejadorEventos.procesar(copia);
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Error al procesar paquete UDP", e);
                    }
                });
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al recibir paquete UDP", e);
            }
        }
    }
}
