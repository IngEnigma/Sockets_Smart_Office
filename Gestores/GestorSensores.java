import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class GestorSensores {
    private List<Sensor> sensores;
    private Random random;

    public GestorSensores() {
        this.random = new Random();
        this.sensores = new ArrayList<>();
        inicializarSensores();
    }

    private void inicializarSensores() {
        sensores.add(new SensorTemperatura(random));
        sensores.add(new SensorHumedad(random));
        sensores.add(new SensorLuz(random));
        sensores.add(new SensorCalidadAire(random));
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
