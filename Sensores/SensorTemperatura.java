import java.util.Random;

public class SensorTemperatura extends Sensor {
    public SensorTemperatura(Random random) {
        super("Temperatura", random);
    }

    @Override
    public double leerValor() {
        return 20 + getRandom().nextDouble() * 10; // 20° - 30°
    }
}