import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventoSensor {
    private String tipo;
    private double valor;

    public static EventoSensor fromString(String mensaje) {
        if (mensaje == null) return null;

        Pattern pattern = Pattern.compile("Sensor='(.*?)', Valor=(\\d+(?:\\.\\d+)?)");
        Matcher matcher = pattern.matcher(mensaje);

        if (matcher.find()) {
            try {
                String tipo = matcher.group(1);  
                double valor = Double.parseDouble(matcher.group(2));  
                return new EventoSensor(tipo, valor);
            } catch (NumberFormatException e) {
                System.err.println("Error al convertir el valor: " + e.getMessage());
                return null;
            }
        }
        return null;
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
