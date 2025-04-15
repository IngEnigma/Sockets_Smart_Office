import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SensorEventDispatcher implements SensorObserver {
    private static final Logger LOGGER = Logger.getLogger(SensorEventDispatcher.class.getName());
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 12345;

    private final ExecutorService pool;
    private final ComunicadorUDP udpManager;

    public SensorEventDispatcher(ComunicadorUDP udpManager) {
        this.pool = Executors.newFixedThreadPool(5);
        this.udpManager = udpManager;
    }

    @Override
    public void actualizar(EventoSensor evento) {
        pool.execute(() -> {
            try {
                String mensaje = "Evento: " + evento;
                InetAddress direccion = InetAddress.getByName(SERVIDOR);
                udpManager.enviarMensaje(mensaje, direccion, PUERTO);
                LOGGER.info("Mensaje enviado al servidor UDP: " + mensaje);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al enviar mensaje UDP", e);
            }
        });
    }
}
