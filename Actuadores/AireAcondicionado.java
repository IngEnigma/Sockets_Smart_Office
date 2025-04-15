public class AireAcondicionado extends ActuadorBase {
    private int temperatura;

    @Override
    public void encender() {
        super.encender();
        this.temperatura = 22; 
        System.out.println("Aire acondicionado encendido a 22°C");
    }

    @Override
    public void apagar() {
        super.apagar();
        this.temperatura = 0;
        System.out.println("Aire acondicionado apagado");
    }

    @Override
    public void ajustar(int temperatura) {
        this.temperatura = Math.max(16, Math.min(30, temperatura));
        if (!encendido) encender();
        System.out.println("Ajustando aire acondicionado a " + temperatura + "°C");
    }

    public boolean isEncendido() {
        return encendido;
    }

    public int getTemperatura() {
        return temperatura;
    }
}