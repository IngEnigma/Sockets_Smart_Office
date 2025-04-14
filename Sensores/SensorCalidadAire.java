import java.util.Random;

public class SensorCalidadAire extends Sensor {
    public SensorCalidadAire(Random random) {
        super("CalidadAire", random);
    }

    @Override
    public double leerValor() {
        double valor = getRandom().nextDouble() * 100; // 0% - 100%
        valor = Utils.redondearADosDecimales(valor);
        notificarObservers(valor); 
        return valor;
    }
}
