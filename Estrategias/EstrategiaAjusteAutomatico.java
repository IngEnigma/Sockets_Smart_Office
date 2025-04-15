public class EstrategiaAjusteAutomatico implements IStrategyAjuste {

    @Override
    public void ajustar(ActuadorBase actuador, double valor) {
        if (actuador instanceof AireAcondicionado) {
            int temperaturaIdeal = (int) valor; 
            ((AireAcondicionado) actuador).ajustar(temperaturaIdeal);
        } 
        else if (actuador instanceof Calefaccion) {
            int temperaturaIdeal = (int) valor; 
            ((Calefaccion) actuador).ajustar(temperaturaIdeal);
        } 
        else if (actuador instanceof Luz) {
            int intensidadIdeal = (int) valor;
            ((Luz) actuador).ajustar(intensidadIdeal);
        } 
        else if (actuador instanceof Persiana) {
            int aperturaIdeal = (int) valor;
            ((Persiana) actuador).ajustar(aperturaIdeal);
        } 
        else if (actuador instanceof Ventana) {
            int aperturaIdeal = (int) valor; 
            ((Ventana) actuador).ajustar(aperturaIdeal);
        }
    }
}
