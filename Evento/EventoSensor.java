public class EventoSensor {
    private String tipo;
    private double valor;
    
    public static EventoSensor fromString(String mensaje) {
        if (mensaje == null || !mensaje.contains("Sensor='") || !mensaje.contains("Valor=")) return null;
    
        try {
            int startTipo = mensaje.indexOf("Sensor='") + 8;
            int endTipo = mensaje.indexOf("'", startTipo);
            String tipo = mensaje.substring(startTipo, endTipo);
    
            int startValor = mensaje.indexOf("Valor=") + 6;
            double valor = Double.parseDouble(mensaje.substring(startValor));
    
            return new EventoSensor(tipo, valor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    

    public EventoSensor(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }


    @Override
    public String toString() {
        return "EventoSensor Sensor='" + tipo + "', Valor=" + valor;
    }
}