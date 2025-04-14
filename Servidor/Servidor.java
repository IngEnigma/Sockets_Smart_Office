import java.net.DatagramPacket;

public class Servidor {
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        ControladorOficina controlador = new ControladorOficina();

        try {
            UDPManager udpManager = new UDPManager(PUERTO);
            System.out.println("Servidor UDP escuchando en el puerto " + PUERTO);

            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket paquete = udpManager.recibirPaquete(buffer);
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                EventoSensor evento = EventoSensor.fromString(mensaje);
                if (evento != null) {
                    controlador.procesarEvento(evento);
                } else {
                    System.out.println("Evento no procesado, formato incorrecto.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor UDP.");
            e.printStackTrace();
        }
    }
}