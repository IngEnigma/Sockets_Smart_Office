public class Persiana implements Actuador {
    private boolean abierta;
    private int porcentajeApertura; // 0% - 100%

    @Override
    public void encender() {
        this.abierta = true;
        this.porcentajeApertura = 100;
        System.out.println("Persiana abierta");
    }

    @Override
    public void apagar() {
        this.abierta = false;
        this.porcentajeApertura = 0; 
        System.out.println("Persiana cerrada");
    }

    @Override
    public void ajustar(int porcentajeApertura) {
        if (this.porcentajeApertura != porcentajeApertura) {
            this.porcentajeApertura = porcentajeApertura;
            this.abierta = porcentajeApertura > 0;
            System.out.println("Ajustando persiana a " + porcentajeApertura + "% de apertura");
        }        
    }

    public int getApertura() {
        return porcentajeApertura;
    }

    public boolean isAbierta() {
        return abierta;
    }
}