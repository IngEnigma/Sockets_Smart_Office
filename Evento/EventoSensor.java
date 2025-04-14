public class EventoSensor {
    private String tipo;
    private double valor;
    
    public static EventoSensor fromString(String mensaje) {
        try {
            String[] partes = mensaje.split("Sensor='|', Valor=");
            if (partes.length < 2) return null;
    
            String tipo = partes[1];
            double valor = Double.parseDouble(partes[2]);
    
            return new EventoSensor(tipo, valor);
        } catch (Exception e) {
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