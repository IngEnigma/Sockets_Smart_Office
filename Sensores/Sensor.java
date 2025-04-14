import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public abstract class Sensor {
    private String tipo;
    private Random random;
    private List<SensorObserver> observers = new ArrayList<>();

    public Sensor(String tipo, Random random) {
        this.tipo = tipo;
        this.random = random;
    }

    public String getTipo() {
        return tipo;
    }

    protected Random getRandom() {
        return random;
    }

    public void agregarObserver(SensorObserver observer) {
        observers.add(observer);
    }

    public void eliminarObserver(SensorObserver observer) {
        observers.remove(observer);
    }

    protected void notificarObservers(double valor) {
        EventoSensor evento = new EventoSensor(tipo, valor);
        for (SensorObserver observer : observers) {
            observer.actualizar(evento);
        }
    }

    public void iniciar() {
        new Thread(() -> {
            while (true) {
                double valor = leerValor();  // leer y notificar
                try {
                    Thread.sleep(5000); // espera 5 segundos (puedes ajustar)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }    

    public abstract double leerValor(); 
}