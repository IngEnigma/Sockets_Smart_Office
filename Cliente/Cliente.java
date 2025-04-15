import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cliente implements SensorObserver {
    ExecutorService pool = Executors.newFixedThreadPool(5);
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 12345;
    private ComunicadorUDP udpManager;

    public Cliente() throws Exception {
        this.udpManager = new UDPManager();
    }

    @Override
    public void actualizar(EventoSensor evento) {
        pool.execute(() -> {
            try {
                String mensaje = "Evento: " + evento.toString();
                InetAddress direccion = InetAddress.getByName(SERVIDOR);
                udpManager.enviarMensaje(mensaje, direccion, PUERTO);
                System.out.println("Mensaje enviado al servidor UDP: " + mensaje);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void iniciar() throws Exception {
        GestorSensores gestorSensores = new GestorSensores();
        List<Sensor> sensores = gestorSensores.getSensores(); 

        for (Sensor sensor : sensores) {
            sensor.agregarObserver(this);
            sensor.iniciar(); 
        }

        while (true) {
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        try {
            Cliente cliente = new Cliente();
            cliente.iniciar();
        } catch (Exception e) {
            System.out.println("Error en el cliente UDP.");
            e.printStackTrace();
        }
    }
}