public class ControladorOficina {
    private IStrategyAjuste estrategia;  
    private GestorActuadores gestorActuadores; 

    public ControladorOficina() {
        this.gestorActuadores = new GestorActuadores();
        this.estrategia = new EstrategiaAjusteAutomatico();  
    }

    public void cambiarEstrategia(IStrategyAjuste nuevaEstrategia) {
        this.estrategia = nuevaEstrategia;
    }

    public void procesarEvento(EventoSensor evento) {
        switch (evento.getTipo()) {
            case "Temperatura":
                ajustarTemperatura(evento.getValor());
                break;

            case "Humedad":
                ajustarHumedad(evento.getValor());
                break;

            case "Luz":
                ajustarIluminacion(evento.getValor());
                break;

            case "CalidadAire":
                ajustarCalidadAire(evento.getValor());
                break;

            default:
                System.out.println("Evento desconocido: " + evento.getTipo());
        }
    }

    private void ajustarTemperatura(double temperatura) {
        System.out.println("Ajustando temperatura a " + temperatura + "°C");
        if (temperatura > 25) {
            gestorActuadores.getAireAcondicionado().ajustar((int) temperatura); // Enciende el aire acondicionado
        } else if (temperatura < 18) {
            gestorActuadores.getCalefaccion().ajustar((int) temperatura); // Enciende la calefacción
        } else {
            gestorActuadores.getAireAcondicionado().apagar();
            gestorActuadores.getCalefaccion().apagar(); // Apaga ambos y mantiene una temperatura neutra
        }
    }

    private void ajustarHumedad(double humedad) {
        System.out.println("Ajustando humedad a " + humedad + "%");
        if (humedad > 60) {
            gestorActuadores.getVentana().ajustar(100); // Abre las ventanas para ventilar
        } else if (humedad < 40) {
            gestorActuadores.getVentana().ajustar(0); // Cierra las ventanas
        } else {
            gestorActuadores.getVentana().ajustar(50); // Mantiene las ventanas semiabiertas
        }
    }

    private void ajustarIluminacion(double intensidadLuz) {
        System.out.println("Ajustando iluminación a " + intensidadLuz + "%");
        if (intensidadLuz < 30) {
            gestorActuadores.getLuz().ajustar(100); // Enciende las luces al máximo
        } else if (intensidadLuz > 70) {
            gestorActuadores.getLuz().ajustar(0); // Apaga las luces
        } else {
            gestorActuadores.getLuz().ajustar(50); // Ajusta las luces al 50%
        }
    }

    private void ajustarCalidadAire(double calidadAire) {
        System.out.println("Ajustando calidad del aire a " + calidadAire + "%");
        if (calidadAire < 50) {
            gestorActuadores.getVentana().ajustar(100); // Abre las ventanas para ventilar
        } else {
            gestorActuadores.getVentana().ajustar(0); // Cierra las ventanas
        }
    }

    // Encender o apagar todos los actuadores (cuando se hace en grupo)
    public void encenderTodos() {
        gestorActuadores.getAireAcondicionado().encender();
        gestorActuadores.getCalefaccion().encender();
        gestorActuadores.getLuz().encender();
        gestorActuadores.getPersiana().encender();
        gestorActuadores.getVentana().encender();
    }

    public void apagarTodos() {
        gestorActuadores.getAireAcondicionado().apagar();
        gestorActuadores.getCalefaccion().apagar();
        gestorActuadores.getLuz().apagar();
        gestorActuadores.getPersiana().apagar();
        gestorActuadores.getVentana().apagar();
    }
}
