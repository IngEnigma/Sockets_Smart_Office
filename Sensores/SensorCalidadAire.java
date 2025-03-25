import java.util.Random;

public class SensorCalidadAire extends Sensor {
    public SensorCalidadAire(Random random) {
        super("CalidadAire", random);
    }

    @Override
    public double leerValor() {
        return getRandom().nextDouble() * 100; // 0% - 100%
    }
}
