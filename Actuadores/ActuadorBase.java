public abstract class ActuadorBase implements Actuador {
    protected boolean encendido;

    @Override
    public void encender() {
        encendido = true;
    }

    @Override
    public void apagar() {
        encendido = false;
    }

    public boolean isEncendido() {
        return encendido;
    }
}
