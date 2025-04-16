public class EstrategiaAjusteAutomatico implements IStrategyAjuste {
    @Override
    public void ajustar(ActuadorBase actuador, double valor) {
        if (actuador instanceof AireAcondicionado aire) {
            if (valor > 25) {
                aire.ajustar((int) valor);
            } else {
                aire.apagar();
            }
        } else if (actuador instanceof Calefaccion calefaccion) {
            if (valor < 18) {
                calefaccion.ajustar((int) valor);
            } else {
                calefaccion.apagar();
            }
        } else if (actuador instanceof Luz luz) {
            if (valor < 30) {
                luz.ajustar(100);
            } else if (valor > 70) {
                luz.ajustar(0);
            } else {
                luz.ajustar(50);
            }
        } else if (actuador instanceof Ventana ventana) {
            if (valor < 50) {
                ventana.ajustar(100); 
            } else {
                ventana.ajustar(0);  
            }
        } else if (actuador instanceof Persiana persiana) {
            persiana.ajustar((int) valor);
        }
    }
}
