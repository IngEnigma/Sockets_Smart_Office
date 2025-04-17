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
        System.out.println("Procesando evento: " + evento.getTipo() + " = " + evento.getValor());

        switch (evento.getTipo()) {
            case "Temperatura":
                estrategia.ajustar(gestorActuadores.getAireAcondicionado(), evento.getValor());
                estrategia.ajustar(gestorActuadores.getCalefaccion(), evento.getValor());
                break;

            case "Humedad":
                estrategia.ajustar(gestorActuadores.getVentana(), evento.getValor());
                break;

            case "Luz":
                estrategia.ajustar(gestorActuadores.getLuz(), evento.getValor());
                estrategia.ajustar(gestorActuadores.getPersiana(), evento.getValor());
                break;

            case "CalidadAire":
                estrategia.ajustar(gestorActuadores.getVentana(), evento.getValor());
                break;

            default:
                System.out.println("Evento desconocido: " + evento.getTipo());
        }
    }

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
