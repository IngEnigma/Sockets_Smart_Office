public class Luz implements Actuador {
    private boolean encendida;
    private int intensidad; // 0% - 100%

    @Override
    public void encender() {
        this.encendida = true;
        this.intensidad = 100; 
        System.out.println("Luces encendidas");
    }

    @Override
    public void apagar() {
        this.encendida = false;
        System.out.println("Luces apagadas");
    }

    @Override
    public void ajustar(int intensidad) {
        if (intensidad < 0) intensidad = 0;
        if (intensidad > 100) intensidad = 100;

        if (this.intensidad != intensidad) {
            this.intensidad = intensidad;
            this.encendida = intensidad > 0;
            System.out.println("Ajustando intensidad de las luces a " + intensidad + "%");
        }
    }

    public boolean isEncendida() {
        return encendida;
    }

    public int getIntensidad() {
        return intensidad;
    }
}