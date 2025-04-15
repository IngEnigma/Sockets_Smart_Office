public class Ventana extends ActuadorBase {
    private int porcentajeApertura; // 0% - 100%

    @Override
    public void encender() {
        super.encender(); 
        this.porcentajeApertura = 100;
        System.out.println("Ventana abierta al 100%");
    }

    @Override
    public void apagar() {
        super.apagar(); 
        this.porcentajeApertura = 0;
        System.out.println("Ventana cerrada");
    }

    @Override
    public void ajustar(int porcentajeApertura) {
        porcentajeApertura = Math.max(0, Math.min(100, porcentajeApertura));

        if (this.porcentajeApertura != porcentajeApertura) {
            this.porcentajeApertura = porcentajeApertura;

            if (!encendido && porcentajeApertura > 0) {
                encender();
            }

            System.out.println("Ajustando ventana a " + porcentajeApertura + "% de apertura");
        }
    }

    public boolean isAbierta() {
        return encendido; 
    }

    public int getPorcentajeApertura() {
        return porcentajeApertura;
    }
}
