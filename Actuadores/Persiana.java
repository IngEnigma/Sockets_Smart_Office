public class Persiana implements Actuador {
    private boolean abierta;
    private int apertura; // 0% - 100%

    @Override
    public void encender() {
        this.abierta = true;
        this.apertura = 100;
        System.out.println("Persiana abierta");
    }

    @Override
    public void apagar() {
        this.abierta = false;
        this.apertura = 0; 
        System.out.println("Persiana cerrada");
    }

    @Override
    public void ajustar(int porcentajeApertura) {
        if (porcentajeApertura < 0) porcentajeApertura = 0;
        if (porcentajeApertura > 100) porcentajeApertura = 100;

        this.apertura = porcentajeApertura;
        System.out.println("Ajustando persiana a " + porcentajeApertura + "% de apertura");
    }

    public int getApertura() {
        return apertura;
    }

    public boolean isAbierta() {
        return abierta;
    }
}