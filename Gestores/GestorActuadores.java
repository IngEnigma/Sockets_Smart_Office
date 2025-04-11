public class GestorActuadores {
    private Luz luz;
    private Ventana ventana;
    private Persiana persiana;
    private AireAcondicionado aireAcondicionado;
    private Calefaccion calefaccion;

    public GestorActuadores() {
        this.luz = new Luz();
        this.ventana = new Ventana();
        this.persiana = new Persiana();
        this.aireAcondicionado = new AireAcondicionado();
        this.calefaccion = new Calefaccion();
    }

    public void ajustarIluminacion(int intensidad) {
        luz.ajustar(intensidad);
    }

    public void ajustarVentana(int porcentajeApertura) {
        ventana.ajustar(porcentajeApertura);
    }

    public void ajustarPersiana(int porcentajeApertura) {
        persiana.ajustar(porcentajeApertura);
    }

    public void ajustarTemperatura(int temperatura) {
        if (temperatura > 25) {
            aireAcondicionado.ajustar(temperatura);
            calefaccion.apagar();
        } else if (temperatura < 18) {
            calefaccion.ajustar(temperatura);
            aireAcondicionado.apagar();
        } else {
            aireAcondicionado.apagar();
            calefaccion.apagar();
        }
    }
}