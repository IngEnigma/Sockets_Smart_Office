import java.util.ArrayList;
import java.util.List;

public class GestorSensores {
    private final List<Sensor> sensores;

    public GestorSensores(List<ISensorFactory> factories) {
        sensores = new ArrayList<>();
        for (ISensorFactory factory : factories) {
            sensores.add(factory.crearSensor());
        }
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public List<EventoSensor> leerDatosSensores() {
        List<EventoSensor> eventos = new ArrayList<>();
        for (Sensor sensor : sensores) {
            double valor = sensor.leerValor();
            eventos.add(new EventoSensor(sensor.getTipo(), valor));
        }
        return eventos;
    }
}