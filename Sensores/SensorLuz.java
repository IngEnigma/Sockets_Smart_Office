import java.util.Random;

public class SensorLuz extends Sensor {

    public SensorLuz() {
        super("Luz", 20);
    }

    @Override
    public double leerValor() {
        double valor = getRandom().nextDouble() * 100; // 0% - 100%
        valor = Utils.redondearADosDecimales(valor);
        notificarObservers(valor); 
        return valor;
    }
}