public class GestorActuadores {
    private AireAcondicionado aireAcondicionado;
    private Calefaccion calefaccion;
    private Luz luz;
    private Persiana persiana;
    private Ventana ventana;

    public GestorActuadores() {
        this.aireAcondicionado = new AireAcondicionado();
        this.calefaccion = new Calefaccion();
        this.luz = new Luz();
        this.persiana = new Persiana();
        this.ventana = new Ventana();
    }

    public AireAcondicionado getAireAcondicionado() {
        return aireAcondicionado;
    }

    public Calefaccion getCalefaccion() {
        return calefaccion;
    }

    public Luz getLuz() {
        return luz;
    }

    public Persiana getPersiana() {
        return persiana;
    }

    public Ventana getVentana() {
        return ventana;
    }
}
