public class Calefaccion implements Actuador {
    private boolean encendido;
    private int temperatura; 

    @Override
    public void encender() {
        this.encendido = true;
        this.temperatura = 22; 
        System.out.println("Calefacción encendida a 22°C");
    }

    @Override
    public void apagar() {
        this.encendido = false;
        System.out.println("Calefacción apagada");
    }

    @Override
    public void ajustar(int temperatura) {
        if (temperatura < 16) temperatura = 16;
        if (temperatura > 30) temperatura = 30;

        this.temperatura = temperatura;
        if (!encendido) encender();
        System.out.println("Ajustando calefacción a " + temperatura + "°C");
    }

    public boolean isEncendida() {
        return encendido;
    }

    public int getTemperatura() {
        return temperatura;
    }
}
