import java.util.Random;

public class SensorTemperatura extends Sensor {
    public SensorTemperatura(Random random) {
        super("Temperatura", random);
    }

    @Override
    public double leerValor() {
        double valor = 20 + getRandom().nextDouble() * 10; // 20° - 30°
        valor = Utils.redondearADosDecimales(valor);
        notificarObservers(valor); 
        return valor;
    }
}