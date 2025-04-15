public class Servidor {
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try {
            ControladorOficina controlador = new ControladorOficina();
            ServidorUDP servidor = new ServidorUDP(PUERTO, controlador);
            servidor.iniciar();
        } catch (Exception e) {
            System.out.println("Error al iniciar el servidor UDP.");
            e.printStackTrace();
        }
    }
}