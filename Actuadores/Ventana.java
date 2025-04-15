public class Ventana implements Actuador {
    private boolean abierta;
    private int porcentajeApertura; // 0% - 100%

    @Override
    public void encender() {
        this.abierta = true;
        this.porcentajeApertura = 100;
        System.out.println("Ventana abierta");
    }

    @Override
    public void apagar() {
        this.abierta = false;
        this.porcentajeApertura = 0;
        System.out.println("Ventana cerrada");
    }

    @Override
    public void ajustar(int porcentajeApertura) {
        if (this.porcentajeApertura != porcentajeApertura) {
            this.porcentajeApertura = porcentajeApertura;
            this.abierta = porcentajeApertura > 0;
            System.out.println("Ajustando persiana a " + porcentajeApertura + "% de apertura");
        }   
    }

    public boolean isAbierta() {
        return abierta;
    }

    public int getPorcentajeApertura() {
        return porcentajeApertura;
    }
}