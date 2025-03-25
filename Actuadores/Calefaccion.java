public class Calefaccion implements Actuador {
    private boolean encendida;
    private int temperatura; 

    @Override
    public void encender() {
        this.encendida = true;
        this.temperatura = 22; 
        System.out.println("Calefacción encendida a 22°C");
    }

    @Override
    public void apagar() {
        this.encendida = false;
        this.temperatura = 0;
        System.out.println("Calefacción apagada");
    }

    @Override
    public void ajustar(int temperatura) {
        if (temperatura < 16) temperatura = 16;
        if (temperatura > 30) temperatura = 30;

        this.temperatura = temperatura;
        this.encendida = true;
        System.out.println("Ajustando calefacción a " + temperatura + "°C");
    }

    public boolean isEncendida() {
        return encendida;
    }

    public int getTemperatura() {
        return temperatura;
    }
}
