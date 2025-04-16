import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Sensor {
    private List<ISensorObserver> observers = new ArrayList<>();
    private static final Random RANDOM = new Random(); 
    private ScheduledExecutorService scheduler;

    private double valorActual;
    private long intervalo;
    private String tipo;

    protected abstract double generarValorAleatorio();
    
    protected abstract double leerValor();

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
            System.out.println("Sensor " + tipo + " detenido.");
        }
    }

    protected void notificarObservers(double valor) {
        EventoSensor evento = new EventoSensor(tipo, valor);
        for (ISensorObserver observer : observers) {
            observer.actualizar(evento);
        }
    }

    public void agregarObserver(ISensorObserver observer) {
        observers.add(observer);
    }

    public void eliminarObserver(ISensorObserver observer) {
        observers.remove(observer);
    }
    
    public Sensor(String tipo, long intervalo) {
        this.intervalo = intervalo;
        this.tipo = tipo;
        this.valorActual = generarValorAleatorio();
    }

    public String getTipo() {
        return tipo;
    }

    public double getValorActual() {
        return valorActual;
    }

    public void setValorActual(double valorActual) {
        this.valorActual = valorActual;
    }

    protected Random getRandom() {
        return RANDOM;
    }
}
