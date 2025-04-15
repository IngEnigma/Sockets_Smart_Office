import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Sensor {
    private List<SensorObserver> observers = new ArrayList<>();
    private ScheduledExecutorService scheduler;
    private String tipo;
    private Random random;

    public void iniciar() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                leerValor(); 
            }, 0, 5, TimeUnit.SECONDS);
        }
    }

    public void detener() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            System.out.println("Sensor de tipo " + tipo + " detenido.");
        }
    }

    protected void notificarObservers(double valor) {
        EventoSensor evento = new EventoSensor(tipo, valor);
        for (SensorObserver observer : observers) {
            observer.actualizar(evento);
        }
    }

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

    public abstract double leerValor(); 
}