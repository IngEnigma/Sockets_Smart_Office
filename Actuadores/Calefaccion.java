public class Calefaccion extends ActuadorBase {
    private int temperatura; 

    @Override
    public void encender() {
        super.encender();
        this.temperatura = 22; 
        System.out.println("Calefacción encendida a 22°C");
    }

    @Override
    public void apagar() {
        this.encendido = false;
        this.temperatura = 0;
        System.out.println("Calefacción apagada");
    }

    @Override
    public void ajustar(int temperatura) {
        this.temperatura = Math.max(16, Math.min(30, temperatura));
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
