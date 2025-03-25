public class AireAcondicionado implements Actuador {
    private boolean encendido;
    private int temperatura;

    @Override
    public void encender() {
        this.encendido = true;
        this.temperatura = 22; 
        System.out.println("Aire acondicionado encendido a 22°C");
    }

    @Override
    public void apagar() {
        this.encendido = false;
        this.temperatura = 0;
        System.out.println("Aire acondicionado apagado");
    }

    @Override
    public void ajustar(int temperatura) {
        if (temperatura < 16) temperatura = 16;
        if (temperatura > 30) temperatura = 30;

        this.temperatura = temperatura;
        this.encendido = true;
        System.out.println("Ajustando aire acondicionado a " + temperatura + "°C");
    }

    public boolean isEncendido() {
        return encendido;
    }

    public int getTemperatura() {
        return temperatura;
    }
}