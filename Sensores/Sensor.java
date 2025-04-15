import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Sensor {
    private static final Random RANDOM = new Random(); 
    private List<SensorObserver> observers = new ArrayList<>();
    private ScheduledExecutorService scheduler;
    private long intervalo;
    private String tipo;

    public void iniciar() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                leerValor(); 
            }, 0, intervalo, TimeUnit.SECONDS);
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

    public Sensor(String tipo, long intervalo) {
        this.intervalo = intervalo;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    protected Random getRandom() {
        return RANDOM;
    }

    public void agregarObserver(SensorObserver observer) {
        observers.add(observer);
    }

    public void eliminarObserver(SensorObserver observer) {
        observers.remove(observer);
    }

    public abstract double leerValor(); 
}
