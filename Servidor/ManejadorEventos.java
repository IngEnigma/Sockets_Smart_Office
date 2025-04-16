import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejadorEventos {
    private static final Logger LOGGER = Logger.getLogger(ManejadorEventos.class.getName());

    public ManejadorEventos() {
    }

    public void procesar(DatagramPacket paquete) {
        try {
            String mensaje = new String(paquete.getData(), 0, paquete.getLength());
            LOGGER.info("Mensaje recibido: " + mensaje);

            EventoSensor evento = EventoSensor.fromString(mensaje);
            if (evento != null) {
                
            } else {
                LOGGER.warning("Evento no procesado, formato incorrecto: " + mensaje);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al procesar evento UDP.", e);
        }
    }
}
