import java.util.Random;

public class SensorCalidadAire extends Sensor {

    public SensorCalidadAire() {
        super("CalidadAire", 20);
    }

    @Override
    public double leerValor() {
        double valor = 400 + getRandom().nextDouble() * 800; // 400 – 1200 PPM
        valor = Utils.redondearADosDecimales(valor);
        notificarObservers(valor); 
        return valor;
    }
}