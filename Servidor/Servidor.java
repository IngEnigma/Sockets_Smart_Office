import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    private static final Logger LOGGER = Logger.getLogger(Servidor.class.getName());
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        LoggerConfig.configurarLoggerGlobal();

        try {
            ControladorOficina controlador = new ControladorOficina();
            ServidorUDP servidor = new ServidorUDP(PUERTO, controlador);
            LOGGER.info("Servidor iniciado correctamente.");
            servidor.iniciar();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al iniciar el servidor UDP.", e);
        }
    }
}
