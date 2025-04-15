public abstract class ActuadorBase implements IActuador {
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
