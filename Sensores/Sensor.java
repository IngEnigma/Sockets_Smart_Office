import java.util.Random;

public abstract class Sensor {
    private String tipo;
    private Random random;

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

    public abstract double leerValor();
}