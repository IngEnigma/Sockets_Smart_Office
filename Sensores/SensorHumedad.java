import java.util.Random;

public class SensorHumedad extends Sensor {
    public SensorHumedad(Random random) {
        super("Humedad", random);
    }

    @Override
    public double leerValor() {
        return 40 + getRandom().nextDouble() * 20; // 40% - 60%
    }
}
