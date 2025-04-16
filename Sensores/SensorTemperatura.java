import java.util.Random;

public class SensorTemperatura extends Sensor {

    public SensorTemperatura() {
        super("Temperatura", 20);
    }

    @Override
    protected double generarValorAleatorio() {
        double valor = 24 + getRandom().nextDouble() * 4; // 24.00° - 28.00°
        return Utils.redondearADosDecimales(valor);
    }

    @Override
    public double leerValor() {
        double valor = generarValorAleatorio();
        notificarObservers(valor); 
        return valor;
    }
}