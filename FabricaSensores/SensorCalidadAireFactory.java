public class SensorCalidadAireFactory implements ISensorFactory {
    @Override
    public Sensor crearSensor() {
        return new SensorCalidadAire();
    }
}