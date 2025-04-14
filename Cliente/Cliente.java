import java.net.InetAddress;
import java.util.List;

public class Cliente implements SensorObserver {
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO = 12345;
    private UDPManager udpManager;

    public Cliente() throws Exception {
        this.udpManager = new UDPManager();
    }

    @Override
    public void actualizar(EventoSensor evento) {
        try {
            String mensaje = "Evento: " + evento.toString();
            InetAddress direccion = InetAddress.getByName(SERVIDOR);
            udpManager.enviarMensaje(mensaje, direccion, PUERTO);
            System.out.println("Mensaje enviado al servidor UDP: " + mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
