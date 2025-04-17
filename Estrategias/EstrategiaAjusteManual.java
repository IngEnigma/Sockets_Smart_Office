public class EstrategiaAjusteManual implements IStrategyAjuste {

    @Override
    public void ajustar(ActuadorBase actuador, double valor) {
        if (actuador instanceof AireAcondicionado) {
            ((AireAcondicionado) actuador).ajustar((int) valor);
        } 
        else if (actuador instanceof Calefaccion) {
            ((Calefaccion) actuador).ajustar((int) valor);
        } 
        else if (actuador instanceof Luz) {
            ((Luz) actuador).ajustar((int) valor);
        } 
        else if (actuador instanceof Persiana) {
            ((Persiana) actuador).ajustar((int) valor);
        } 
        else if (actuador instanceof Ventana) {
            ((Ventana) actuador).ajustar((int) valor);
        }
    }
}
