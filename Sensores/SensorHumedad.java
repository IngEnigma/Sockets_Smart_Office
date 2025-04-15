import java.util.Random;

public class SensorHumedad extends Sensor {

    public SensorHumedad() {
        super("Humedad", 5);
    }

    @Override
    public double leerValor() {
        double valor = 40 + getRandom().nextDouble() * 20; // 40% - 60%
        valor = Utils.redondearADosDecimales(valor);
        notificarObservers(valor); 
        return valor;
    }
}
