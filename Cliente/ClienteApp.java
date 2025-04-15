import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteApp {
    private static final Logger LOGGER = Logger.getLogger(ClienteApp.class.getName());
    private final SensorEventDispatcher dispatcher;

    public ClienteApp() {
        IComunicadorUDP udpManager;
        try {
            udpManager = new UDPManager();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo inicializar el UDPManager", e);
        }
        this.dispatcher = new SensorEventDispatcher(udpManager);
    }

    public void iniciar() {
        try {
            LoggerConfig.configurarLoggerGlobal("cliente");

            List<ISensorFactory> factories = new ArrayList<>();
            factories.add(new SensorTemperaturaFactory());
            factories.add(new SensorLuzFactory());
            factories.add(new SensorHumedadFactory());
            factories.add(new SensorCalidadAireFactory());

            GestorSensores gestorSensores = new GestorSensores(factories);

            List<Sensor> sensores = gestorSensores.getSensores();
            for (Sensor sensor : sensores) {
                sensor.agregarObserver(dispatcher);
                sensor.iniciar();
            }

            mantenerActivo();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al iniciar la aplicación cliente", e);
        }
    }


    private void mantenerActivo() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            Thread.sleep(1000);
        }
    }
}
