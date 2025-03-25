import java.util.Random;

public class SensorLuz extends Sensor {
    public SensorLuz(Random random) {
        super("Luz", random);
    }

    @Override
    public double leerValor() {
        return getRandom().nextDouble() * 100; // 0% - 100%
    }
}