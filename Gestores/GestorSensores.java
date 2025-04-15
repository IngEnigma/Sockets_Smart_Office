import java.util.ArrayList;
import java.util.List;

public class GestorSensores {
    private List<Sensor> sensores;

    public GestorSensores() {
        this.sensores = new ArrayList<>();
        inicializarSensores();
    }

    private void inicializarSensores() {
        sensores.add(new SensorTemperatura());
        sensores.add(new SensorHumedad());
        sensores.add(new SensorLuz());
        sensores.add(new SensorCalidadAire());
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public List<EventoSensor> leerDatosSensores() {
        List<EventoSensor> eventos = new ArrayList<>();
        for (Sensor sensor : sensores) {
            eventos.add(new EventoSensor(sensor.getTipo(), sensor.leerValor()));
        }
        return eventos;
    }
}
