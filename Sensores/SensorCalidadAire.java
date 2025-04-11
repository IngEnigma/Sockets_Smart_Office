import java.util.Random;

public class SensorCalidadAire extends Sensor {
    public SensorCalidadAire(Random random) {
        super("CalidadAire", random);
    }

    @Override
    public double leerValor() {
        double valor = getRandom().nextDouble() * 100; // 0% - 100%
        return Utils.redondearADosDecimales(valor);
    }
}
