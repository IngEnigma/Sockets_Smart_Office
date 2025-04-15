import java.net.DatagramPacket;

public class ManejadorEventos {
    private final ControladorOficina controlador;

    public ManejadorEventos(ControladorOficina controlador) {
        this.controlador = controlador;
    }

    public void procesar(DatagramPacket paquete) {
        try {
            String mensaje = new String(paquete.getData(), 0, paquete.getLength());
            System.out.println("Mensaje recibido: " + mensaje);

            EventoSensor evento = EventoSensor.fromString(mensaje);
            if (evento != null) {
                controlador.procesarEvento(evento);
            } else {
                System.out.println("Evento no procesado, formato incorrecto.");
            }
        } catch (Exception e) {
            System.out.println("Error al procesar evento.");
            e.printStackTrace();
        }
    }
}
