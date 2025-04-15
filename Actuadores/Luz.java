public class Luz extends ActuadorBase {
    private int intensidad; // 0% - 100%

    @Override
    public void encender() {
        super.encender();
        this.intensidad = 100;
        System.out.println("Luces encendidas al 100%");
    }

    @Override
    public void apagar() {
        super.apagar();
        this.intensidad = 0;
        System.out.println("Luces apagadas");
    }

    @Override
    public void ajustar(int intensidad) {
        intensidad = Math.max(0, Math.min(100, intensidad));

        if (this.intensidad != intensidad) {
            this.intensidad = intensidad;

            if (intensidad == 0) {
                apagar();
            } else {
                if (!encendido) encender(); 
                System.out.println("Ajustando intensidad de las luces a " + intensidad + "%");
            }
        }
    }

    public int getIntensidad() {
        return intensidad;
    }
}
