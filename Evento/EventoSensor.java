public class EventoSensor {
    private String tipo;
    private double valor;

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