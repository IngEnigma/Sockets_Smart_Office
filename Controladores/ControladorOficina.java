public class ControladorOficina {
    private GestorActuadores gestorActuadores;

    public ControladorOficina() {
        this.gestorActuadores = new GestorActuadores();
    }

    public void procesarEvento(EventoSensor evento) {
        String tipoEvento = evento.getTipo().replace("Evento: ", ""); 
        
        switch (tipoEvento) {
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
                System.out.println("Tipo de evento no reconocido: " + tipoEvento);
        }
    }    

    private void ajustarTemperatura(double temperatura) {
        System.out.println("Ajustando temperatura a " + temperatura + "°C");
        if (temperatura > 25) {
            gestorActuadores.ajustarTemperatura((int) temperatura); // Enciende el aire acondicionado
        } else if (temperatura < 18) {
            gestorActuadores.ajustarTemperatura((int) temperatura); // Enciende la calefacción
        } else {
            gestorActuadores.ajustarTemperatura(22); // Apaga ambos y mantiene una temperatura neutra
        }
    }

    private void ajustarHumedad(double humedad) {
        System.out.println("Ajustando humedad a " + humedad + "%");
        if (humedad > 60) {
            gestorActuadores.ajustarVentana(100); // Abre las ventanas para ventilar
        } else if (humedad < 40) {
            gestorActuadores.ajustarVentana(0); // Cierra las ventanas
        } else {
            gestorActuadores.ajustarVentana(50); // Mantiene las ventanas semiabiertas
        }
    }

    private void ajustarIluminacion(double intensidadLuz) {
        System.out.println("Ajustando iluminación a " + intensidadLuz + "%");
        if (intensidadLuz < 30) {
            gestorActuadores.ajustarIluminacion(100); // Enciende las luces al máximo
        } else if (intensidadLuz > 70) {
            gestorActuadores.ajustarIluminacion(0); // Apaga las luces
        } else {
            gestorActuadores.ajustarIluminacion(50); // Ajusta las luces al 50%
        }
    }

    private void ajustarCalidadAire(double calidadAire) {
        System.out.println("Ajustando calidad del aire a " + calidadAire + "%");
        if (calidadAire < 50) {
            gestorActuadores.ajustarVentana(100); // Abre las ventanas para ventilar
        } else {
            gestorActuadores.ajustarVentana(0); // Cierra las ventanas
        }
    }
}